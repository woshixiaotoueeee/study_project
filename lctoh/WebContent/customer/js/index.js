$(function(){
	init();//初始化主体页面
	loadRestaurantHtml();
})
function init(){
	  /*加载主页主体页面*/
	/* var initStr='<iframe src="./customer.html" name="iframe_a" scrolling="no"></iframe>';
	 $('#section_change').html(initStr);
	 $('footer').css('margin-top','-5px'); */
	
	 //购物车移动
	 var isLeft=1;
     $('#click_car').click(function(){
        if(isLeft==1){
          $('.shopping_car').animate({right:'0px'},2000);  
          $(this).find('.greater').html('&lsaquo;');         
          isLeft=2;
       }
       else{
           $('.shopping_car').animate({right:'22px'},2000); 
           $(this).find('.greater').html('&rsaquo;');              
           isLeft=1;
       }
     })
	
}
function loadRestaurantHtml(){
  
	/*点击用户图标显示用户设置*/
	$('.small_up').click(function(){   	 
		$('.set_up').css('display','block');
	})
	$('.set_up').mouseout(function(){
		$('.set_up').css('display','none');
	})
	$('.set_up').mouseover(function(){
		$('.set_up').css('display','block');
	})
   
	//点击我的设置进行调整事件
	$('.set_up ul li').click(function(){
		$(this).css('background','#ccc').siblings().css('background','#fff');
	})
	$('#my_center').click(function(){ //个人中心
		//alert('跳到个人中心');     
	})
	$('#my_coll').click(function(){  //我的收藏
	   //alert('跳到我的收藏')
	})
	$('#my_adress').click(function(){ //我的地址
	   //alert('跳到我的地址')
	})
	$('#sign_out').click(function(){  //退出登录
	   //alert('跳到登录页面')
	})
   /*
    *  跳转页面设置
    * */
   function changePage(){
	   /*点击我的订单进入我的订单信息*/
	   $('#my_order').click(function(){
		   var str='<iframe src="./myCenter.html" name="iframe_a" scrolling="no"></iframe>';
		   $('#section_change').html(str);
		   $(this).addClass('frist_show').siblings().removeClass('frist_show');
	   })
	    /*点击个人中心进入我的个人信息*/
	   $('#my_center').click(function(){
		   var str='<iframe src="./myCenter.html" name="iframe_a" scrolling="no"></iframe>';
		   $('#section_change').html(str); 
		   $(this).addClass('frist_show').siblings().removeClass('frist_show');
	   })
	    /*点击地址进入定位页面*/
	   $('#last_loca').click(function(){
		   $('#section_change').css('width','100%');
		   $('.notice_show').css('display','none');
		   var str='<iframe src="../location/address_confirm.html" name="iframe_a" scrolling="no"></iframe>';
		   $('#section_change').html(str); 
	   })
	   /*点击地址进入店家详情*/
	   $('.cont_shop').click(function(){
		   var str='<iframe src="./storeCenter.html" name="iframe_a" scrolling="no"></iframe>';
		   $('#section_change').html(str); 
	   })
	    /*点击跳转首页*/
	    $('#my_page').click(function(){
	        var initStr='<iframe src="./customer.html" name="iframe_a" scrolling="no"></iframe>';
	   	    $('#section_change').html(initStr);
	   	   $(this).addClass('frist_show').siblings().removeClass('frist_show');
	    })
	    /*点击到联系我们*/
	    $('#contact_us').click(function(){	    
	        var str='<iframe src="../ownShare/connect.html" name="iframe_a" scrolling="no"></iframe>';
	   	    $('#section_change').html(str);
	   	    $(this).addClass('frist_show').siblings().removeClass('frist_show');
	    })
	    $('footer').css('margin-top','-5px');    
   }
	changePage();
}
 
 