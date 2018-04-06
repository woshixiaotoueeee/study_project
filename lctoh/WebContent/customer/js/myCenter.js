var customer=null;
$(function(){
	init();
	
})
function init(){
	getRestaurantCategoryData();
	initHtml(customer);
	setCustomerToHtml(customer);
}
function initHtml(_customer){
	if(_customer==null)return;
    /* 点击编辑头像*/
	$('#edit_portrait').click(function(){
		edit_portrait(_customer);
	})	
	/* 点击编辑个人资料*/
	$('#edit_infor').click(function(){
		edit_name_eail(_customer);	
	})	
	/* 点击修改密码*/
	$('#edit_password').click(function(){
		edit_password(_customer);
	})
   //我的地址  新增地址 
   $('#add_new').click(function(){
	   edit_address();
   })
   // 修改地址
   $('.modify_del span').eq(0).click(function(){
	   edit_address();
   })
   /*点击显示订单详情*/
   $('.my_order .ord_click input').click(function(){
	    order_detail();
   })
   /*订单统计过程线图*/
   order_graph();
   /*个人中心 我的收藏 我的订单 我的订单切换*/ 
   my_center_list();
}
  /*编辑头像*/
function edit_portrait(_customer) {
	var str=`<div class='portrait_lay'>
	    <div class='lay_infor'>
	        <div class='pic_img'>
	             <img src='#portrait#'/>
		    </div>
	    </div>
	    <div class='lay_infor'>
	    	<div class='upload_img'>
	    	  <span>上传图片</span>
	    	  <form id='updatePortrait'>
	    	     <input type="file" name="file" id="file" accept="image/gif,image/jpeg,image/.png" />
	    	  </form>	
	    	 </div>
	    </div>
	    <div class='lay_infor'>
		    <input type='button' class='pic_save' value='保存图像'/>
		    <input type='button' class='pic_cancel' value='取消'/>
	    </div>	    
	</div>`;
	
	str=str.replace("#portrait#",'../'+_customer.customerPortrait);
	var layerIndex=layer.open({
	  title:['编辑头像', 'font-size:18px;'],
	  type: 1,
	  area: ['500px', '450px'], //宽高
	  content: str
	});
	$(".pic_save").click(function(){
		if($('#file').val().length==0){
			layer.msg('请选择文件', {time:2500}); 
			return;
		}
		var image = new FormData($("#updatePortrait")[0]);  
	    $.ajax({  
	         url: Common.updateCustomerPortrait,  
	         type: 'POST',  
	         data: image, 
	         dataType: "json",
	         cache: false,  
	         contentType: false,  
	         processData: false, 
	         success: function (returndata) {  
	        	 layer.msg(returndata.responseInfo, {time:2500});
	        	 layer.close(layerIndex);
	        	 getRestaurantCategoryData();
	        	 setCustomerToHtml(customer);
	        	 
	         },  
	         error: function (returndata) {  
	        	 layer.msg('文件过大或文件格式不对', {time:2500}); 
	        	 layer.close(layerIndex);
	         }  
	    });
	});
	$(".pic_cancel").click(function(){
		layer.close(layerIndex);
	});
	
 }
