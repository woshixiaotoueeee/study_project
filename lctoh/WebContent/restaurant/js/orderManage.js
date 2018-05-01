//商家的订单管理页面
var restCenter;
$(function(){
	
	//初始化商家订单的信息数据
	init_order();
    
    
 })
function init_order(){
     my_center_list(); /*新订单 正在配送 取消订单 完成订单切换*/
 
     /*点击显示订单详情*/
 	$(".center_cont .ord_click input").unbind("click"); 
 	$('.center_cont .ord_click input').click(function(){
 	    order_detail( $(this).attr('id'));
 	})
}
function my_center_list(){
	  /*新订单 正在配送 取消订单 完成订单切换*/
	   $('.infor_left ul li').mouseover(function(){
		    var _this=this;
		    center_tabs(_this);
	    })
	   $('.infor_left ul li').click(function(){  //点击切换	
		   var index=$(this).index();
	       var _this=this;
	       if(index==0){
		     	  return false;
		   }
	       center_tabs(_this);	
	       /*$('.span_left').eq(index).css('display','block').siblings().css('display','none');*/
	       $('.center_cont').eq(index-1).css('display','block').siblings().css('display','none');  
	       $('.pic_infor_right').css('display','block');   
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
