//结算购物车
$(function(){
	//初始化数据  订单及地址数据刷新
	init_shopping_car();	
	
	//清空购物车
	clear_car();
	//地址的选择
	confirm_address();
	//添加新地址
	add_address();
	//点击确认下单函数
	place_order();
	
})
function init_shopping_car(){
	//初始化数据
	
}
function place_order(){
	//确认下单
	
	$('#place_order').click(function(){
		
		layer.confirm('是否确认下单？', {
		  btn: ['确认','取消'],//按钮
		  shade: 0.2,
			}, function(){
				alert($('.unit_price').html());
				var cust_obj={};
				//配送客户的名字
				cust_obj.name=$('.address_checked').find('.cust_name').html();
				//配送客户的电话
				cust_obj.phone=$('.address_checked').find('.cust_phone').html();
				//配送客户的地址
				cust_obj.address=$('.address_checked').find('.cust_address').html();
				alert(cust_obj.address);
				alert(cust_obj.name);
			    layer.msg('下单成功', {icon: 1, time: 1000});
			    
			  
			}, function(){
			    layer.msg('取消成功', {
					    time: 1000, //1s后自动关闭
					   
					  });
			});
		
	})
}
function clear_car(){//清空购物车
	
}
function confirm_address(){//地址的选择
	$('.edit_infor').find('.address_ck').click(function(){
	
		$(this).addClass("address_checked").siblings().removeClass("address_checked");
	})
}
function add_address(){//添加新地址
	
	$('.address_new').click(function(){
		 
	    var str='./addAddress.html';
	    layer.open({
			  title: ['添加新地址', 'font-size:18px;'],
			  type: 2,
			  shadeClose: true,
			  shade: 0.2,
			  area: ['560px', '420px'],
			  content: str  //iframe的url
		 }); 
	})
	
	
	
}

