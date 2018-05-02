//骑手管理页面
var rider;

$(function(){ 
	//初始化骑手数据
	init_rider();
    //操作骑手事件
	operation_rider();
    
 })
 function init_rider(){ //初始化骑手数据
	
}
function operation_rider(){//操作骑手事件
	//解封
	$(".rider_detaile .unseal").unbind("click");
	$('.rider_detaile .unseal').click(function(){
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
	$(".rider_detaile .seal").unbind("click");
	$('.rider_detaile .seal').click(function(){
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
	$(".rider_detaile .count_check").unbind("click");
	$('.rider_detaile .count_check').click(function(){
		layer.open({
			  type: 2,
			  title: ['骑手统计图表','font-size:18px;'],
			  shadeClose: true,
			  shade: 0.2,
			  area: ['820px', '480px'],
			  content: './riderGraph.html'  //iframe的url
			}); 
	})
}