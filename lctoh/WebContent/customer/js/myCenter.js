$(function(){
	    /* 点击编辑头像*/
		$('#edit_portrait').click(function(){
			edit_portrait();
		})	
		/* 点击编辑姓名*/
		$('#edit_name').click(function(){
			edit_name_eail();
		})
		/* 点击编辑eamil*/
		$('#edit_email').click(function(){
			edit_name_eail();	
		})	
		/* 点击修改eamil*/
		$('#edit_password').click(function(){
			edit_password();
		})
	   //我的地址  新增地址 修改地址
	   $('#add_new').click(function(){
		   edit_address();
	   })	   
	   $('.modify_del span').click(function(){
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
})
  /*编辑头像*/
function edit_portrait() {
	var str=`<div class='portrait_lay'>
	    <div class='lay_infor'>
	        <div class='pic_img'>
	             <img src='./images/portrait/tou.png'/>
		    </div>
	    </div>
	    <div class='lay_infor'>
	    	<div class='upload_img'>
	    	  <span>上传图片</span>
	    	  <form>
	    	     <input type="file" name="pic" id="pic" accept="image/gif,image/jpeg,image/.png" />
	    	  </form>	
	    	 </div>
	    </div>
	    <div class='lay_infor'>
		    <input type='button' class='pic_save' value='保存图像'/>
		    <input type='button' value='取消'/>
	    </div>	    
	</div>`;
	layer.open({
	  title:['编辑头像', 'font-size:18px;'],
	  type: 1,
	  area: ['500px', '450px'], //宽高
	  content: str
	});
 }
/*编辑修改个人信息*/
function edit_name_eail(){
	var strEdit=`<div class='edit_name'>
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
		   <span>手机号码</span>
		   <input type='text' placeholder="18770811902"/>	
	    </div>
	    <div class='lay_infor'>
		   <span>我的邮箱</span>
		   <input type='text' placeholder="请绑定您的邮箱"/>	
	    </div>
	    <div class='lay_infor save_cancel'>
		    <input type='button' class='edit_save' value='保存'/>
		    <input type='button' class='edit_cancel' value='取消'/>
	    </div>			   
	</div>`;
	layer.open({
		  title: ['修改资料', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '400px'], //宽高
		  content: strEdit
		});	
}
/*编辑修改密码*/
function edit_password(){
	var str=`<div class='edit_password'>
		    <div class='lay_infor'>
			    <span>原密码</span>
			    <input type='password' placeholder=""/>	
		    </div>	
		    <div class='lay_infor'>
			   <span>新密码</span>
			   <input type='password' placeholder=""/>
		    </div>
		    <div class='lay_infor'>
			   <span>确认密码</span>
			   <input type='password' placeholder=""/>	
		    </div>
		    <div class='lay_infor save_cancel'>
			    <input type='button' class='edit_save' value='保存'/>
			    <input type='button' class='edit_cancel' value='取消'/>
		    </div>	
		</div>`;
		layer.open({
		  title:['修改密码', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '360px'], //宽高
		  content: str
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
		  area: ['800px', '600px'],
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