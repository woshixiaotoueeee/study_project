//骑手js代码
var rider;
$(function(){
   //初始化骑手个人信息设置
   init_rider();
   //修改密码
   edit_password();
   //修改基础信息弹窗
   edit_rider_infor();
})
function init_rider(){
	$.ajax({
		   type: "post",
		   data:null,
		   url:Common.getRiderLoginInfo,
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   rider=data.responseInfo;
				   setRidertoHtml(rider);
			   }
			   else{
				   layer.msg(data.responseInfo, {time:2500});
				   top.location.href=projectDirectory+"/Login/login.html";
			   }
		   },
		   error:function(errordate){}
		})
}
function setRidertoHtml(_rider){
	
	$('#account').html(_rider.riderUser.userAccount);
	$('#customerNickname').html(_rider.riderName);
	$('#sex').html(_rider.riderUser.userSex);
	$('#phone').html(_rider.riderUser.userPhone);
	$('#email').html(_rider.riderUser.userEmail);

	var _stateId=_rider.riderState.stateId;
	if(_stateId==130001){
		$('#stateInfo').html("上班");
	}else{
		$('#stateInfo').html("下班");
	}
}



function edit_password(){ //修改密码
	
	$("#edit_password").unbind("click"); 
   $('#edit_password').click(function(){
		var str=`<div class='edit_password'>
		    <div class='lay_infor'>
			   <span>原密码</span>
			   <input type='password' id='old_password' placeholder="请输入原密码"/>	
		    </div>	
		    <div class='lay_infor'>
			   <span>新密码</span>
			   <input type='password' id='new_password' placeholder="请输入新密码"/>
		    </div>
		    <div class='lay_infor'>
			   <span>确认密码</span>
			   <input type='password' id='_new_password' placeholder="请再次确认密码"/>	
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
			var data={};
			data.userId=rider.riderUser.userId;
			data.oldPassword=$('#old_password').val();
			data.newPassword=$('#new_password').val();
			data._newPassword=$('#_new_password').val();
			$.ajax({
				   type: "post",
				   data:data,
				   url:Common.updatePasswordByUserId,
				   dataType: "json",
				   success:function(data){
						layer.msg(data.responseInfo, {time:2500});
						layer.close(layer_password);
				   },
				   error:function(errordate){
					   layer.msg('未知错误，请联系管理员', {time:2500});
					   layer.close(layer_password);
				   }
			})
	    })
	    $('#edit_cancel').click(function(){
	   	    $('.lay_infor').find('input[type="password"]').val('');
	   	 layer.close(layer_password);
	    })
	})
}

function edit_rider_infor(){ //修改基础信息
	$("#edit_infor").unbind("click"); 
	$('#edit_infor').click(function(){
		
		var strEdit=`<form id="riderForm" name="riderForm" >
	       <div class='edit_name'>
			    <div class='lay_infor'>
				   <span>姓名</span>
				   <input type='text' id='riderName' placeholder="请输入姓名" value="#riderName#"/>	
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
				   <input type='text' id='riderPhone' placeholder="请输入联系方式" value="#riderPhone#"/>	
			    </div>
			    <div class='lay_infor'>
				   <span>我的邮箱</span>
				   <input type='text' id='riderEmail' placeholder="请绑定您的邮箱" value="#riderEmail#"/>	
			    </div>	   
			    <div class='lay_infor save_cancel'>
				    <input type='button' class='edit_save' id='rider_save' value='保存'/>
				    <input type='button' class='edit_cancel' id='rider_cancel' value='取消'/>
			    </div>			   
			</div>
		</form>`;
		 
		strEdit=strEdit.replace("#riderName#",rider.riderName);
		strEdit=strEdit.replace("#riderPhone#",rider.riderUser.userPhone);
		strEdit=strEdit.replace("#riderEmail#",rider.riderUser.userEmail);
		
		
		
		var layer_infor=layer.open({
		  title: ['修改信息', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '380px'], //宽高
		  content: strEdit
		});
		
		if(rider.riderUser.userSex=='男'){
			$('.sex_infor input:radio[name="sex"][value="男"]').attr("checked","checked");	
		}
		else{
			$('.sex_infor input:radio[name="sex"][value="女"]').attr("checked","checked");
			
		}
		rider_save_cancel(layer_infor); //骑手信息设置的保存取消函数
	})
	
	
}
function rider_save_cancel(layer_infor){ //骑手信息设置的保存取消函数
   //点击保存按钮事件
   $('#rider_save').click(function(){  
     var riderdata={};
     
     riderdata.riderName=$('#riderName').val();
     riderdata.userEmail=$('#riderEmail').val();
     riderdata.userPhone=$('#riderPhone').val();
     riderdata.userSex=$('.sex_infor').find(' input[name="sex"]:checked ').val();
	   
     $.ajax({
		   type: "post",
		   data:riderdata,
		   url:Common.updateRider,
		   dataType: "json",
		   success:function(data){
				layer.msg(data.responseInfo, {time:2500});
				layer.close(layer_infor);
				init_rider();
		   },
		   error:function(errordate){
			   layer.msg('未知错误，请联系管理员', {time:2500});
			   layer.close(layer_infor);
		   }
	})
      
   })
   //点击取消按钮事件
   $('#rider_cancel').click(function(){ 
	   layer.close(layer_infor);
   })

}