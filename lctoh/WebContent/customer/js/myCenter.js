var customer=null;
var addressList=null;
var orderList=null;
var map = new BMap.Map();//初始化地图
$(function(){
	init();
	
})
function init(){
	getLoginInfo();
	getAddressData(customer);
	queryOrder(1);
	initHtml(customer);
	getCollectRestaurant();
	setCustomerToHtml(customer);
}

function getCollectRestaurant(){
	$.ajax({  
		url: Common.getCollectRestaurant,  
		type: 'POST',  
		data: null, 
		dataType: "json",
		success: function (returndata) {  
			if(returndata.state==1){
				setCollectRestaurant(returndata.responseInfo);
			}
			else{
				layer.msg(returndata.responseInfo, {time:2500});
			}
        	 
		},  
		error: function (returndata) {  
        	 layer.msg('未知错误，请刷新页面重试', {time:2500}); 
        	 layer.close(layerIndex);
		}  
    });
}
function setCollectRestaurant(restaurantList){
	var divContent=$(".collect_restaurant");
	divContent.html("");
	var strRes='';
	for(var i=0;i<restaurantList.length;i++){
		strRes+="<div id='"+restaurantList[i].restaurantId+"' class='cont_shop'> <div class='img_shop'> " +
				"<img src='../"+restaurantList[i].restaurantImage+"'> </div>  <div class='shop_same shop_title'>"+restaurantList[i].restaurantName+"</div>" +
				"<div class='shop_same shop_cont'>****共售<span>"+restaurantList[i].orderCount+"份</span> </div>" +
				"<div class='shop_same shop_bot' > <ul> <li> 起送：<span>"+restaurantList[i].restaurantOfferPrice+"￥</span></li> <li> 配送费：<span>"+restaurantList[i].restaurantDeliveryFee+"￥</span></li>" +
				"<li><span>30分钟</span></li> </ul></div> </div>";
	}

	divContent.html(strRes);
	
	$('.cont_shop').click(function(){
		var rtid=$(this)["0"].id;
		var str='<iframe src="./storeCenter.html?rtid='+rtid+'" name="iframe_a" scrolling="no"></iframe>';
		
		$('#section_change').html(str); 
		$('footer').css('margin-top','-5px'); 
	})
}


