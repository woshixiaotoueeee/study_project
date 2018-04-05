$(function(){

	$('#edit_portrait').click(function(){
		var str=`<div class='portrait_lay'>
		    <div class='lay_infor'>
		        <div class='pic_img'>
	                 <img src='./images/portrait/tou.png'/>
			    </div>
		    </div>
		    <div class='lay_infor'>
		    	<div class='upload_img'>上传图片</div>
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

	})
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
	$('#edit_name').click(function(){
		
		layer.open({
		  title: ['修改资料', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '400px'], //宽高
		  content: strEdit
		});
	})
	$('#edit_password').click(function(){
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
	})
	$('#edit_email').click(function(){
		var str=`<div class='portrait_lay'>
		    
		</div>`;
		layer.open({
		  title:'修改资料',
		  type: 1,
		  area:['544px', '420px'], //宽高
		  content:strEdit
		});
	})
   //我的地址  新增地址 修改地址
   $('#add_new').click(function(){
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
   })
   /*订单详情*/
   $('.my_order .ord_click input').click(function(){
       var str='<div>订单详情</div>';
       layer.open({
		  title: ['订单详情', 'font-size:18px;'],
		  type: 1,
		  area: ['780px', '600px'], //宽高
		  content: str
		});
   })
   /*订单统计过程线图*/
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
   
   /*个人中心 我的收藏 我的订单 我的订单切换*/
    $('.infor_left ul li').mouseover(function(){
    	$(this).find('>span').css('display','block');
        $(this).siblings().find('>span').css('display','none');
    })
    $('.infor_left ul li').click(function(){  //点击切换
    	var index=$(this).index();
    	if(index==0){
    		$('.infor_left ul li').eq(0).css('color','#dddbdb');
    	}
    	else{
    		$('.infor_left ul li').eq(0).css('color','#333');
    	}
        $(this).find('>span').css('display','block');
        $(this).siblings().find('>span').css('display','none');

        $('.center_cont').eq(index).css('display','block').siblings().css('display','none');
        //$('.span_left').eq(index).css('display','block').siblings().css('display','none');
    })
    
   /*个人中心 我的收藏 我的订单 我的订单切换*/
	  
})