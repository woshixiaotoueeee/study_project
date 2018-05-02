//管理员首页的地图显示骑手地址
$(function(){
	//初始化数据 获取地图数据
	init_rider_map();

	//骑手信息与地图的交互操作
	edit_admin_map();
})

function init_rider_map(){ //初始化数据 获取可配送订单 配送订单 地图数据
   
}	

function edit_admin_map(){ //订单与地图的交互操作
    $('.send_admin').find('.admin_list .admin_one').click(function(){
    	alert('跳转到骑手位置');
    	$(this).css('border-color','#fa7c03').siblings().css('border-color','#666');
    	$(this).find('.rider_name').css('color','#fa7c03');
    	$(this).siblings().find('.rider_name').css('color','#666');
     })
}