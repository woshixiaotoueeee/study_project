//var order=null;
window.onload=function(){
	getDispatching(GetQueryString('orderId'));
}




function getDispatching(orderId){
	$.ajax({  
        url: Common.getDispatchingById,  
        type: 'POST',  
        data: {'dispatchingId':orderId}, 
        dataType: "json",
        success: function (returndata) {
        	if(returndata.type==0){
        		layer.msg(returndata.responseInfo, {time:2500});
        	}else{
        		setDispatchingToHtml(returndata.responseInfo);
        	}
        },  
        error: function (returndata) {  
       	 layer.msg('获取订单信息失败，请稍后再试', {time:2500}); 
        }  
   });
}


function setDispatchingToHtml(dispatchingInfo){
	$('.ban_img div').css('display','none');
	$('.ban_img #'+dispatchingInfo.dispatchingState.stateId).css('display','block');
	$('.pic_por img').attr('src','../'+dispatchingInfo.dispatchingOrder.orderRestaurant.restaurantImage);
	$('.right_inf div.name_num label').html(dispatchingInfo.dispatchingOrder.orderRestaurant.restaurantName);
	$('.phone_restaurant').html(dispatchingInfo.dispatchingOrder.orderRestaurant.restaurantPhone);
	$('.orderId').html(dispatchingInfo.dispatchingOrder.orderId);
	$('.address_restaurant').html(dispatchingInfo.dispatchingOrder.orderRestaurant.restaurantAddressInfo);
	
	setOrderItemList(dispatchingInfo.dispatchingOrder.orderItemList,
			dispatchingInfo.dispatchingOrder.orderPrice,
			dispatchingInfo.dispatchingOrder.orderRestaurant);
	setDispatchingInfoToHtml(dispatchingInfo.dispatchingOrder.orderHarvestAddress,dispatchingInfo.dispatchingOrder.orderDeliveryTime);
}

function setDispatchingInfoToHtml(orderHarvestAddress,orderDeliveryTime){
	
	//$('.psfs .info').html("联创配送");
	if(orderDeliveryTime!=null){
		$('.wcsj .info').html(getDate(orderDeliveryTime)+" "+getTime(orderDeliveryTime));
	}
	$('.addressName .info').html(orderHarvestAddress.harvestAddressName);
	$('.addressPhone .info').html(orderHarvestAddress.harvestAddressPhone);
	$('.addressInfo .info').html(orderHarvestAddress.harvestAddressInfo);
	
}


function setOrderItemList(orderItemList,orderPrice,orderRestaurant){
	$('.order_detail').html(orderPrice);
	var orderdetail_cont=$('.detail_cont_1');
	orderdetail_cont.html('');
	var dishHtml='';
	
	dishHtml+="<div class='order_entry  order_title'><div class='ord_dishes'>菜品</div><div class='ord_num'>数量</div>" +
			"<div class='ord_price'>单价</div><div class='ord_subt'>小计</div></div>";
	for(var i=0;i<orderItemList.length;i++){
		dishHtml+="<div class='order_entry'><div class='ord_dishes'>"+orderItemList[i].orderItemDish.dishName+"</div>" +
				"<div class='ord_num'>"+orderItemList[i].orderItemCount+"</div><div class='ord_price'>"+orderItemList[i].orderItemDish.dishPrice+"</div><div class='ord_subt'>"+orderItemList[i].orderItemSum+"</div></div>";
	}
	dishHtml+="<div class='order_entry'><div class='ord_dishes'>配送费</div><div class='ord_num'></div><div class='ord_price'></div>" +
			"<div class='ord_subt'>"+orderRestaurant.restaurantDeliveryFee+"</div> </div>";
	
	orderdetail_cont.html(dishHtml);
}