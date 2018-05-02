//商家管理页面
var restaurant;

$(function(){ 
	//初始化商家数据
	init_restaurant();
    //操作商家事件
	operation_restaurant();
    
 })
 function init_restaurant(){ //初始化商家数据
	
}
function operation_restaurant(){//操作商家事件
	//解封
	$(".rest_detaile .unseal").unbind("click");
	$('.rest_detaile .unseal').click(function(){
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
	$(".rest_detaile .seal").unbind("click");
	$('.rest_detaile .seal').click(function(){
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
	$(".rest_detaile .count_check").unbind("click");
	$('.rest_detaile .count_check').click(function(){
		layer.open({
			  type: 2,
			  title: ['统计图表','font-size:18px;'],
			  shadeClose: true,
			  shade: 0.2,
			  area: ['820px', '480px'],
			  content: './restGraph.html'  //iframe的url
			}); 
	})
}