var project="/takeawayplatform";//项目名
var restaurant;
var _dishList;
//加载初始数据
window.onload=function(){ 
	$.post(project+"/RestaurantController/getRestaurant",{},function(data){
		if(data==null){
			if(top.location.href!=self.location.href){
				top.location.href=project+"/res/login.html";
			}
			top.location.href=project+"/res/login.html";
		}else{
			restaurant=data;
			putRestaurant(data);
		}
	},"JSON");
}


//加载店家信息至页面
function putRestaurant(res){
	$("input[name='rtname']").eq(0).val(res.rtname);
	$("input[name='rtphone']").eq(0).val(res.rtphone);
	$("input[name='rtdistribution']").eq(0).val(res.rtdistribution);
	//alert(res.rtnotice)
	//$("input[name='rtnotice']").eq(0).val("123413131");
	//$("input[name='rtnotice']").eq(0).text("132132132");
	//$("input[name='rtnotice']").eq(0).html("414777777");
	$("textarea[name='rtnotice']").eq(0).val(res.rtnotice);
	//alert($("input[name='rtnotice']").eq(0))
}


//更新店家信息
function update(){
	var rtname=$("input[name='rtname']").eq(0).val();
	var rtphone=$("input[name='rtphone']").eq(0).val();
	var rtdistribution= $("input[name='rtdistribution']").eq(0).val();
	var rtnotice=$("textarea[name='rtnotice']").eq(0).val();
	$.post(project+"/RestaurantController/updateInfo",{"rtname":rtname,"rtphone":rtphone,"rtdistribution":rtdistribution,"rtnotice":rtnotice},function(data){
		if(data==null||data==0){alert("操作失败")}
		else if(data==1){
			alert("操作成功")
			 shuaxin();
		}
	},"JSON");
	return false;
}



function shuaxin(){
	
	setTimeout(function(){top.location.href=project+"/restaurant_admin/index.html";},1000);
}
