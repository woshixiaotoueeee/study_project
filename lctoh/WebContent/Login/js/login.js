
  //客户登录后页面
 /* 
 var aBtn=document.getElementById('sign_btn');
 aBtn.onclick=function(){
  	alert(99);
  	window.location.href="Customer.html";
  }*/
  /*...........input获取焦点事件........*/
  $('.user').focus(function(){
    	$(this).attr('placeholder','');	
   })
  $('.password').focus(function(){
    	$(this).attr('placeholder','');	
   })
   /* .............正则表达式..............  */
   //账号错误提示  数字0-16位
    var  user_num=/^[1-9]\d{0,16}$/;
    $('.user').change(function(){
      var pNu=$(this).val();
      if(user_num.test(pNu)){
        /*  alert('账号正确');*/
          $('.user_p p').css('display','none');
      }
      else{  
       /* alert('账号不正确');  */   
        $('.user_p p').css('display','block');
      }
    })
   //密码错误提示
    var  password_num=/^\w{6,16}$/;
    $('.password').change(function(){
      var pNu=$(this).val();
      if(password_num.test(pNu)){
          $('.password_p p').css('display','none');
      }
      else{          
        $('.password_p p').css('display','block');
      }
    })
    //...................单选改变样式(字体颜色)。。。。。。。。。。。
    var inpRad=$(".select_user input[name='user']");
