   /* alert($(".sex input[name='sex']:checked").val()); */
    $('.register>input').focus(function(){
    	$(this).attr('placeholder','');
    	
    })
    /* .............正则表达式..............  */
    //昵称
    var  idna=/^[\w]{1,12}$/g;     
    $('#idName').change(function(){
    	var nickname=$(this).val();
    	if(idna.test(nickname)){
	       alert('昵称正确');
	    }
	    else{
	    	alert('昵称不正确')
	    }
    })  

    //密码
    
    //确认密码
    
    //手机号码
    var  pnamber=/^1\d{10}$/;
    $('#phone_nu').change(function(){
    	var pNu=$(this).val();
    	if(pnamber.test(pNu)){
	        alert('手机号正确')
	    }
	    else{
	    	alert('手机号不正确')
	    }
    }) 
    //邮箱email
    /* .............正则表达式..............  */
    /* .............点击注册发生的事件..............  */
    $("#btnSubmit").click(function(){
     /*       var val=$(".sex input[name='sex']:checked").val();
            if(val=='男'){
                alert("为男性");             
            }
            else if(val=='女'){
                alert('为女性');
            }
            else{
                 alert('不男不女')
                 return false;
            }*/

        })
     /* .............点击注册发生的事件..............  */
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
        
        
        
        