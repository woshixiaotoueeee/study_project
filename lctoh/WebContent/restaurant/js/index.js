$(function(){
     init();//初始化  渲染html
     jump_click(); //跳转链接
})
function init(){
   
   //点击换颜色(首页 订单管理 )
   $('.header-wrap .nav ul li').click(function(){
   	   $(this).addClass('frist_show').siblings().removeClass('frist_show');
   })
   //显示店家设置及财务管理、 退出登录的div
   user_setting();
}
function user_setting(){ //显示店家设置及财务管理、 退出登录的div
	$('.small_up').click(function(){
		$('.set_up').css('display','block');
		
	})
	$('.set_up').mouseout(function(){
		$('.set_up').css('display','none');
	})
	$('.set_up').mouseover(function(){
		$('.set_up').css('display','block');
	})
}
function jump_click(){

    //点击首页跳转到首页页面
	$('#home_page').click(function(){
	   $('.sec_iframe').css('height','900px');
   	   $('#iframe_a').attr('src','./restaurant.html');
  	})
  	//点击订单管理跳转到订单管理页面
   	$('#order_manage').click(function(){
   	   $('.sec_iframe').css('height','680px');
   	   $('#iframe_a').attr('src','./orderManage.html');
  	})
  	//点击菜肴管理跳转到菜肴管理页面
   	$('#menu_manage').click(function(){
   	   $('#iframe_a').attr('src','./dishView.html');
  	})
  	//点击财务管理跳转到财务管理页面
  	$('#finance_manage').click(function(){
  		$('.sec_iframe').css('height','650px');
  	    var num=2;   //2代表进入我的订单页面
	    var url='./restCenter.html?num='+num;
	    $('#iframe_a').attr('src',url);
  	})
 	//点击联系我们跳转到联系我们页面
 	$('#contact_us').click(function(){
 	  $('.sec_iframe').css('height','852px');
	  $('#iframe_a').attr('src','../ownShare/connect.html');
	   	
  	})
  	$('#rest_set').click(function(){ //店家设置
  		  $('.sec_iframe').css('height','680px');
		  var num=1;   //1代表进入店家设置页面
		  var url='./restCenter.html?num='+num;
		  $('#iframe_a').attr('src',url);
	})
	$('#rest_finance').click(function(){  //财务管理
		  $('.sec_iframe').css('height','680px');
		  var num=2;   //2代表进入我的订单页面
		  var url='./restCenter.html?num='+num;
		  $('#iframe_a').attr('src',url);
	})
	
	$('#sign_out').click(function(){  //退出登录
	   //alert('跳到登录页面')
		window.location.href=projectDirectory+'/Login/login.html'; 
	})
   
}