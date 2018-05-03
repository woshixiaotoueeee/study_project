   /* 获取焦点时 input提示为空 */
    $('.register>input').focus(function(){
    	$(this).attr('placeholder','');	
    })
    /* .............正则表达式..............  */
    //昵称  只含有汉字、数字、字母、下划线,不能以下划线数字开头和结尾：
    var  idna=/^(?!_)(?!.*?_$)[a-zA-Z_\u4e00-\u9fa5][a-zA-Z0-9_\u4e00-\u9fa5]{0,10}$/;     
    $('#idName').change(function(){
    	var nickname=$(this).val();
    	if(idna.test(nickname)){
	     /*  alert('昵称正确')*/
        $('.password_p p').eq(0).css('display','none');
	    }
      else if(nickname==''){
           $('.password_p p').eq(0).css('display','none'); 
      }
	    else{
	    	/*alert('昵称不正确')*/
        $('.password_p p').eq(0).css('display','block');
	    }
    })  
    //密码错误提示 
    var  password_num=/^\w{6,16}$/;
    $('.password').change(function(){
      var pNu=$(this).val();
      if(password_num.test(pNu)){
          $('.password_p p').eq(1).css('display','none');
      }
      else if(pNu==''){
           $('.password_p p').eq(1).css('display','none'); 
      }
      else{          
        $('.password_p p').eq(1).css('display','block');
      }
    })
    //确认密码
    $('.r_password').change(function(){
      var _pw=$(this).val();
      var pw=$('.password').val()
      if(_pw==pw){
        $('.password_p p').eq(2).css('display','none');      
      }
      else if(_pw==''){
           $('.password_p p').eq(2).css('display','none'); 
      }
      else{          
         $('.password_p p').eq(2).css('display','block');
      }
    })
    //手机号码
    var  pnamber=/^1\d{10}$/;
    $('#phone_nu').change(function(){
    	var pNu=$(this).val();
    	if(pnamber.test(pNu)){
	         $('.password_p p').eq(3).css('display','none');
	    }
      else if(pNu==''){
           $('.password_p p').eq(3).css('display','none'); 
      }
	    else {
	    	 $('.password_p p').eq(3).css('display','block');
	    }
    }) 
    //邮箱email
    var  email_nu=/^([a-zA-Z1-9_])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])*(\.(info|biz|com|edu|gov|net|am|bz|cn|cx|hk|jp|tw|vc|vn))$/;
    $('.email').change(function(){
      var pNu=$(this).val();
      if(email_nu.test(pNu)){
           $('.password_p p').eq(4).css('display','none');
      }
      else if(pNu==''){
           $('.password_p p').eq(4).css('display','none'); 
      }
      else{
         $('.password_p p').eq(4).css('display','block');
      }
    }) 
    
    /* .............正则表达式..............  */
    
    /* .............layer弹窗..............  */
    //layer.msg('账号密码不能为空');
    
   /* $('#test1').click(function(){
     	var ii=layer.msg('账号密码不能为空');
     	   layer.style(ii, {
			  top: '10px',
			  color:'red',
			  border:'1px solid black',
			  background:'blue',
			  borderRadius:'10px'
			});
     	  setTimeout(function(){
     		layer.close(ii);
     	  },1000);
        })
      */
   /* 
     function layer1(){
    	var ti=layer.open({
	 		type:1,
	 		title: '<span style="color:#c00;font-size:10px;padding:0;margin:0;">提示</span>',
	            //['提示','font-size:10px;height:20px;padding:0;line-height:20px'],
	 		closeBtn: 0,
	 		area:['300px','160px'],
	 		shade: 0,
	 		shadeClose:true,//点击遮罩层关闭
	 		content:'\<\div style="padding:10px;">账号密码不能为空\<\/div>'
	 	    })
	     setTimeout(function(){
	 		layer.close(ti);
	 	  },1000);
    }
    layer1();*/
    /* .............layer弹窗..............  */
    
    /* .........点击注册与后台交互获取数据。。。。。。。*/ 
     $("#btnSubmit").click(function(){
    	 /*        后台所需数据
 		{
 			userPassword:"123456";		//密码
 			_userPassword:"123456";		//确认密码
 			userSex:"男"；				//性别
 			userEmail:"1285739190@qq.com";//邮箱
 			userPhone:"123456789";		//电话号码
 		}*/
       var  regisObj={  //注册对象及对象信息
    		   userPassword:"123456",		//密码
		       _userPassword:"123456",		//确认密码
		       userSex:"男",				//性别
		       userEmail:"1285739190@qq.com",//邮箱
		       userPhone:"123456789"		//电话号码
          }  
       //regisObj.userPassword=$('.register .password').val();
       //regisObj._userPassword=$('.register 。r_password').val();
       regisObj.userSex=$('.register .sex input:radio:checked').val();
       regisObj.userEmail=$('.register .email').val();
       regisObj.userPhone=$('.register #phone_nu').val();
       if(regisObj.userPassword==''||regisObj._userPassword==''||
	    		regisObj.userPhone==''||$('#idName').val()==''){
	    	    //alert('请填写必填信息');        	    	   
	    	    var ii=layer.msg('请填写必填信息');
	      	    layer.style(ii, {
	 			   color:'red'
	 			 });
	      	   setTimeout(function(){
	      		 layer.close(ii);
	      	   },1000);
	       }
        //ajax
        var custData=$.post(projectDirectory+"/UserController/register",
        		regisObj,
        		function(data){          	          
       	         if(data.state==0){  
       	        	
       	        	 alert(data.responseInfo)
       	           }
       	         else{
       	                alert('注册成功');      	              
       	        	    alert("你的账户为:"+data.responseInfo.userAccount);
       	        	 top.location.href=projectDirectory+"/Login/login.html";
       	           }
        	          	           	   
          },'JSON')
        .error(
        		function(custData){
        			alert('erro');
        		}
         );       
     })
		

        
     /* .........点击注册与后台交互获取数据。。。。。。。*/ 
        
        
        
        