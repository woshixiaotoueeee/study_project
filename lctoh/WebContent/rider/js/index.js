//骑手首页
//地图
var map;

window.onload=function(){
	setMap();
	var timeid = window.setInterval(function(){
		dingwei();
	},60000);
	setriderstate();
	init();//初始化  渲染html
	user_setting();//用户设置下拉的隐藏与显示
	jump_click(); //跳转链接
	
}
function init(){
   
   //点击换颜色(首页 统计管理 )
   $('.header-wrap .nav ul li').click(function(){

   	   $(this).addClass('frist_show').siblings().removeClass('frist_show');
   })
   
}
function user_setting(){
	$('.small_up').click(function(){
		$('.set_up').css('display','block');
		
	})
	$('.set_up').mouseout(function(){
		$('.set_up').css('display','none');
	})
	$('.set_up').mouseover(function(){
		$('.set_up').css('display','block');
	})
}
function jump_click(){
    //点击首页跳转到首页页面
	 $('#home_page').click(function(){
	   $('.sec_cont').removeClass('sec_connect');
   	   $('#iframe_a').attr('src','./riderView.html');
  	})
  	//点击统计管理跳转到统计管理页面
   	$('#comp_manage').click(function(){
   		$('.sec_cont').removeClass('sec_connect');
   	    $('#iframe_a').attr('src','./summaryView.html');
  	})
 	  //点击联系我们跳转到联系我们页面
  	$('#contact_us').click(function(){
  		$('.sec_cont').addClass('sec_connect')
  	    $('#iframe_a').attr('src','../ownShare/connect.html');   
         
  	})
    //点击个人信息跳转到设置查看个人信息页面
    $('#myDataInfor').click(function(){
  		$('.sec_cont').removeClass('sec_connect');
    	$('.header-wrap .nav ul li').removeClass('frist_show');
        $('#iframe_a').attr('src','./setInfor.html');
    })
    //点击退出登录跳转到登录页面
    $('#signOut').click(function(){
    	window.location.href=projectDirectory+'/Login/login.html'; 
    })
   
}




function setriderstate(){
	
	$.ajax({
		   type: "post",
		   data:null,
		   url:Common.getRiderLoginInfo,
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   setStatetoHtml(data.responseInfo);
			   }
			   else{
				   layer.msg(data.responseInfo, {time:2500});
				   top.location.href=projectDirectory+"/Login/login.html";
			   }
		   },
		   error:function(errordate){}
		})
}

function setStatetoHtml(rider){
	
	var _state=rider.riderState.stateId;
	var mystate=$("#myState");
	if(_state==130001){
		mystate.html("<div class='img_list'><img src='./images/state_1.png'></div>下班");
		mystate.attr('riderstate',2);
	}else {
		mystate.html("<div class='img_list'><img src='./images/state_1.png'></div>上班");
		mystate.attr('riderstate',1);
	}
	
	mystate.unbind("click"); 
	mystate.click(function(){
		var stateType=$(this).attr('riderstate');
		
		updateRiderState(stateType);
		
	})
}




function updateRiderState(stateType){
	$.ajax({
		   type: "post",
		   data:{'stateType':stateType},
		   url:Common.updateRiderState,
		   dataType: "json",
		   success:function(data){
			   layer.msg(data.responseInfo, {time:2500});
			   setriderstate();
		   },
		   error:function(errordate){}
	})
}



function setMap(){
	map=new BMap.Map();//初始化地图
}
/**自动定位*/
function dingwei(){
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			var _location={};
			//清除覆盖物
			_location.riderLongitude=r.point.lng;
			_location.riderLatitude=r.point.lat;
			sendTosession(_location);
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
}
function sendTosession(locationInfo){
	$.ajax({
		   type: "post",
		   data:locationInfo,
		   url:Common.riderLocation,
		   dataType: "json",
		   success:function(data){},
		   error:function(errordate){}
		})
}