$(function(){

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
      alert('跳到个人中心');
      
   })
   $('#my_coll').click(function(){  //我的收藏
   	  alert('跳到我的收藏')
   })
   $('#my_adress').click(function(){ //我的地址
   	  alert('跳到我的地址')
   })
   $('#sign_out').click(function(){  //退出登录
   	  alert('跳到登录页面')
   })
   /*商家分类 划过变颜色*/

   $('.sec_hotel_type .type_cont ul li').mouseover(function(){
        //alert($(this).index());
        var index=$(this).index()+1;
        $(this).find('.img_list img').attr('src','./images/bus_type/food_'+index+'_1.png');
       //$(this).addClass('first_list');
   })
   $('.sec_hotel_type .type_cont ul li').mouseout(function(){
        var index=$(this).index()+1;
        $(this).find('.img_list img').attr('src','./images/bus_type/food_'+index+'.png');
        $('.sec_hotel_type .type_cont ul li').eq(0).find('.img_list img').attr('src','./images/bus_type/food_1_1.png');
       //$(this).removeClass('first_list');
   })
   /*商家分类 点击变颜色及事件*/
   $('.sec_hotel_type .type_cont ul li').click(function(){

   })
   /*点击商家进入店家详细信息*/
   function changePage(){
	   var str='<iframe src="./storeDetails.html" name="iframe_a" scrolling="no"></iframe>';
	    $('#section_change').html(str);
	    $('footer').css('margin-top','-5px');
   }
   changePage();
   
})