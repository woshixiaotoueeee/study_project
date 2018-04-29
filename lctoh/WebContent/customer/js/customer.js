$(function(){
	loadRestaurantHtml();
})
function loadRestaurantHtml(){
    
	/*商家分类 划过变颜色*/
	/*$('.sec_hotel_type .type_cont ul li').mouseover(function(){
	
		//var index=$(this).index()+1;
		//$(this).find('.img_list img').attr('src','./images/bus_type/food_'+index+'_1.png');
		//$(this).addClass('first_list');
   })
   $('.sec_hotel_type .type_cont ul li').mouseleave(function(){
	   //var index=$(this).index()+1;
	  // $(this).find('.img_list img').attr('src','./images/bus_type/food_'+index+'.png');
	   //$(this).removeClass('first_list');
   })*/
   $('.sec_hotel_type .type_cont ul li').mousedown(function(){	
	         
	   $(this).addClass('first_list').siblings().removeClass('first_list');
   })
   $('.sec_hotel_type .type_cont ul li').mouseup(function(){
	       
	   $(this).addClass('first_list').siblings().removeClass('first_list');
   })

   /*商家分类 点击变颜色及事件*/
   /* $('.sec_hotel_type .type_cont ul li').click(function(){
     
   })*/
   /*跳转页面设置*/
   function changePage(){
		   /*点击地址进入店家详情*/
	   $('.cont_shop').click(function(){
		   var rtid=$(this).attr("value");
		   var str='<iframe src="./storeCenter.html?rtid='+rtid+'" name="iframe_a" scrolling="no"></iframe>';
		   $('#section_change').html(str); 
		   $('footer').css('margin-top','-5px'); 
	   })
	    /*点击地址进入定位页面*/
	   $("#last_loca").unbind("click"); 
	   $('#last_loca').click(function(){
		   
		  /* var str='<iframe src="../location/address_confirm.html" name="iframe_a" scrolling="no"></iframe>';
		   $('#section_change').html(str); */
		   
		   var str='../location/address_confirm.html';
		    layer.open({
				  title:0,
				  type: 2,
				  shadeClose: true,
				  shade: 0.2,
				  area: ['100%', '100%'],
				  content: str  //iframe的url
			 }); 
	   })   
   }
   /*点击地址进入店家详情*/
   changePage();
   /* 点击店家分类获取店家的接口   （参数  城市 city 店家分类id ）*/
  /* $('#all_shop').click(function(){
	   $.ajax({
		   type: "post",
		   url:Common.getRestaurant,
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   alert(99);
				   alert(data);
			   }
			   else{
				   alert('访问后台失败');
			   }	        
		   },
		   error:function(errordate){
			   alert('未知错误请联系管理员');
		   }
	   }) 
   })  */
   /* /UserController/loginbasic 登录地址*/
}
 
 