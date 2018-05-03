//管理员基础信息设置代码
var admin=null;
$(function(){
   //初始化管理员个人信息设置
   init_admin();
   //修改密码
   edit_password();
   //修改基础信息弹窗
   edit_admin_infor();
})
function init_admin(){
   //初始化数据
   if(admin==null)return;
   /* 获取管理员信息*/
   $.ajax({
	   type: "post",
	   data:null,
	   url:Common.getRiderLoginInfo,
	   dataType: "json",
	   success:function(data){
		   if(data.state==1){
			   rider=data.responseInfo;
			   setAdmintoHtml(rider);
		   }
		   else{
			  /* layer.msg(data.responseInfo, {time:2500});
			   top.location.href=projectDirectory+"/Login/login.html";*/
		   }
	   },
	   error:function(errordate){}
	})
	
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
		  area: ['544px', '340px'], //宽高
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
function edit_admin_infor(){ //修改基础信息
   var strEdit=`<form id="adminForm" name="adminForm" >
       <div class='edit_name'>
		    <div class='lay_infor'>
			   <span>姓名</span>
			   <input type='text' placeholder="刘贵平" value='刘贵平'/>	
		    </div>	
		    <div class='lay_infor sex_infor'>
			   <span>性别</span>
			   <div class='edit_sex'>
				   <input type='radio' class='sex' name='sex' value='' checked/>
				   <span>先生</span>
				   <input type='radio' class='sex' name='sex' value=''/>
				   <span>女士</span>
			   </div>  
		    </div>		     
		    <div class='lay_infor'>
			   <span>手机号码</span>
			   <input type='text' placeholder="" value='18770811902'/>	
		    </div>
		    <div class='lay_infor'>
			   <span>我的邮箱</span>
			   <input type='text' placeholder="请绑定您的邮箱"/>	
		    </div>
		    <div class='lay_infor save_cancel'>
			    <input type='button' class='edit_save' id='admin_save' value='保存'/>
			    <input type='button' class='edit_cancel' id='admin_cancel' value='取消'/>
		    </div>			   
		</div>
	</form>`;
	$('#edit_infor').click(function(){	
		var layer_infor=layer.open({
		  title: ['修改信息', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '380px'], //宽高
		  content: strEdit
		});
		admin_save_cancel(layer_infor); //管理员信息设置的保存取消函数
	})
	
}
function admin_save_cancel(layer_infor){ //管理员信息设置的保存取消函数
   //点击保存按钮事件
   $('#admin_save').click(function(){  
     alert('保存信息');
      layer.close(layer_infor);
   })
   //点击取消按钮事件
   $('#admin_cancel').click(function(){  
   	   //清除表单
       $('#adminForm')[0].reset();
   })

}