 
 
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
    $('#set_login').click(function(){
      alert('登录成功');
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
    $("#get_code").click(function(){   //userEmail:"123456@qq.com";		//邮箱(字符串)
	   //code:"123456";					//验证码（字符串）
    	request_email.userEmail=$('.find_password .email').val();
    	if(request_email.Account==''){
    		alert('邮箱不能为空');
    	}
    	else{
    		var userData=$.post(
    	   			projectDirectory+"/UserController/getCodeByUserEmail",    			
    	   			request_email,
        			function(data){ //{"state":0,"responseInfo":"邮箱为空"}   	   			
    	   				alert(data);
/*    	   				if(data.state==0){
    	   					alert(data.responseInfo);
    	   				}
    	   				else if(data.state==1){
    	   					alert(data.responseInfo);  	   					   	   					
    	   				}*/
        		})
        	 .error(
        			 function(userData) {
        				 alert('erro'); 
        	});
    	}
     })
    $("#confirm_btn").click(function(){
    	//获取账号，密码，类型type
    	request_email.userEmail=$('.find_password .email').val();
    	request_email.code=$('.find_password .email_code').val();
    	if(request_email.Account==''||request_email.code==''){
    		alert('邮箱及验证码不能为空');
    	}
    	else{
    		//ajax  userEmail
    	   	var userData=$.post(
    	   			projectDirectory+"/UserController/getCodeByUserEmail",    			
    	   			request_email.Account,
        			function(data){ //{"state":0,"responseInfo":"邮箱为空"}
    	   				/*alert(data);*/
    	   				alert(data.state);
    	   				if(data.state==0){
    	   					alert(data.responseInfo);
    	   				}
    	   				else if(data.state==1){
    	   					alert(data.responseInfo);  	   					
    	   					
    	   					
    	   				}
        		},'JSON')
        	 .error(
        			 function(userData) {
        				 alert('erro'); 
        	});
    	}  	
    })
    
    /*..............与后台交互数据................*/