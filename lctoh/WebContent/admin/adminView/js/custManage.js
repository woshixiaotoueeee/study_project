//客户管理页面
var customer;

$(function(){ 
	//初始化客户数据
	init_customer();
    //操作客户事件
	operation_cust();
    
 })
 function init_customer(){ //初始化客户数据
	
}
function operation_cust(){//操作客户事件
	//解封
	$(".order_detaile .unseal").unbind("click");
	$('.order_detaile .unseal').click(function(){
		layer.confirm('是否确认解封？', {
			  btn: ['确认','取消'],//按钮
			  shade: 0.2,
				}, function(){
					
					layer.msg('解封成功', {
					    time: 1000, //1s后自动关闭
					});
				  
				}, function(){
				    
				});
	})
	//封号
	$(".order_detaile .seal").unbind("click");
	$('.order_detaile .seal').click(function(){
		layer.confirm('是否确认封号？', {
			  btn: ['确认','取消'],//按钮
			  shade: 0.2,
				}, function(){
					
					layer.msg('封号成功', {
					    time: 1000, //1s后自动关闭
					});
				  
				}, function(){
				    
				});
	})
	//统计查看
	$(".order_detaile .count_check").unbind("click");
	$('.order_detaile .count_check').click(function(){
		layer.open({
			  type: 2,
			  title: ['统计图表','font-size:18px;'],
			  shadeClose: true,
			  shade: 0.2,
			  area: ['820px', '480px'],
			  content: '/lctoh/customer/orderGraph.html'  //iframe的url
			}); 
	})
}