//骑手首页
$(function(){
     init();//初始化  渲染html
     jump_click(); //跳转链接
})
function init(){
   
   //点击换颜色(首页 统计管理 )
   $('.header-wrap .nav ul li').click(function(){
    alert($(this).index());
   	   $(this).addClass('frist_show').siblings().removeClass('frist_show');
   })

}
function jump_click(){

    //点击首页跳转到首页页面
	 $('#home_page').click(function(){
   	   $('#iframe_a').attr('src','./riderView.html');
  	})
  	//点击统计管理跳转到统计管理页面
   	$('#comp_manage').click(function(){
   	     $('#iframe_a').attr('src','./summaryView.html');
  	})
 	  //点击联系我们跳转到联系我们页面
  	$('#contact_us').click(function(){
  	    $('#iframe_a').attr('src','../ownShare/connect.html');   
         
  	})
    //点击个人信息跳转到设置查看个人信息页面
    $('#myDataInfor').click(function(){
       alert('个人信息');
        $('#iframe_a').attr('src','./setInfor.html');
    })
    //点击退出登录跳转到登录页面
    $('#signOut').click(function(){

    })
   
}