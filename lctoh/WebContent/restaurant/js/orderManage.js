//商家的订单管理页面
var restCenter;
var restaurant
$(function(){
	getLogininfo();
	//初始化商家订单的信息数据
	init_order();
 })
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
				   getOrder(restaurant.restaurantId);
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
function getOrder(restaurantId){
	$.ajax({
		   type: "post",
		   data:{'restaurantId':restaurantId},
		   url: Common.findOrder,
		   dataType: "json",
		   async: false,
		   success:function(data){
			   if(data.state==1){
				   setOrderToHtml(data.responseInfo);
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
function setOrderToHtml(orderList){
	var newOrderDiv=$(".order_new .edit_cont .edit_infor .order_infor .order_detaile");
	var newOrderstr='';
	
	var zzpsOrderDiv=$(".order_ending .edit_cont .edit_infor .order_infor .order_detaile");
	var zzpsOrderstr='';
	
	var wcOrderDiv=$(".order_finish .edit_cont .edit_infor .order_infor .order_detaile");
	var wcOrderstr='';
	
	var ycOrderDiv=$(".order_cancel .edit_cont .edit_infor .order_infor .order_detaile");
	var ycOrderstr='';
	
	var item;
	for(var i=0;i<orderList.length;i++){
		item=orderList[i];
		if(item.orderState.stateId==100002){
			newOrderstr+=getOrderStr(item,'订单需确认');
		}else if(item.orderState.stateId==100003){
			zzpsOrderstr+=getOrderStr(item,'订单正在配送');
		}else if(item.orderState.stateId==100004){
			wcOrderstr+=getOrderStr(item,'订单已完成');
		}else if(item.orderState.stateId==100005){
			ycOrderstr+=getOrderStr(item,'订单已取消');
		}
	}
	newOrderDiv.html(newOrderstr);
	
	zzpsOrderDiv.html(zzpsOrderstr);
	
	wcOrderDiv.html(wcOrderstr);
	
	ycOrderDiv.html(ycOrderstr);
}


function getOrderStr(item,orderInfo){
	return "<div class='order_one' >" +
				"<div class='ord_time'>" +
				"<div class='time_show'>" +
				"<span>"+getDate(item.orderCreatTime)+"</span>" +
				"<span>"+getTime(item.orderCreatTime)+"</span>" +
				"</div></div>" +
				"<div class='pic_img'></div>" +
				"<div class='border_bot'>" +
				"<div class='ord_cont'>" +
					"<div class='ord_title'>"+item.orderRestaurant.restaurantName+"</div>" +
					"<div class='ord_inf'></div>" +
					"<div class='ord_number'><span>订单号：</span><span>"+item.orderId+"</span></div>" +
				"</div>" +
				"<div class='ord_state'><span>"+orderInfo+"</span></div>" +
				"<div class='ord_money'> <span>"+item.orderPrice+"元</span><span>在线支付</span></div>" +
				"<div class='ord_click ' ><input class='first_ord' orderId='"+item.orderId+"' type='button' name='' value='订单详情'></div>" +
			"</div>";
}



function init_order(){
     my_center_list(); /*新订单 正在配送 取消订单 完成订单切换*/
 
     /*点击显示订单详情*/
 	$(".center_cont .ord_click input").unbind("click"); 
 	$('.center_cont .ord_click input').click(function(){
 		
 	    order_detail( $(this).attr('orderId'));
 	})
}
function my_center_list(){
	  /*新订单 正在配送 取消订单 完成订单切换*/
	   $('.infor_left ul li').mouseover(function(){
		    var _this=this;
		    center_tabs(_this);
	    })
	    $(".infor_left ul li").unbind("click");
	   $('.infor_left ul li').click(function(){  //点击切换	
		   var index=$(this).index();
	       var _this=this;
	       if(index==0){
		     	  return false;
		   }
	       center_tabs(_this);	
	       /*$('.span_left').eq(index).css('display','block').siblings().css('display','none');*/
	       $('.center_cont').eq(index-1).css('display','block').siblings().css('display','none');  
	       /*$('.pic_infor_right').css('display','block');*/   
	    })	    
	  /*新订单 正在配送 取消订单 完成订单切换*/	 	  
}
function center_tabs(_this){  //切换
	   var index=$(_this).index();
	   if(index==0){
	     	  return false;
	      }
	   $(_this).find('.dec_span').addClass('first_dec');
	   $(_this).siblings().find('.dec_span').removeClass('first_dec');
	      
}
function order_detail(orderId){
	 var str='./orderDetail.html?orderId='+orderId;
     layer.open({
		  title: ['订单详情', 'font-size:18px;'],
		  type: 2,
		  shadeClose: true,
		  shade: 0.8,
		  area: ['800px', '630px'],
		  content: str  //iframe的url
	 }); 
}
