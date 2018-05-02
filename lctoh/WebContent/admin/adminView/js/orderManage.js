//订单管理页面
var customer;

$(function(){ 
	//初始化订单数据
	init_customer();
    //操作订单事件
	operation_cust();
    
 })
 function init_customer(){ //初始化订单数据
	
}
function operation_cust(){//操作订单事件
	
	//查看订单详情
	$(".order_detaile .btn_order").unbind("click");
	$('.order_detaile .btn_order').click(function(){
		layer.open({
			  type: 2,
			  title: ['订单详情','font-size:18px;'],
			  shadeClose: true,
			  shade: 0.2,
			  area: ['820px', '600px'],
			  content: './orderDetail.html'  //iframe的url
			}); 
	})
}