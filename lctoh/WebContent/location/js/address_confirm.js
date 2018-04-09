var map;//百度地图对象
var _location=new Object();
var provinceList=[];
window.onload = function (){
	// 百度地图API功能
	map = new BMap.Map("allmap");//初始化地图
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,11);
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	//添加地图类型控件
	map.addControl(new BMap.MapTypeControl({
		mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
           ]}));
	
	//鼠标定位
	var geoc = new BMap.Geocoder();
	map.addEventListener("click", function(e){        
		var pt = e.point;
		geoc.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			
			if(addComp.province==''||addComp.city==''){
				alert("请选择正确的位置");
				return;
			}
			
			_location.province=addComp.province;
			_location.city=addComp.city;
			_location.info='';
			if(addComp.district!=null){
				_location.info+=addComp.district;
			}
			if(addComp.street!=null){
				_location.info+=addComp.street;
			}
			if(addComp.streetNumber!=null){
				_location.info+=addComp.streetNumber;
			}
			_location.longitude=pt.lng;
			_location.latitude=pt.lat;
			//清除覆盖物
			map.clearOverlays();
			//添加点
			var mk = new BMap.Marker(pt);
			map.addOverlay(mk);
			map.panTo(pt);
			if(confirm('你的位置为：'+_location.province+'   '+_location.city+'   '+_location.info+'是否确认?')){
				Loction(_location);
			}
		});        
	});
	//自动定位城市
	var myCity = new BMap.LocalCity();
	myCity.get(myFun);
	
	//显示位置数据
	$('#show_wei').click(function(){
		 //ajax 获取数据	
			var data=$.post(
					projectDirectory+"/ProvinceController/getAllProvince",				
					function(t_data){	
						alert('位置省市');
						alert(t_data[4].provinceName+t_data[4].cityList[0].cityName);				
				},"JSON")
			 .error(
					 function(data) {
						 layer.msg('未知错误请刷新页面或联系管理员', {time:2500}); 
			});			
	})
	//商家的省份，城市联动    运用了ajax连接后台数据
	var posiData=$.post(
					projectDirectory+"/ProvinceController/getAllProvince",					
					function(positionData){	
						provinceList=positionData.responseInfo;
						setProvienceTohtml(provinceList);
						
				},"JSON")
			 .error(
					 function(posiData) {
						 layer.msg('未知错误请刷新页面或联系管理员', {time:2500}); 
			});		

	
};

/**将省市信息放入联动菜单*/
function setProvienceTohtml(_provinceList){
	$('#province').html('');
	//省份数据
	for(i in _provinceList){ //i为 省份的下标
       //添加省份数据，option的value值为省份数据的id 
			
        $('#province').append('<option value="'+_provinceList[i].provinceId+'">'+_provinceList[i].provinceName+'</option>'); 					        
    }
	
	//positionData[i].cityList[0].cityName
	//城市随省份联动  通过id相关联
	$('#province').change(function(){	
		changeprovince($(this).val());
	})
	
	function changeprovince(val){
		
		for(i in provinceList){
            if(provinceList[i].provinceId==val){
            	$('#cityList').html('');	
            	for(j in provinceList[i].cityList){	//城市city	           		 		                         
		             $('#cityList').append('<option value="'+provinceList[i].cityList[j].cityId+'">'+provinceList[i].cityList[j].cityName+'</option>');
            	}			            						                            
            }
        }
		var citylist=$('#cityList')[0];
		changeCity($('#cityList').val(),citylist.options[citylist.selectedIndex].text);
	}
	//城市随省份联动  通过id相关联
	$('#cityList').change(function(){
		changeCity($(this).val(),this.selectedOptions[0].innerText);
	})
	
	changeprovince($('#province').val());
	function changeCity(cityId,cityName){
		getRestaurant(cityId);
		map.centerAndZoom(cityName,15);
	}
	
}

function getRestaurant(cityId){
	$.ajax({
		   type: "post",
		   data:{"cityId":cityId},
		   url:Common.getRestaurantCityId,
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   loadRestaurantData(data.responseInfo);
			   }
			   else{
				   layer.msg(data.responseInfo, {time:2500});
			   }
		   },
		   error:function(errordate){
			   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
		   }
		})
}

function loadRestaurantData(restaurantList){
	var storyDiv=$('.nearby_store');
	storyDiv.html('');
	var resHtml=''
	for(var i=0;i<restaurantList.length;i++){
		resHtml+="<div class='store_one'>" +
				"<div class='img_shop'><img src='../"+restaurantList[i].restaurantImage +"'></div>" +
				"<div class='shop_same shop_title'>"+restaurantList[i].restaurantName+"</div>" +
				" <div class='shop_same shop_cont'><div class='bg_img'>****</div>" +
				"<div class='mon_sales'>共出售<span>"+restaurantList[i].orderCount+"份</span></div></div>" +
				"<div class='shop_same shop_bot' >" +
				"<ul><li> 起送：<span>"+restaurantList[i].restaurantOfferPrice+"￥</span></li><li> 配送费：<span>"+restaurantList[i].restaurantDeliveryFee+"￥</span></li><li> <span>"+(restaurantList[i].restaurantDistance/1000).toFixed(2)+"km</span></li>" +
				"</ul></div></div>";
	}
	storyDiv.html(resHtml);
}




//自动定位
function dingwei(){
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			
			if(r.address.province==''||r.address.city==''){
				alert("请选择正确的位置");
				return;
			}
			
			//清除覆盖物
			map.clearOverlays();
			map.addOverlay(mk);
			map.panTo(r.point);
			
			_location.province=r.address.province;
			_location.city=r.address.city;
			_location.info='';
			if(r.address.district!=null){
				_location.info+=r.address.district;
			}
			if(r.address.street!=null){
				_location.info+=r.address.street;
			}
			if(r.address.streetNumber!=null){
				_location.info+=r.address.streetNumber;
			}
			_location.longitude=r.point.lng;
			_location.latitude=r.point.lat;
			if(confirm('你的位置为：'+_location.province+'   '+_location.city+'   '+_location.info+'是否确认?')){
				Loction(_location);
			}
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
}
//自动定位城市
function myFun(result){
	map.setCenter(result.name);
}
//将定位信息发送到后台
function Loction(_location){
	var data=$.post(
			projectDirectory+"/LocationController/setLocation",
			_location,
			function(data){
				if(data.state==1){
					top.location.href=projectDirectory+"/customer/index.html";
				}else{
					layer.msg(data.responseInfo, {time:2500});
				}
				
				
		},"JSON")
	 .error(
			 function(data) {
				 layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
	});	
}	