function initHtml(_customer){
	if(_customer==null)return;
	
    /* 点击编辑头像*/
	$("#edit_portrait").unbind("click"); 
	$('#edit_portrait').click(function(){
		edit_portrait(_customer);
	})	
	/* 点击编辑个人资料*/
	$("#edit_infor").unbind("click"); 
	$('#edit_infor').click(function(){
		edit_name_eail(_customer);	
	})	
	/* 点击修改密码*/
	$("#edit_password").unbind("click"); 
	$('#edit_password').click(function(){
		edit_password(_customer);
	})
	//我的地址  新增地址 
	$("#add_new").unbind("click"); 
	$('#add_new').click(function(){
	   add_address();
	})
	// 修改地址
	$(".modify_address").unbind("click"); 
	$('.modify_address').click(function(){
	   edit_address();
	   
	})
	// 删除地址
	$(".delete_address").unbind("click"); 
	$('.delete_address').click(function(){
	   delete_address(this);
	})
	/*点击显示订单详情*/
	$(".my_order .ord_click input").unbind("click"); 
	$('.my_order .ord_click input').click(function(){
	    order_detail( $(this).attr('id'));
	})
	/*订单统计过程线图*/
	order_graph();
	/*个人中心 我的收藏 我的订单 我的订单切换*/ 
	my_center_list();
	$(".query").unbind("click"); 
	$('.query').click(function(){
	   queryOrder( $(this).attr('value'));
	})
   
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
	        	 getLoginInfo();
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
			   <input type='radio' class='sex' name='sex' value='男' checked/>
			   <span>先生</span>
			   <input type='radio' class='sex' value='女' name='sex'/>
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
	str=str.replace(/#phone#/g,_customer.customerUser.userPhone);
	str=str.replace("#email#",_customer.customerUser.userEmail);
	//性别			_customer.customerUser.userSex
	_customer.customerUser.userSex='女';
	str=str.replace("#email#",_customer.customerUser.userEmail);
	var layerIndex=layer.open({
		  title: ['修改资料', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '400px'], //宽高
		  content: str
		});	
	if(_customer.customerUser.userSex=='男'){
		$('.edit_sex input:radio[name="sex"][value="男"]').attr("checked","checked");	
	}
	else{
		$('.edit_sex input:radio[name="sex"][value="女"]').attr("checked","checked");
		
	}
	
	
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
		var sexVal=$('.edit_sex').find(' input[name="sex"]:checked ').val();
        //alert(sexVal);
		updateData.userSex=sexVal;//$('#customerNickname').val();
	    $.ajax({  
	         url: Common.updateCustomer,  
	         type: 'POST',  
	         data: updateData, 
	         dataType: "json",
	         success: function (returndata) {  
	        	 layer.msg(returndata.responseInfo, {time:2500});
	        	 layer.close(layerIndex);
	        	 getLoginInfo();
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
		        	 getLoginInfo();
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
/*编辑修改地址*/
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
		  title: ['修改地址', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '480px'], //宽高
		  content: add_new
		});	 	 
}
/*添加新地址*/
function add_address(){
	 var add_new=`<div class='edit_name edit_address'>
		    <div class='lay_infor'>
			   <span>姓名</span>
			   <input type='text' placeholder="请输入您的姓名"/>	
		    </div>	
		    <div class='lay_infor'>
			   <span>位置</span>
			   <input type="text" id="sel_address" />
			   <div id="searchResultPanel11" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
		    </div>
		    <div class='lay_infor'>
			   <span>详细地址</span>
			   <input type='text' />	
		    </div>
		    <div class='lay_infor'>
			   <span>手机号码</span>
			   <input type='text' placeholder="请输入您的手机号码"/>	
		    </div>	
		    <div id="r-result">请输入:<input type="text" id="suggestId" size="20" value="1111111" style="width:150px;" /></div>
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
	 var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
				{"input" : "suggestId"
				,"location" : map
			});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		//$("#searchResultPanel").html(str);
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		//$("#searchResultPanel").html("onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue);
		//setPlace();
	});
}
/*删除地址*/
function delete_address(_this){
	aler(_this);
	layer.open({
		  title: ['删除地址', 'font-size:14px;'],
		  content: '是否确认删除？'
		  ,btn: ['确认',  '取消']
		  ,yes: function(index, layero){
		    //按钮【按钮一】的回调
		  }
		  ,cancel: function(){ 
		    //右上角关闭回调
		  }
	 });
}
/*订单详情*/
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
/*订单过程线*/
function order_graph(){
	$('.pic_line').mouseover(function(){
	 	  $(this).find('img').attr('src','./images/my_order/tubiao_1.png');
	 })
	 $('.pic_line').mouseout(function(){
	 	  $(this).find('img').attr('src','./images/my_order/tubiao.png');
	 })
	 $('.pic_line').unbind("click"); 
	 $('.pic_line').click(function(){
	      layer.open({
			  type: 2,
			  title: ['订单统计图表','font-size:18px;'],
			  shadeClose: true,
			  shade: 0.8,
			  area: ['780px', '500px'],
			  content: 'orderGraph.html?customerId='+customer.customerId  //iframe的url
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
function getLoginInfo(){
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
 * 获取客户地址信息
 * */
function getAddressData(_customer){
	if(_customer==null)return;
	$.ajax({
	   type: "post",
	   url:Common.findAddressByCustomerId,
	  // async: false,
	   data: {'customerId':_customer.customerId}, 
	   dataType: "json",
	   success:function(data){
		   if(data.state==1){
			   addressList=data.responseInfo;
			   setAddressListToHtml(addressList);
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
//查询
function queryOrder(type){
	
	var querydata={};
	var stm,etm;
	var nowdate=new Date();
	etm=dateFtt("yyyy-MM-dd hh:mm:ss",nowdate);
	if(type==1){
		stm=dateFtt("yyyy-MM-dd hh:mm:ss",new Date(nowdate.toLocaleDateString()));
	}else if(type==2){
		var timerLong=nowdate.getTime;
		timerLong=timerLong-1000*60*60*24*30;
		stm=dateFtt("yyyy-MM-dd hh:mm:ss",new Date(timerLong));
	}else if(type==3){
		var timerLong=nowdate.getTime;
		timerLong=timerLong-1000*60*60*24*30*3;
		stm=dateFtt("yyyy-MM-dd hh:mm:ss",new Date(timerLong));
	}
	querydata.stm=stm;
	querydata.etm=etm;
	querydata.customerId=customer.customerId;
	
	getOrderData(querydata);
	
}







/**
 * 获取客户订单信息
 * */
function getOrderData(querydata){
	$.ajax({
	   type: "post",
	   url:Common.findOrder,
	  // async: false,
	   data:querydata, 
	   dataType: "json",
	   success:function(data){
		   if(data.state==1){
			   orderList=data.responseInfo;
			   setOrderListToHtml(orderList);
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

/**
 * 设置地址信息
 * */
function setAddressListToHtml(_addressList){
	/*
	if(_addressList==null)return;
	var addressHtml='';
	
	
	addressHtml+="<div class='address_one address_new'>" +
			"<div  id='add_new'><div class='ck_img'>+</div><div class='add_new'>添加新地址</div></div>"+
			"</div>";
	//var addressDiv=
	$(".my_address .edit_cont .edit_infor").html(addressHtml);
	
	*/
}


/**
 * 设置订单信息
 * */
function setOrderListToHtml(_orderList){
	if(_orderList==null)return;
	var orderHtml='';
	
	
	$(".order_detaile").html('');
	
	
	for(var i=0;i<_orderList.length;i++){
		if(i==0){
			orderHtml+="<div class='order_one' ><div class='ord_time'><div class='time_show'>" +
			"<span>"+getDate(_orderList[i].orderCreatTime)+"</span><span>"+getTime(_orderList[i].orderCreatTime)+"</span></div></div>" +
			"<div class='pic_img'></div><div class='border_bot'><div class='ord_cont'>" +
			"<div class='ord_title'>"+_orderList[i].orderRestaurant.restaurantName+"</div><div class='ord_inf'>尖椒肉丝盖浇饭1份 等3个菜品</div>" +
			"<div class='ord_number'><span>订单号：</span><span>"+_orderList[i].orderId+"</span></div>  </div><div class='ord_state'> " +
			"<span>"+_orderList[i].orderState.stateInfo+"</span> </div><div class='ord_money'> <span>"+_orderList[i].orderPrice+"元</span>" +
			"<span>在线支付</span></div><div class='ord_click' ><input class='first_ord order_button' id='"+_orderList[i].orderId+"' type='butto' name='' value='订单详情'></div></div></div>";
		}
		else{
			orderHtml+="<div class='order_one' ><div class='ord_time'><div class='time_show'>" +
			"<span>"+getDate(_orderList[i].orderCreatTime)+"</span><span>"+getTime(_orderList[i].orderCreatTime)+"</span></div></div>" +
			"<div class='pic_img'></div><div class='border_bot'><div class='ord_cont'>" +
			"<div class='ord_title'>"+_orderList[i].orderRestaurant.restaurantName+"</div><div class='ord_inf'>尖椒肉丝盖浇饭1份 等3个菜品</div>" +
			"<div class='ord_number'><span>订单号：</span><span>"+_orderList[i].orderId+"</span></div>  </div><div class='ord_state'> " +
			"<span>"+_orderList[i].orderState.stateInfo+"</span> </div><div class='ord_money'> <span>"+_orderList[i].orderPrice+"元</span>" +
			"<span>在线支付</span></div><div class='ord_click' ><input class='order_button' id='"+_orderList[i].orderId+"' type='butto' name='' value='订单详情'></div></div></div>";
		}
		
	}
	$(".order_detaile").html(orderHtml);
	initHtml(customer);
}