/*编辑修改个人信息*/
function edit_name_eail(_customer){
	var strEdit=`<div class='edit_name'>
	    <div class='lay_infor'>
		   <span>昵称</span>
		   <input id='upcustomerNickname' type='text' placeholder="请输入您的昵称" value='#customerNickname#'/>	
	    </div>	
	    <div class='lay_infor sex_infor'>
		   <span>性别</span>
		   <div class='edit_sex'>
			   <input type='radio' class='sex' name='sex' checked/>
			   <span>先生</span>
			   <input type='radio' class='sex' name='sex'/>
			   <span>女士</span>
		   </div>  
	    </div>
	    <div class='lay_infor'>
		   <span>手机号码</span>
		   <input id='upphone' type='text' placeholder="#phone#" value="#phone#"/>	
	    </div>
	    <div class='lay_infor'>
		   <span>我的邮箱</span>
		   <input id='upemail' type='text' placeholder="#email#" value="#email#"/>	
	    </div>
	    <div class='lay_infor save_cancel'>
		    <input type='button' class='edit_save' value='保存'/>
		    <input type='button' class='edit_cancel' value='取消'/>
	    </div>			   
	</div>`;
	//补全信息
	var str=strEdit.replace("#customerNickname#",_customer.customerNickname);
	str=str.replace("#phone#",_customer.customerUser.userPhone);
	str=str.replace("#email#",_customer.customerUser.userEmail);
	//性别			_customer.customerUser.userSex
	str=str.replace("#email#",_customer.customerUser.userEmail);
	var layerIndex=layer.open({
		  title: ['修改资料', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '400px'], //宽高
		  content: str
		});	
	
	
	
	/* <pre>
	 * _user说明{
	 * 		userEmail String 字符串  邮箱
	 * 		userPhone String 字符串 联系方式
	 * 		userSex String 字符串 性别
	 * }
	 * </pre>
	 * @param nickName String 字符串 昵称
	 */
	
	$(".edit_save").click(function(){
		var updateData={};
		updateData.nickName=$('#upcustomerNickname').val();
		updateData.userEmail=$('#upemail').val();
		updateData.userPhone=$('#upphone').val();
		//性别
		updateData.userSex='男';//$('#customerNickname').val();
	    $.ajax({  
	         url: Common.updateCustomer,  
	         type: 'POST',  
	         data: updateData, 
	         dataType: "json",
	         success: function (returndata) {  
	        	 layer.msg(returndata.responseInfo, {time:2500});
	        	 layer.close(layerIndex);
	        	 getRestaurantCategoryData();
	        	 setCustomerToHtml(customer);
	         },  
	         error: function (returndata) {  
	        	 layer.msg('未知错误请刷新页面重试', {time:2500}); 
	        	 layer.close(layerIndex);
	         }  
	    });
	});
	
	$(".edit_cancel").click(function(){
		layer.close(layerIndex);
	});
}
/*编辑修改密码*/
function edit_password(_customer){
	var str=`<div class='edit_password'>
		    <div class='lay_infor'>
			    <span>原密码</span>
			    <input id='oldpwd' type='password' placeholder=""/>	
		    </div>	
		    <div class='lay_infor'>
			   <span>新密码</span>
			   <input id='newpwd' type='password' placeholder=""/>
		    </div>
		    <div class='lay_infor'>
			   <span>确认密码</span>
			   <input id='_newpwd' type='password' placeholder=""/>	
		    </div>
		    <div class='lay_infor save_cancel'>
			    <input type='button' class='edit_save' value='保存'/>
			    <input type='button' class='edit_cancel' value='取消'/>
		    </div>	
		</div>`;
		var layerIndex=layer.open({
		  title:['修改密码', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '360px'], //宽高
		  content: str
		});
		
		
		$(".edit_save").click(function(){
			var pwd={};
			pwd.oldPassword=$('#oldpwd').val();
			pwd.newPassword=$('#newpwd').val();
			pwd._newPassword=$('#_newpwd').val();
			pwd.userId=_customer.customerUser.userId;
			
		    $.ajax({  
		         url: Common.updatePasswordByUserId,  
		         type: 'POST',  
		         data: pwd, 
		         dataType: "json",
		         success: function (returndata) {  
		        	 layer.msg(returndata.responseInfo, {time:2500});
		        	 layer.close(layerIndex);
		        	 getRestaurantCategoryData();
		        	 setCustomerToHtml(customer);
		         },  
		         error: function (returndata) {  
		        	 layer.msg('未知错误请刷新页面重试', {time:2500}); 
		        	 layer.close(layerIndex);
		         }  
		    });
		});
		
		$(".edit_cancel").click(function(){
			layer.close(layerIndex);
		});
		
		
}
/*编辑添加地址*/
function edit_address(){
	 var add_new=`<div class='edit_name edit_address'>
		    <div class='lay_infor'>
			   <span>姓名</span>
			   <input type='text' placeholder="请输入您的姓名"/>	
		    </div>	
		    <div class='lay_infor sex_infor'>
			   <span>性别</span>
			   <div class='edit_sex'>
				   <input type='radio' class='sex' name='sex' checked/>
				   <span>先生</span>
				   <input type='radio' class='sex' name='sex'/>
				   <span>女士</span>
			   </div>  
		    </div>
		    <div class='lay_infor'>
			   <span>位置</span>
			   <select id='sel_address'>
				  <option value ="volvo">请输入小区或路名</option>
				  <option value ="saab">Saab</option>
				  <option value="opel">Opel</option>
				  <option value="audi">Audi</option>
			   </select>	
		    </div>
		    <div class='lay_infor'>
			   <span>详细地址</span>
			   <input type='text' />	
		    </div>
		    <div class='lay_infor'>
			   <span>手机号码</span>
			   <input type='text' placeholder="请输入您的手机号码"/>	
		    </div>		    
		    <div class='lay_infor save_cancel'>
			    <input type='button' class='edit_save' value='保存'/>
			    <input type='button' class='edit_cancel' value='取消'/>
		    </div>			   
		</div>`;
	 layer.open({
		  title: ['新添地址', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '480px'], //宽高
		  content: add_new
		});
	 
	 
	 
	 
	 
}
/*订单详情*/
function order_detail(){
	 var str='./orderDetail.html';
     layer.open({
		  title: ['订单详情', 'font-size:18px;'],
		  type: 2,
		  shadeClose: true,
		  shade: 0.8,
		  area: ['800px', '630px'],
		  content: str  //iframe的url
	 }); 
}
/*订单过程线*/
function order_graph(){
	$('.pic_line').mouseover(function(){
	 	  $(this).find('img').attr('src','./images/my_order/tubiao_1.png');
	 })
	 $('.pic_line').mouseout(function(){
	 	  $(this).find('img').attr('src','./images/my_order/tubiao.png');
	 })
	 $('.pic_line').click(function(){
	      layer.open({
			  type: 2,
			  title: ['订单统计图表','font-size:18px;'],
			  shadeClose: true,
			  shade: 0.8,
			  area: ['780px', '500px'],
			  content: 'orderGraph.html'  //iframe的url
			}); 
	 })  
}
function my_center_list(){
	   /*个人中心 我的收藏 我的订单 我的订单切换*/
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
	       $('.span_left').eq(index).css('display','block').siblings().css('display','none');
	       $('.center_cont').eq(index-1).css('display','block').siblings().css('display','none');      
	    })	    
	   /*个人中心 我的收藏 我的订单 我的订单切换*/	 	  
}
function center_tabs(_this){
	   var index=$(_this).index();
	   if(index==0){
	     	  return false;
	       }
	   $(_this).find('>span').css('display','block');
	   $(_this).siblings().find('>span').css('display','none');
	      
}
/**
 * 获取客户信息
 * */
function getRestaurantCategoryData(){
	$.ajax({
	   type: "post",
	   url:Common.getLoginInfo,
	   async: false,
	   dataType: "json",
	   success:function(data){
		   if(data.state==1){
			   customer=data.responseInfo;
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
/**
 * 设置客户信息
 * */
function setCustomerToHtml(_customer){
	
	$(".portrait").find('>img').attr('src','../'+_customer.customerPortrait);
	$("#my_balance").html(_customer.customerBalance.toFixed(2));
	$("#account").html(_customer.customerUser.userAccount);
	$("#customerNickname").html(_customer.customerNickname);
	$("#sex").html(_customer.customerUser.userSex);
	$("#phone").html(_customer.customerUser.userPhone);
	$("#email").html(_customer.customerUser.userEmail);
}