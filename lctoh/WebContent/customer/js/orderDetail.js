//var order=null;
window.onload=function(){
	getOrder(GetQueryString('orderId'));
	
}




function getOrder(orderId){
	$.ajax({  
        url: Common.findOrderByOrderId,  
        type: 'POST',  
        data: {'orderId':orderId}, 
        dataType: "json",
        success: function (returndata) {
        	if(returndata.type==0){
        		layer.msg(returndata.responseInfo, {time:2500});
        	}else{
        		setOrderToHtml(returndata.responseInfo);
        	}
        },  
        error: function (returndata) {  
       	 layer.msg('获取订单信息失败，请稍后再试', {time:2500}); 
        }  
   });
}


function setOrderToHtml(order){
	$('.ban_img div').css('display','none');
	$('.ban_img #'+order.orderState.stateId).css('display','block');
	$('.pic_por img').attr('src','../'+order.orderRestaurant.restaurantImage);
	$('.right_inf div label').html(order.orderRestaurant.restaurantName);
	$('.phone_restaurant').html(order.orderRestaurant.restaurantPhone);
	$('.orderId').html(order.orderId);
	
	setOrderItemList(order.orderItemList,order.orderRestaurant);
	
}

function setOrderItemList(orderItemList,orderRestaurant){
	
	var orderdetail_cont=$('.detail_cont_1');
	orderdetail_cont.html('');
	var dishHtml='';
	orderdetail_cont.html('');
	
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