//添加新地址页面
var map = new BMap.Map();//初始化地图
var restaurant;
var cityName;
$(function(){
	getLogininfo();
	
	init_update();
	
	//initRestaurantToHtml(restaurant);
	save_infor();
	//取消修改信息
	cancel_infor();
})


function initRestaurantToHtml(_restaurant){
	$("#upcustomerNickname").val(_restaurant.restaurantName);
	
	$("#restaurantDeliveryFee").val(restaurant.restaurantDeliveryFee);
	
	$("#restaurantOfferPrice").val(_restaurant.restaurantOfferPrice);
	
	$("#upphone").val(_restaurant.restaurantPhone);
	
	$("#upemail").val(_restaurant.restaurantUser.userEmail);
	
	$("#suggestId").val(_restaurant.restaurantAddressInfo);
	
	$("#dishes_brief").val(_restaurant.restaurantNotice);
	
	
}



function getLogininfo(){
	$.ajax({
		   type: "post",
		   data:null,
		   url: Common.getRestaurantLoginInfo,
		   dataType: "json",
		   async: false,
		   success:function(data){
			   if(data.state==1){
				   restaurant=data.responseInfo;
				   initRestaurantToHtml(restaurant);
			   }
			   else{
				   layer.msg(data.responseInfo, {time:2500});
			   }
		   },
		   error:function(errordate){
			 //  layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
		   }
		})
}

function init_update(){
	//加载地图数据选择地址列表
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
			{"input" : "suggestId"
			,"location" : map
		});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		$("#searchResultPanel").html(str);
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		$("#searchResultPanel").html("onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue);
		
		
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			
			//address.addressLatitude=pp.lat;
			//address.addressLongitude=pp.lng;
			/**经度*/
			restaurant.restaurantLongitude=pp.lng;
			/**纬度*/
			restaurant.restaurantLatitude=pp.lat;
			var geoc = new BMap.Geocoder();    
			geoc.getLocation(pp, function(rs){
				var addComp = rs.addressComponents;
				//address.addressProvince=addComp.province;
				//address.addressCity=addComp.city;
				cityName=addComp.city;
			}); 
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	});
}
function save_infor(){ 
	//信息修改
	$('.edit_save').click(function(){
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			
			/**经度*/
			restaurant.restaurantLongitude=pp.lng;
			/**纬度*/
			restaurant.restaurantLatitude=pp.lat;
			
			var geoc = new BMap.Geocoder();    
			geoc.getLocation(pp, function(rs){
				var addComp = rs.addressComponents;
				cityName=addComp.city;
				
			}); 
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search($("#suggestId").val());
		
		var resData={};
		resData.cityName=cityName;
		resData.restaurantLongitude=restaurant.restaurantLongitude;
		resData.restaurantLatitude=restaurant.restaurantLatitude;
		resData.restaurantName=$("#upcustomerNickname").val();
		resData.restaurantDeliveryFee=$("#restaurantDeliveryFee").val();
		
		resData.restaurantOfferPrice=$("#restaurantOfferPrice").val();
		resData.restaurantPhone=$("#upphone").val();
		resData.userEmail=$("#upemail").val();
		resData.restaurantAddressInfo=$("#suggestId").val();
		
		resData.restaurantNotice=$("#dishes_brief").val();
		
		
		$.ajax({
			   type: "post",
			   url:Common.updateRestaurant,
			   data:resData, 
			   dataType: "json",
			   success:function(data){
					layer.msg(data.responseInfo, {time:2500});
					setTimeout(function(){
						parent.layer.closeAll();
					},2500);
			   },
			   error:function(errordate){
				   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
				   parent.layer.closeAll();
			   }
			})
	})
	
}
function cancel_infor(){//取消信息修改
	$('.edit_cancel').click(function(){
		parent.layer.closeAll();
	})
}
