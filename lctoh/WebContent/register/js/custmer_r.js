   /* alert($(".sex input[name='sex']:checked").val()); */
    $('.register>input').focus(function(){
    	$(this).attr('placeholder','');	
    })
    /* .............正则表达式..............  */
    //昵称  只含有汉字、数字、字母、下划线,不能以下划线数字开头和结尾：
    var  idna=/^(?!_)(?!.*?_$)[a-zA-Z_\u4e00-\u9fa5][a-zA-Z0-9_\u4e00-\u9fa5]{1,10}$/;     
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
       regisObj.userPassword=$('.register .re_password').val();
       regisObj._userPassword=$('.register .re_confimpw').val();
       regisObj.userSex=$('.register .sex').val();
       regisObj.userEmail=$('.register .re_email').val();
       regisObj.userPhone=$('.register #phone_nu').val();
     //ajax
        var custData=$.post(projectDirectory+"/UserController/register",
        		regisObj,
        		function(data){
        	       if(regisObj.userPassword==''||regisObj._userPassword==''||
        	    		regisObj.userPhone==''||$('#idName').val()==''){
        	    	    alert('请填写信息完');
        	       }
        	       else{  // state":0,"responseInfo":"邮箱格式错误"
        	    	   alert('访问后台');
           	           alert(data);
           	        /* if(data.state==0){
           	        	   alert(data.responseInfo)
           	           }
           	           else{
           	                alert('注册成功');
           	        	    alert(data.responseInfo);
           	           }*/
        	       }       	           	   
        })
        .error(
        		function(custData){
        			alert('erro');
        		}
         );       
     })
		

        
     /* .........点击注册与后台交互获取数据。。。。。。。*/ 
        
        
        
        