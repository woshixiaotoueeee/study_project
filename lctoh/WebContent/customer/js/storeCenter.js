var restaurant=null;

window.onload=function(){
	var rtid=GetQueryString("rtid");
	if(rtid==null){
		 layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
		 return;
	}else{
		getrestaurantInfo(rtid);
	}
}
//获取店家信息
function getrestaurantInfo(rtid){
	$.ajax({
		   type: "post",
		   data:{"restaurantId":rtid},
		   url:Common.getRestaurantByRestaurantId,
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   restaurant=data.responseInfo;
				   initHtml();
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

//店家信息写入页面
function initHtml(){
	initrestaurantInfo();
	initDishCategory();
	initDish();
}
//店家基本信息
function initrestaurantInfo(){
	$('.store_brief').find('p').html(restaurant.restaurantNotice);
	$('.img_shop').find('img').attr('src','../'+restaurant.restaurantImage);
	$('.shop_address').find('span').html(restaurant.restaurantAddressInfo);
	$('.shop_title').html(restaurant.restaurantName);
	$('#restaurantOfferPrice').html(restaurant.restaurantOfferPrice+'￥');
	$('#restaurantDeliveryFee').html(restaurant.restaurantDeliveryFee+'￥');
	$('.notice_cont').find('p').html(restaurant.restaurantNotice);
}
//菜肴分类信息
function initDishCategory(){
	
	var dishHtml=$('.res_menu_type ul');
	var  dishCategoryList=restaurant.dishCategory;
	
	var str="<li>全部菜肴<hr></li>";
	for(var i=0;i<dishCategoryList.length;i++){
		str+="<li id='"+dishCategoryList[i].dishCategoryId+"' >"+dishCategoryList[i].dishCategoryName+"<hr></li>"
	}
	dishHtml.html(str);
	dishHtml.find('li').click(function(){
		initDish(this.id);
     })
	
}
function initDish(dcid){
	var  dishCategoryList=restaurant.dishCategory;
	
	
	var dishHtml=$('.res_menu_content');
	var str='';
	if(dcid==null||dcid==''){
		for(var i=0;i<dishCategoryList.length;i++){
			for(var j=0;dishCategoryList[i].dishList!=null&&j<dishCategoryList[i].dishList.length;j++){
				str+=dishToHtml(dishCategoryList[i].dishList[j]);
			}
		}
	}else{
		for(var i=0;i<dishCategoryList.length&&dcid==dishCategoryList[i].dishCategoryId;i++){
			for(var j=0;j<dishCategoryList[i].dishList.length;j++){
				str+=dishToHtml(dishCategoryList[i].dishList[j]);
			}
		}
	}
	dishHtml.html(str);
	
	$('.pic_car').click(function(){
		addToCart($(this).parents('.menu_single').attr("id"));
    })
}

function dishToHtml(dish){
	var dishstr="<div class='menu_single' id="+dish.dishId+">" +
			"<div class='img_single' >" +
			"<img  src='../restaurant"+dish.dishImage+"' style='width:100%;'>" +
			"</div>" +
			"<div class='single_same'>" +
			"<div class='single_tit'><label >"+dish.dishName+"</label></div>" +
			"<div class='single_sales'><div class='img_score'>" +
			"<img src='./images/store_detail/xingxing.png'>" +
			"</div>" +
			"<div class='shop_num'>月售<span>264份</span></div>" +
			"</div>" +
			"<div class='single_price'><span class='shop_state'>￥"+dish.dishPrice.toFixed(2)+"</span>" +
			"<div class='pic_car'><img src='./images/store_detail/addbuy.png'></div>" +
			"</div></div></div>";
	return dishstr;
}

function addToCart(dishId){
	$.ajax({
		   type: "post",
		   data:{'dishId':dishId,'dishCount':1},
		   url:Common.addDishCart,
		   dataType: "json",
		   success:function(data){
			   layer.msg(data.responseInfo, {time:2500});
		   },
		   error:function(errordate){
			   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
		   }
		})
}