/*    $(".select_user input[name='user']").click(function(){
    	 //alert(3);   	
    	alert($(this).val());    	
     });*/
    for(var i=0;i<inpRad.length;i++){
    	inpRad[i].index=i;
        inpRad[i].onclick=function(){ 	
        	$('.select_user span').eq(this.index).css('color','white').siblings().css('color','black');
        }
    }
  /* .............点击选择登录发生的事件..............  */
 /*  $("#sign_btn").click(function(){
            var val=$(".select_user input[name='user']:checked").val();
            //客户1  店家4  管理员2 骑手3
            if(val=='1'){
                alert("登录客户页面");    
              //window.location.href="Customer.html";         
            }
            else if(val=='4'){
                alert('登录店家页面');
            }
            else if(val=='2'){
                alert('登录管理员页面');
            }
            else if(val=='3'){
                alert('登录骑手页面');
            }
            else{
                 alert('错误')
                 return false;
            }

        })*/
    /* .............点击选择登录发生的事件..............  */

    /* .............点击注册发生的事件..............  */
     $("#register").click(function(){
            var val=$(".select_user input[name='user']:checked").val();
            if(val=='客户'){
                alert("进入客户注册页面");             
            }
            else if(val=='店家'){
                alert('进入店家注册页面');
            }
            else if(val=='骑手'){
                alert('进入骑手注册页面');
            }
            else{
                 alert('错误')
                 return false;
            }

        })
    /* .............点击注册发生的事件..............  */
    /* .............点击忘记密码发生的事件..............  */
    $('#forget_pw').click(function(){
        $('.find_password').css('display','block');    
    })
    //关闭找回密码页
     $('.find_password .close_p').click(function(){
         $('.find_password').css('display','none'); 
     })
    $('#cancel_btn').click(function(){
       $('.find_password').css('display','none'); 
    })
    /* .............点击忘记密码发生的事件..............  */
    /* .............设置密码的事件..............  */
    //关闭设置密码页
    $('.set_password .close_p').click(function(){
         $('.set_password').css('display','none'); 
     })
    /* .............设置密码的事件..............  */
    /*..............与后台交互数据................*/
    //获取前台数据对象
    var obuser={
      user:{
    	  userAccount:12,		//账号（字符串）
    	  userPassword:	111111111,		//密码（字符串）

      },
      type:1   	            //角色类型（整形：1客户，2管理员，3骑手，4店家）          							
    }     
   //点击登录跳转到不同的用户页面
    $("#sign_btn").click(function(){
    	//获取账号，密码，类型type
    	obuser.userAccount=$('.user').val();
    	obuser.userPassword=$('.password').val();
    	obuser.type=$(".select_user input[name='user']:checked").val();
    	if(obuser.userAccount==''||obuser.userPassword==''){
    		alert('账号密码不能为空');
    	}
    	else{
    		//ajax
    	   	var userData=$.post(
    	   			projectDirectory+"/UserController/login",    			
        			obuser,
        			function(data){ //{"state":0,"responseInfo":"账号不存在"}
    	   				if(data.state==0){
    	   					alert(data.responseInfo);
    	   				}
    	   				else if(data.state==1){
    	   					//地址跳转   /custmer/index.html  /rider/index.html   	   					
    	   					window.location.href=projectDirectory+data.responseInfo;
    	   					
    	   				}
        		},'JSON')
        	 .error(
        			 function(userData) {
        				 alert('erro'); 
        	});
    	}  	
    })
    //。。。。。。。。。忘记密码。。。邮箱的验证码。。。。。
     //找回密码的确定按钮
   /*$('#confirm_btn').click(function(){
        $('.set_password').css('display','block');
        $('.find_password').css('display','none');  
    })*/
    //获取验证码
    var request_email={};
    var wait=60;  
	function time(o) {   
	  if (wait == 0) {     
	  	o.removeAttribute("disabled");       
	    o.value="获取验证码";    
	     wait = 60;    
	   } 
	else {       
	    o.setAttribute("disabled", true);     
	    o.value="重新发送(" + wait + ")";     
	    wait--;     
	    setTimeout(function() {      time(o)     },     1000)    
	    }  
	 }
	
    $("#get_code").click(function(){   //userEmail:"123456@qq.com";		//邮箱(字符串)
	         //code:"123456";					//验证码（字符串）
    	request_email.userEmail=$('.find_password .email').val();
    	var v = $(this).get(0);
    	//time(v);
    	if(request_email.userEmail==''){
    		alert('邮箱不能为空');
    	}
    	else{
    		var userData=$.post(
    	   			projectDirectory+"/UserController/getCodeByUserEmail",    			
    	   			request_email,
        			function(data){ //{"state":0,"responseInfo":"邮箱格式不正确"}   	   			    	   				
    	   				if(data.state==0){
    	   					alert(data.responseInfo);
    	   				}
    	   				else if(data.state==1){
    	   					alert('获取验证码成功');
    	   					time(v);
    	   				}
        		},'JSON')
        	 .error(
        			 function(userData) {
        				 alert('erro'); 
        	});
    	}
     })
     //判断验证码和邮箱是否对应
    $("#confirm_btn").click(function(){
    	//获取账号，密码，类型type
    	request_email.userEmail=$('.find_password .email').val();
    	request_email.code=$('.find_password .email_code').val();
    	if(request_email.userEmail==''||request_email.code==''){
    		 alert('邮箱及验证码不能为空');
    	}
    	else{
    		//ajax  userEmail
    	   	var emailData=$.post(
    	   			projectDirectory+"/UserController/verificationByCode",   			
    	   			request_email,
        			function(data){  //{"state":0,"responseInfo":"邮箱为空"}   	   				
    	   			   if(data.state==0){
    	   					alert(data.responseInfo);
    	   				}
    	   			   else if(data.state==1){   	   				  
    	   					alert(data.responseInfo);
    	   				    $('.find_password').css('display','none'); 
    	   					$('.set_password').css('display','block');    	   				   
    	   				}
        		},'JSON')
        	 .error(
        			 function(emailData) {
        				 alert('erro 未知错误，请联系管理员'); 
        	});
    	}  	
    })
    //.................设置密码对应。。。。。。。。
    var set_password={
    	password:123,
    	_password:123
    } 
     $('.set_password').css('display','block');  
    $("#set_login").click(function(){
    	//获取账号，密码，类型type
    	set_password.password=$('.set_password .set_pw').val();
    	set_password._password=$('.set_password .confirm_pw').val();
    	if(set_password.password==''||set_password._password==''){
    		 alert('密码不能为空');
    	}
    	else{
    		//ajax  userEmail
    	   	var set_p=$.post(
    	   			projectDirectory+"/UserController/updatePassword",   			
    	   			set_password,
        			function(data){  //{"state":0,"responseInfo":"密码为空"}   	   				
    	   			   if(data.state==0){    	   				    
    	   					alert(data.responseInfo);
    	   				}
    	   			   else if(data.state==1){   
    	   				    alert('设置密码成功');
    	   			        $('.set_password').css('display','none');   	   			       
    	   				}
        		},'JSON')
        	 .error(
        			 function(set_p) {
        				 alert('erro 未知错误，请联系管理员'); 
        	});
    	}  	
    })
    
    /*..............与后台交互数据................*/