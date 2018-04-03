var restaurantList=[];
window.onload=function(){
	getRestaurantCategoryData();
	getRestaurant();
}
/**
 * 获取店家分类信息
 * */
function getRestaurantCategoryData(){
	$.ajax({
	   type: "post",
	   url:Common.getRestaurantCategory,
	   dataType: "json",
	   success:function(data){
		   if(data.state==1){
			   loadRestaurantCategoryData(data.responseInfo);
		   }
		   else{
			   layer.msg('获取数据失败', {time:1000});
		   }
	   },
	   error:function(errordate){
		   layer.msg('未知错误请刷新页面或联系管理员', {time:1000});
	   }
	})
}
/**
 * 加载店家分类信息
 * rtcList 菜肴分类数组
 * */
function loadRestaurantCategoryData(rtcList){
	var rtcUl=$(".type_cont ul");
	var rtclength=rtcList.length;
	var rtcHtml="<li class='first_list' id='all_shop' value=''>" +
				"<div class='img_list' >" +
				"<img src='./images/bus_type/food_1_1.png'>" +
				"</div>全部商家</li>";
	for(var i=0 ;i<rtclength;i++){
		rtcHtml+="<li value="+rtcList[i].restaurantCategoryId+">" +
				"<div class='img_list' >" +
				"<img src='./images/bus_type/food_"+rtcList[i].restaurantCategoryIcon+".png'>" +
				"</div>"+rtcList[i].restaurantCategoryName+"</li>";
	}
	rtcUl.html(rtcHtml);
	loadRestaurantHtml();
}
/**
 * 根据店家分类ID及定位信息查询附近的店家
 * rtcId 店家分类ID
 * */
function getRestaurant(rtcId){
	$.ajax({
		   type: "post",
		   data:{"restaurantCategoryId":rtcId},
		   url:Common.getRestaurantByCityNameAndrcid,
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   restaurantList=data.responseInfo;
				   loadRestaurantData(data.responseInfo);
			   }
			   else{
				   layer.msg('获取数据失败', {time:1000});
			   }
		   },
		   error:function(errordate){
			   layer.msg('未知错误请刷新页面或联系管理员', {time:1000});
		   }
		})
}
/**
 * 加载店家信息
 * rtList 菜肴分类数组
 * */
function loadRestaurantData(rtList){
	var rtDiv=$(".sec_hotel_content");
	var rtlength=rtList.length;
	var rtHtml="";
	for(var i=0 ;i<rtlength;i++){
		rtHtml+="<div class='cont_shop' value='"+rtList[i].restaurantId+"'>" +
				"<div class='img_shop'><img src='.."+rtList[i].restaurantImage+"'></div>" +
				"<div class='shop_same shop_title'>"+rtList[i].restaurantName+"</div>" +
				"<div class='shop_same shop_cont'>共出售<span>"+rtList[i].orderCount+"份</span></div>" +
				"<div class='shop_same shop_bot' >" +
				"<ul><li>起送：<span>"+rtList[i].restaurantOfferPrice+"￥</span></li><li> 配送费：<span>"+rtList[i].restaurantDeliveryFee+"￥</span></li>" +
				"<li> 距离：<span>"+(rtList[i].restaurantDistance/1000).toFixed(2)+"km</span></li> </ul></div>" +
				"</div>";
	}
	rtDiv.html(rtHtml);
	loadRestaurantHtml();
}
/**
 * 店家排序
 * type 排序类型
 * restaurantDistance:距离
 * restaurantOfferPrice:起送费
 * restaurantDeliveryFee:配送费
 * */
function sortRestaurant(type){
	var restaurant;
	var rtListLength=restaurantList.length;
	for(var i=1;i<rtListLength;i++){
		for(var j=0;j<rtListLength-i;j++){
  			if(restaurantList[j][type]>restaurantList[j+1][type]){
  				restaurant=restaurantList[j];
  				restaurantList[j]=restaurantList[j+1];
  				restaurantList[j+1]=restaurant;
  			}
  		}
	}
	loadRestaurantData(restaurantList);
}








 