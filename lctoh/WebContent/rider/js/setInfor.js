//骑手js代码
$(function(){
   //初始化骑手个人信息设置
   init_rider();
   //修改密码
   edit_password();
   //修改基础信息弹窗
   edit_rider_infor();
})
function init_rider(){
   //初始化数据
}
function edit_password(){ //修改密码
   $('#edit_password').click(function(){
		var str=`<div class='edit_password'>
		    <div class='lay_infor'>
			   <span>原密码</span>
			   <input type='password' placeholder="请输入原密码"/>	
		    </div>	
		    <div class='lay_infor'>
			   <span>新密码</span>
			   <input type='password' placeholder="请输入新密码"/>
		    </div>
		    <div class='lay_infor'>
			   <span>确认密码</span>
			   <input type='password' placeholder="请再次确认密码"/>	
		    </div>
		    <div class='lay_infor save_cancel'>
			    <input type='button' class='edit_save' id='edit_save' value='保存'/>
			    <input type='button' class='edit_cancel' id='edit_cancel' value='取消'/>
		    </div>	
		</div>`;
		var layer_password=layer.open({
		  title:['修改密码', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '360px'], //宽高
		  content: str
		});
		$('#edit_save').click(function(){
	   	    save_password();
            layer.close(layer_password);
	    })
	    $('#edit_cancel').click(function(){
	   	    alert('取消密码修改');
	   	    $('.lay_infor').find('input[type="password"]').val('');
	    })
	})
}
function save_password(){ //修改密码保存函数
    alert('设置密码成功')
}
function edit_rider_infor(){ //修改基础信息
   var strEdit=`<form id="riderForm" name="riderForm" >
       <div class='edit_name'>
		    <div class='lay_infor'>
			   <span>姓名</span>
			   <input type='text' placeholder="刘贵平"/>	
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
			    <input type='button' class='edit_save' id='rider_save' value='保存'/>
			    <input type='button' class='edit_cancel' id='rider_cancel' value='取消'/>
		    </div>			   
		</div>
	</form>`;
	 /*<div class='lay_infor state_infor'>
	   <span>状态</span>
	   <div class='edit_state'>
		   <input type='radio' class='state' name='state' checked/>
		   <span>上班</span>
		   <input type='radio' class='state' name='state'/>
		   <span>下班</span>
	   </div>  
	 </div>*/
	
	
	$('#edit_infor').click(function(){	
		var layer_infor=layer.open({
		  title: ['修改信息', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '380px'], //宽高
		  content: strEdit
		});
		rider_save_cancel(layer_infor); //骑手信息设置的保存取消函数
	})
	
}
function rider_save_cancel(layer_infor){ //骑手信息设置的保存取消函数
   //点击保存按钮事件
   $('#rider_save').click(function(){  
     alert('保存信息');
      layer.close(layer_infor);
   })
   //点击取消按钮事件
   $('#rider_cancel').click(function(){  
   	   alert($('#riderForm'));
       $('#riderForm')[0].reset();
   })

}