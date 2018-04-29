//结算购物车
$(function(){
	//初始化数据  订单及地址数据刷新
	init_shopping_car();	
	
	//清空购物车
	clear_car();
	
	//点击确认下单函数
	place_order();
	
})
function init_shopping_car(){
	getCart();
	getAddress();
	//初始化数据
}
function getAddress(){
	
	$.ajax({
		   type: "post",
		   url:Common.findAddressByCustomer,
		   data:null, 
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   initAddress(data.responseInfo);
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
function initAddress(addressList){
	var addressdiv=$('.edit_infor');
	var str='';
	for(var i=0;i<addressList.length;i++){
		str+="<div class='address_one address_ck'><div class='add_name add_show'><div class='name_sex'>" +
			"<span class='cust_name' id='"+addressList[i].addressId+"'>"+addressList[i].addressName+"</span><span></span>" +
			"<span class='add_phone add_show cust_phone'>"+addressList[i].addressPhone+"</span></div></div>" +
			"<div class='add_ress add_show'><span class='cust_address'>"+addressList[i].addressProvince+addressList[i].addressCity+addressList[i].addressInfo+"</span></div></div>";
	}
	str+="<div class='address_one address_new'><div  id='add_new'>" +
			"<div class='ck_img'>+</div><div class='add_new'>添加新地址</div></div></div>";
	addressdiv.html(str);
	//选择地址
	$('.edit_infor').find('.address_ck').click(function(){
		$(this).addClass("address_checked").siblings().removeClass("address_checked");
	})
	//添加地址
	$('.address_new').click(function(){
	    var str='./addAddress.html';
	    layer.open({
			  title: ['添加新地址', 'font-size:18px;'],
			  type: 2,
			  shadeClose: true,
			  shade: 0.2,
			  area: ['560px', '420px'],
			  content: str,  //iframe的url
			  end: function () {
				  getAddress();
	            }
		 }); 
	})
}

function getCart(){
	$.ajax({
		   type: "post",
		   url:Common.getCart,
		   data:null, 
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   initCart(data.responseInfo);
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
function initCart(cart){
	var cartDiv=$('.store_infor');
	if(cart.restaurant==null)return;
	
	var str="<div class='menu_single'><div class='img_single'><img  src='./images/store_detail/store_pic1.png' style='width:100%;'>" +
			"</div><div class='single_same'><div class='single_tit'><label >"+cart.restaurant.restaurantName+"</label></div><div class='single_sales'><div class='img_score'>" +
			"<img src='../"+cart.restaurant.restaurantName+"'></div></div><div class='single_price'>配送费<span class='shop_state'>￥"+cart.restaurant.restaurantDeliveryFee+"</span></div></div></div><div class='ord_car'>";
	var item;
	for(var key in cart.map){
		if (cart.map.hasOwnProperty(key)){
			item= cart.map[key];
			str+="<div class='car_one'><span>"+item.dish.dishName+"</span><div class='add_order'><ul><li>-</li><li>"+item.dishCount+"</li><li>+</li></ul></div><span class='price_one'>￥"+item.dish.dishPrice+"</span><span class='unit_price'>￥"+item.subtotal+"</span></div>";
		}
	}
	str+="</div><div class='edit_set'><div class='pic_car'><img src='./images/shopping_car/buy_2.png'></div><div class='order_ledger'>￥<span class='ledger_num'>"+cart.total+"</span></div></div>";
	cartDiv.html(str);
}

function place_order(){
	//确认下单
	$("#place_order").unbind("click"); 
	$('#place_order').click(function(){
		layer.confirm('是否确认下单？', {
		  btn: ['确认','取消'],//按钮
		  shade: 0.2,
			}, function(){
				
				var data={};
				//配送地址ID
				data.addressId=$('.address_checked').find('.cust_name')[0].id;
				$.ajax({
					   type: "post",
					   url:Common.cartToOrder,
					   data:data, 
					   dataType: "json",
					   success:function(data){
						   if(data.state==1){
							   layer.msg('下单成功', {icon: 1, time: 2500});
						   }
						   else{
							   layer.msg(data.responseInfo, {time:2500});
						   }
					   },
					   error:function(errordate){
						   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
					   }
					})
			  
			}, function(){
			    layer.msg('取消成功', {
					    time: 1000, //1s后自动关闭
					  });
			});
		
	})
}
function clear_car(){//清空购物车
	
}



