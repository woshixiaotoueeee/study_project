$(function(){
     init();//初始化  渲染html
     jump_click(); //跳转链接
})
function init(){
   
   //点击换颜色(首页 订单管理 )
   $('.header-wrap .nav ul li').click(function(){
   	   $(this).addClass('frist_show').siblings().removeClass('frist_show');
   })

}
function jump_click(){

    //点击首页跳转到首页页面
	$('#home_page').click(function(){
   	   $('#iframe_a').attr('src','./restaurant.html');
  	})
  	//点击订单管理跳转到订单管理页面
   	$('#order_manage').click(function(){
   	
  	})
  	//点击菜肴管理跳转到菜肴管理页面
   	$('#menu_manage').click(function(){
   		
   	   $('#iframe_a').attr('src','./dishView.html');
  	})
  	//点击财务管理跳转到财务管理页面
  	$('#finance_manage').click(function(){
   	
  	})
 	//点击联系我们跳转到联系我们页面
 	$('#contact_us').click(function(){

  	})
   
}