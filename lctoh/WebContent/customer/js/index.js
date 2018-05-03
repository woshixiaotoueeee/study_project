$(function(){
	init();//初始化主体页面
	loadRestaurantHtml();
})
function init(){
	  /*加载主页主体页面*/
	/* var initStr='<iframe src="./customer.html" name="iframe_a" scrolling="no"></iframe>';
	 $('#section_change').html(initStr);
	 $('footer').css('margin-top','-5px'); */	
	//input 的获取焦点事件
	  $('.query_btn input').focus(function(){
	    	$(this).attr('placeholder','');	
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
	//公告显示
	$('.notice_pic').click(function(){
		$('.notice_cont').css('display','block');
	})
	$('.notice_cont').mouseout(function(){
		$(this).css('display','none');
	})
	
    //var num=null; //var url='./个人信息.html?num='+num 一个参数  跳转到我的个人中心时要传的参数
	
	//点击我的设置进行调整事件
	$('.set_up ul li').click(function(){
		$(this).css('background','#ccc').siblings().css('background','#fff');
		 $('.query_btn').css('display','none');
		 $('.sec_right').css('display','none');
		 $('.nav ul li').removeClass('frist_show');
		 $('#section_change').css('height','920px');
	})
	$('#my_center').click(function(){ //个人资料
		  var num=1;   //1代表进入我的资料页面
		  //var url='./myCenter.html?num='+num 一个参数
		  var url='./myCenter.html?num='+num;
		  var str='<iframe src='+url+' id="iframe_mycoll"  name="iframe_a" scrolling="no"></iframe>'; 
		  $('#section_change').html(str);
	})
	$('#my_coll').click(function(){  //我的收藏
		  var num=3;   //2代表进入我的订单页面
		  //var url='./myCenter.html?num='+num 一个参数
		  var url='./myCenter.html?num='+num;
		  var str='<iframe src='+url+' id="iframe_mycoll"  name="iframe_a" scrolling="no"></iframe>';
		  $('#section_change').html(str);
	})
	$('#my_adress').click(function(){ //我的地址
		  var num=4;   //2代表进入我的订单页面
		  //var url='./myCenter.html?num='+num 一个参数
		  var url='./myCenter.html?num='+num;
		  var str='<iframe src='+url+' id="iframe_mycoll"  name="iframe_a" scrolling="no"></iframe>';
		  $('#section_change').html(str);
	})
	$('#sign_out').click(function(){  //退出登录
	   //alert('跳到登录页面')
		window.location.href=projectDirectory+'/Login/login.html'; 
	})
   /*
    *  跳转页面设置
    * */
   function changePage(){
	   /*点击我的订单进入我的订单信息*/
	   $('#my_order').click(function(){
		    var num=2;   //2代表进入我的订单页面
		    //var url='./个人信息.html?num='+num 一个参数
		    var url='./myCenter.html?num='+num;
		    var str='<iframe src='+url+' id="iframe_mycoll"  name="iframe_a" scrolling="no"></iframe>';
		    $('.query_btn').css('display','none');
		    $('.sec_right').css('display','none');	
		   //var str='<iframe src='+url+' id="iframe_order" name="iframe_a" scrolling="no"></iframe>';
		   $('#section_change').html(str);
		   $(this).addClass('frist_show').siblings().removeClass('frist_show');
		   $('#section_change').css('height','920px');
	   })
	   /* 点击个人中心进入我的个人信息
	   $('#my_center').click(function(){
		   var str='<iframe src="./myCenter.html" name="iframe_a" scrolling="no"></iframe>';
		   $('#section_change').html(str); 
		   $(this).addClass('frist_show').siblings().removeClass('frist_show');
		   $('#section_change').css('height','920px');
		   
	   })*/
	   
	    /*点击地址进入定位页面*/
	   $('#last_loca').click(function(){
		   $('#section_change').css('width','100%');
		   $('.notice_show').css('display','none');
		   $('.query_btn').css('display','none');
		   var str='<iframe src="../location/address_confirm.html" name="iframe_a" scrolling="no"></iframe>';
		   $('#section_change').html(str); 
	   })
	   /*点击地址进入店家详情*/
	   $('.cont_shop').click(function(){
		   var str='<iframe src="./storeCenter.html" name="iframe_a" scrolling="no"></iframe>';
		   $('#section_change').html(str); 
		   $('.query_btn').css('display','none');
		   $('.sec_right').css('display','none');	
		   $('#section_change').css('height','960px');
	   })
	    /*点击跳转首页*/
	    $('#my_page').click(function(){
	    	$(this).addClass('frist_show').siblings().removeClass('frist_show');
	        var initStr='<iframe src="./customer.html" name="iframe_a" scrolling="no"></iframe>';
	   	    $('#section_change').html(initStr);
	   	    $('.query_btn').css('display','block');
	   	    $('.sec_right').css('display','block');	
	   	    $('#section_change').css('height','860px');
	   	    
	    })
	     /*点击跳转我的购物车页面*/
	    $('#my_shopping_car').click(function(){
	    	 $(this).addClass('frist_show').siblings().removeClass('frist_show');
	         var initStr='<iframe src="./shopingCar.html" name="iframe_a" scrolling="no"></iframe>';
	   	     $('#section_change').html(initStr);
	   	     $('.sec_right').css('display','none');	
	   	     $('.query_btn').css('display','none');
	   	     $('#section_change').css('height','760px');
	    })
	    /*点击到联系我们*/
	    $('#contact_us').click(function(){	    
	        var str='<iframe src="../ownShare/connect.html" name="iframe_a" scrolling="no"></iframe>';
	   	    $('#section_change').html(str);
	   	    //隐藏不需要的
	   	    $('.query_btn').css('display','none');
	   	    $('.sec_right').css('display','none');	 
	   	    $(this).addClass('frist_show').siblings().removeClass('frist_show');
	   	    $('#section_change').css({'height':'860px','width':'100%','margin-left':'0px'});
	    })
	   /* $('footer').css('margin-top','-5px');  */  
   }
	changePage();
}
//iframe的高度
function setIframeHeight(iframe){
    if(iframe){
        var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
        if(iframeWin.document.body){
            iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
        }
    }
}
 
 