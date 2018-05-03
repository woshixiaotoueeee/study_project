 //商家的个人中心页面
var restaurant;
initDate();//初始化时间  必须写在外面  日期控件依赖    lctoh/Common/easyui/jquery.min.js
$(function(){
	 
	change_page();//接收参数不同时 跳转到不同的页面（店家设置 1 统计管理2 ）
	getLogininfo();
	//初始化商家个人中心的信息数据
	init_restCenter();
	
    //点击查询统计信息按钮事件
    query_chart();
    
    $("#btn_qurey").click(function(){
    	query_chart();
	});
   // qureyChartInfo();
	 /*订单统计饼图*/
	 // pie_chart();
	 /*订单统计过程线*/
	 //line_chart();	
	 /*营业额统计柱状图*/
	 //bar_chart();
    
    
 })
 
 function getLogininfo(){
	$.ajax({
		   type: "post",
		   data:null,
		   url: Common.getRestaurantLoginInfo,
		   dataType: "json",
		   async: false,
		   success:function(data){
			   if(data.state==1){
				   restaurant=data.responseInfo;
				   initRestaurantToHtml(restaurant);
			   }
			   else{
				   layer.msg(data.responseInfo, {time:2500});
			   }
		   },
		   error:function(errordate){
			 //  layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
		   }
		})
}
function initRestaurantToHtml(_restaurant){
	$(".portrait img").attr("src","../"+_restaurant.restaurantImage);
	
	//$("#my_balance").html(_restaurant.restaurantIncome);
	

	$("#account").html(_restaurant.restaurantUser.userAccount);
	
	$("#customerNickname").html(_restaurant.restaurantName);
	
	$("#restaurantDeliveryFee").html(_restaurant.restaurantDeliveryFee+"￥");
	
	$("#restaurantOfferPrice").html(_restaurant.restaurantOfferPrice+"￥");
	
	$("#phone").html(_restaurant.restaurantPhone);
	
	$("#email").html(_restaurant.restaurantUser.userEmail);
	
	$("#addressInfo").html(_restaurant.restaurantAddressInfo);
	
	$(".rest_brief").html(_restaurant.restaurantNotice);
}





function change_page(){
	var urlmy=window.location.href;
    //alert(urlmy+'iframe地址栏'); 
    //alert(window.location.search);
    
   var Request = new Object(); 
    Request = GetRequest(); 
    var num_page; 
    num_page = Request.num; 
    var len=$('.dec_span').length;
    //传入参数不同时显示的不一样
    // $('.dec_span').eq(num_page-1).css('display','block').siblings().css('display','none');
    if(num_page==1){
    	/*alert('进入个人资料')*/
    	$('.infor_left ul li').eq(1).find('.dec_span').css('display','block');
	    $('.infor_left ul li').eq(1).siblings().find('.dec_span').css('display','none');
    	//$('.dec_span').eq(0).css('display','block').siblings().css('display','none');
    	$('.center_cont').eq(0).css('display','block').siblings().css('display','none');
    }
    if(num_page==2){
    	/*alert('进入我的订单')*/
    	$('.infor_left ul li').eq(2).find('.dec_span').css('display','block');
	    $('.infor_left ul li').eq(2).siblings().find('.dec_span').css('display','none');
    	//$('.dec_span').eq(1).css('display','block').siblings().css('display','none');
    	$('.center_cont').eq(1).css('display','block').siblings().css('display','none');
    }   
}
function GetRequest() {   //转化地址参数的函数
	var url = window.location.search; //获取url中"?"符后的字串 
	var theRequest = new Object(); 
	if (url.indexOf("?") != -1) { 
	var str = url.substr(1); 
	strs = str.split("&"); 
	for(var i = 0; i < strs.length; i ++) { 
		theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
		} 
	} 
	return theRequest; 
}
/*
 * 初始化时间
 * */
function initDate(){
	var buttons = $.extend([], $.fn.datebox.defaults.buttons);
	buttons.splice(1, 0, {
		text: 'MyBtn',
		handler: function(target){
			alert('click MyBtn');
		}
	});
	var str='';
	var now=new Date();
	/*alert(now);*/
	// console.log(now);//当前系统的时间对象
	var year=now.getFullYear();//年
	var month=now.getMonth()+1;//月，从下标0计算
	var date=now.getDate();//日
    str=month+'/'+date+'/'+year;

    $('#etm').val(str);
    var strs='';
    var newDate = now - 9*24 * 60 * 60 * 1000; //备注 如果是往前计算日期则为减号 否则为加号
    newDate = new Date(newDate);
    var years=newDate.getFullYear();//年
	var months=newDate.getMonth()+1;//月，从下标0计算
	var dates=newDate.getDate();//日
    var strs=months+'/'+dates+'/'+years;
   /* alert(newDate);*/
    $('#stm').val(strs);
}
function init_restCenter(){
     my_center_list(); /*店家设置 统计管理切换*/

     my_data_edit();//店家设置信息 包含弹窗

}
function my_center_list(){
	   /*店家设置 统计管理切换*/
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
	       /*$('.span_left').eq(index).css('display','block').siblings().css('display','none');*/
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
/*
 * 店家设置函数
 * */
function my_data_edit(){  //店家信息设置
   
    
     
    /* 点击编辑头像*/
	$("#edit_portrait").unbind("click"); 
	$('#edit_portrait').click(function(){
		edit_portrait();
	})	
	/* 点击编辑店家信息*/
	$("#edit_infor").unbind("click"); 
	$('#edit_infor').click(function(){
		edit_rest_infor();	
	})	
	/* 点击修改密码*/
	$("#edit_password").unbind("click"); 
	$('#edit_password').click(function(){
		edit_password();
	})
}
 /*编辑头像*/
function edit_portrait() {
	var str=`<div class='portrait_lay'>
	    <div class='lay_infor'>
	        <div class='pic_img'>
	             <img src='#portrait#'/>
		    </div>
	    </div>
	    <div class='lay_infor'>
	    	<div class='upload_img'>
	    	  <span>上传图片</span>
	    	  <form id='updatePortrait'>
	    	     <input type="file" name="file" id="file" accept="image/gif,image/jpeg,image/.png" />
	    	  </form>	
	    	 </div>
	    </div>
	    <div class='lay_infor'>
		    <input type='button' class='pic_save' value='保存图像'/>
		    <input type='button' class='pic_cancel' value='取消'/>
	    </div>	    
	</div>`;
	
	str=str.replace("#portrait#",'../'+restaurant.restaurantImage);
	var layerIndex=layer.open({
	  title:['编辑头像', 'font-size:18px;'],
	  type: 1,
	  area: ['500px', '450px'], //宽高
	  content: str
	});
	$(".pic_save").click(function(){
		if($('#file').val().length==0){
			layer.msg('请选择文件', {time:2500}); 
			return;
		}
		var image = new FormData($("#updatePortrait")[0]);  
	    $.ajax({  
	         url: Common.updateRestaurantPortrait,  
	         type: 'POST',  
	         data: image, 
	         dataType: "json",
	         cache: false,  
	         contentType: false,  
	         processData: false, 
	         success: function (returndata) {  
	        	 layer.msg(returndata.responseInfo, {time:2500});
	        	 layer.close(layerIndex);
	        	 getLogininfo();
	        	 
	         },  
	         error: function (returndata) {  
	        	 layer.msg('文件过大或文件格式不对', {time:2500}); 
	        	 layer.close(layerIndex);
	         }  
	    });
	});
	$(".pic_cancel").click(function(){
		layer.close(layerIndex);
	});
	
	
}
/*编辑店家信息*/
function edit_rest_infor(){
	 var edit_rest='./editRestInfor.html';
	    layer.open({
			  title: ['修改店家信息', 'font-size:18px;'],
			  type: 2,
			  shadeClose: true,
			  shade: 0.2,
			  area: ['560px', '600px'],
			  content: edit_rest,  //iframe的url
			  end: function () {
				  getLogininfo();
	            }
		 }); 
}
/*编辑修改密码*/
function edit_password(_restaurant){
	var str=`<div class='edit_password'>
		    <div class='lay_infor'>
			    <span>原密码</span>
			    <input id='oldpwd' type='password' placeholder=""/>	
		    </div>	
		    <div class='lay_infor'>
			   <span>新密码</span>
			   <input id='newpwd' type='password' placeholder=""/>
		    </div>
		    <div class='lay_infor'>
			   <span>确认密码</span>
			   <input id='_newpwd' type='password' placeholder=""/>	
		    </div>
		    <div class='lay_infor save_cancel'>
			    <input type='button' class='edit_save' value='保存'/>
			    <input type='button' class='edit_cancel' value='取消'/>
		    </div>	
		</div>`;
		var layerIndex=layer.open({
		  title:['修改密码', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '360px'], //宽高
		  content: str
		});
		
		
		$(".edit_save").click(function(){
			var pwd={};
			pwd.oldPassword=$('#oldpwd').val();
			pwd.newPassword=$('#newpwd').val();
			pwd._newPassword=$('#_newpwd').val();
			pwd.userId=restaurant.restaurantUser.userId;
			
		    $.ajax({  
		         url: Common.updatePasswordByUserId,  
		         type: 'POST',  
		         data: pwd, 
		         dataType: "json",
		         success: function (returndata) {  
		        	 layer.msg(returndata.responseInfo, {time:2500});
		        	 layer.close(layerIndex);
		        	 getLogininfo();
		         },  
		         error: function (returndata) {  
		        	 layer.msg('未知错误请刷新页面重试', {time:2500}); 
		        	 layer.close(layerIndex);
		         }  
		    });
		});
		
		$(".edit_cancel").click(function(){
			layer.close(layerIndex);
		});
		
}
/*
 *统计管理函数
 **/
function query_chart(){  //点击统计管理的查询按钮
	var queryData=getTm();
	queryData.restaurantId=restaurant.restaurantId;
	
	$.ajax({
		   type: "post",
		   url:Common.orderStatistics,
		   data:queryData, 
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   line_chart(data.responseInfo);
				   bar_chart(data.responseInfo);
			   }
			   else{
				   layer.msg(data.responseInfo, {time:2500});
			   }
		   },
		   error:function(errordate){
			   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
		   }
		})
		
	$.ajax({
		   type: "post",
		   url:Common.orderStatisticsByState,
		   data:queryData, 
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   pie_chart(data.responseInfo);
			   }
			   else{
				   layer.msg(data.responseInfo, {time:2500});
			   }
		   },
		   error:function(errordate){
			   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
		   }
		})	
	
	
}
/*
 * 获取时间
 * */
function getTm(){
	var timeObj={};
	timeObj.etm=dateFtt("yyyy-MM-dd hh:mm:ss",new Date("23:59:59 "+$('#etm').val()));
	timeObj.stm=dateFtt("yyyy-MM-dd hh:mm:ss",new Date($('#stm').val()));
	return timeObj;
}
function qureyChartInfo(){//统计管理的查询事件
    /*订单统计饼图*/
	//pie_chart();
	/*订单统计过程线*/
	//line_chart();	
	/*营业额统计柱状图*/
	//bar_chart();
}
/*
 * 统计饼图
 * */
function  pie_chart(dataList){
	var newDispatching={
            psnm:"订单",
            num:"0"
         };
	var pszDispatching={
            psnm:"配送中",
            num:"0"
         };
	var zapsDispatching={
            psnm:"完成配送",
            num:"0"
         };
	var ycDispatching={
            psnm:"异常订单",
            num:"0"
         };
	for(var i=0;i<dataList.length;i++){
		if(dataList[i].type<100003){
			newDispatching.num+=dataList[i].count;
		}else if(dataList[i].type=100003){
			pszDispatching.num+=dataList[i].count;
		}else if(dataList[i].type=100004){
			zapsDispatching.num+=dataList[i].count;
		}else if(dataList[i].type>100004){
			ycDispatching.num+=dataList[i].count;
		}
		
	}
	var data=[newDispatching,pszDispatching,zapsDispatching,ycDispatching];
	   
	
    var myChartTwo = echarts.init(document.getElementById('mainPie'));
    var optionTwo = {
	    title : {
	        text: '配送订单饼图',
	        textStyle: {
            fontSize: 14,
            //fontStyle: 'lighter',
            color:'#000033'              			                
           },
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	    	type: 'scroll',
	        orient: 'vertical',
	        right:0,
	        top:40,
	        data: [data[0].psnm,data[1].psnm,data[2].psnm,data[3].psnm]
	    },
	    series : [
	        {
	            name: '配送信息',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	           data:[
	                {value:data[0].num, name:data[0].psnm,
	                 itemStyle: {
                         normal: {
                         	 color: "#FFFF00"
                             }
                         }
                    },
	                {value:data[1].num, name:data[1].psnm,
                        itemStyle: {
                         normal: {
                         	 color: "green"
                             }
                         }
	                },
	                {value:data[2].num, name:data[2].psnm,
                         itemStyle: {
                            normal: {
                         	   color: "#ef4a1e"
                               }
                          }
	                },
	                {value:data[3].num, name:data[3].psnm,
                        itemStyle: {
                           normal: {
                        	   color: "#0000CD"
                              }
                         }
	                }
	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	}
   myChartTwo.setOption(optionTwo);
}
/*
 * 统计过程线
 * */
function  line_chart(dataList){
	var myGraph=echarts.init(document.getElementById('lineGraph'));
    /*var dataList=[
          {
             "ordps": 36, 
             "ordww": 3, 
		     "ordwc": 145, 
		     "ordid": "30427800", 
		     "tm": "2018-03-21  "
		 },
		  {
             "ordps": 26, 
             "ordww": 0, 
		     "ordwc": 165, 
		     "ordid": "30427800", 
		     "tm": "2018-03-22 "
		 },
		 {
             "ordps": 37, 
             "ordww": 6, 
		     "ordwc": 145, 
		     "ordid": "30427800", 
		     "tm": "2018-03-23"
		 },
		  {
             "ordps": 7, 
             "ordww": 0, 
		     "ordwc": 135, 
		     "ordid": "30427800", 
		     "tm": "2018-03-24"
		 },
		 {
             "ordps": 9, 
             "ordww": 0, 
		     "ordwc": 145, 
		     "ordid": "30427800", 
		     "tm": "2018-03-25"
		 },
		 {
             "ordps": 6, 
             "ordww": 0, 
		     "ordwc": 125, 
		     "ordid": "30427800", 
		     "tm": "2018-03-26"
		 }
       ]; */
       //数据处理
    	var ordwcList = [];    //已完成的订单
    	var tms = [];
    	var item;
    	for(var i=0;i<dataList.length;i++){
    		item = dataList[i];
    		ordwcList.push(item.count);
    		tms.push(item.tm);
    	}
      var optionGragh={
       	 title:{
            text:'完成订单过程线',
            x:'center',
            top: '5px',
            left: 'center',
            textStyle: {
                fontSize: 14,
                //fontStyle: 'lighter',
                color:'#000033'              			                
            }
       	 },
       	 tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            animation: false,
	            label: {
	                backgroundColor: '#505765'
	            }
	        }		       
	    },
	     toolbox: { //可视化的工具箱
              show: true,
              feature: {                                                                  
  		    		dataView: { //数据视图                 		    			
  		    			optionToContent: function(option){
  		    				var axisData = option.xAxis[0].data;
  		    				var series = option.series;
  		    				var table = '<table border="1"  bordercolor="#a0c6e5" style="width:100%;text-align:center"><tbody><tr bgcolor="lightgray">'
  		    					
  		    						  + '<td>时间</td>'
  		    						  + '<td>' + series[0].name + '</td>'		          		    						  
  		    						  + '</tr>';
  		    				for(var i=0,l=axisData.length; i<l; i++){
  		    					table += '<tr>'
  		    						  + '<td>' + axisData[i] + '</td>'
  		    						  + '<td>' + series[0].data[i] + '</td>'  		    						          		    						
  		    						  + '</tr>';
  		    				}
  		    				table += '</tbody></table>';
  		    				return table;
  		    			}	                      
                  },
                  restore: { //重置
                      show: true
                  },                                                        
                  magicType: {//动态类型切换
                      type: ['bar', 'line']
                  }
              }
          },  

	    legend: {
	       
	        top: 30, 
	        data: '已完成订单'
	    },
	    xAxis: {
	    	type: 'category',
	    	name: '时间',							            							           
            position: 'right',
            axisTick: {
                alignWithLabel: true
            },
	        data: tms
	    },
	    yAxis: {
            type: 'value',
            name: '数量',							            							           
            position: 'left',	
            axisLabel: {
                formatter: '{value} 单'
              }				         
	    },
	    series: {
		            name: '已完成订单',
		            type:'line', 
		            barGap:'-100%',
                    barCategoryGap:'60%',
		            itemStyle: {
		                normal: {
		                    color: "#00FF66"
		                  }
		                },
		            data: ordwcList
		       }

       }
    myGraph.setOption(optionGragh);
}
/*
 *柱状图
 */
function  bar_chart(dataList){

   var myGraph=echarts.init(document.getElementById('barGraph'));
    /*var dataList=[
          {
             "price": 336, 
		     "tm": "2018-03-21  "
		 },
		  {
             "price": 296, 
		     "tm": "2018-03-22 "
		 },
		 {
             "price": 176, 
		     "tm": "2018-03-23"
		 },
		 {
             "price": 226,  
		     "tm": "2018-03-24"
		 },
		 {
             "price": 426,  
		     "tm": "2018-03-25"
		 },
		 {
             "price": 126, 
		     "tm": "2018-03-26"
		 }
       ]; */
       //数据处理
    	var ordwcList = [];    //已完成的订单
    	var tms = [];
    	var item;
    	for(var i=0;i<dataList.length;i++){
    		item = dataList[i];
    		ordwcList.push(item.amount); 
    		tms.push(item.tm);
    	}
      var optionGragh={
       	 title:{
            text:'营业额',
            x:'center',
            top: '5px',
            left: 'center',
            textStyle: {
                fontSize: 14,
                //fontStyle: 'lighter',
                color:'#000033'              			                
            }
       	 },
       	 tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            animation: false,
	            label: {
	                backgroundColor: '#505765'
	            }
	        }		       
	    },
	     toolbox: { //可视化的工具箱
              show: true,
              feature: {                                                                  
  		    		dataView: { //数据视图                 		    			
  		    			optionToContent: function(option){
  		    				var axisData = option.xAxis[0].data;
  		    				var series = option.series;
  		    				var table = '<table border="1"  bordercolor="#a0c6e5" style="width:100%;text-align:center"><tbody><tr bgcolor="lightgray">'
  		    					
  		    						  + '<td>时间</td>'
  		    						  + '<td>' + series[0].name + '</td>'		          		    						  
  		    						  + '</tr>';
  		    				for(var i=0,l=axisData.length; i<l; i++){
  		    					table += '<tr>'
  		    						  + '<td>' + axisData[i] + '</td>'
  		    						  + '<td>' + series[0].data[i] + '</td>'  		    						          		    						
  		    						  + '</tr>';
  		    				}
  		    				table += '</tbody></table>';
  		    				return table;
  		    			}	                      
                  },
                  restore: { //重置
                      show: true
                  },                                                        
                  magicType: {//动态类型切换
                      type: ['bar', 'line']
                  }
              }
          },  
	    legend: {
	       
	        top: 30, 
	        data: '营业额'
	    },
	    xAxis: {
	    	type: 'category',
	    	name: '时间',							            							           
            position: 'right',
	        data: tms
	    },
	    yAxis: {
            type: 'value',
            name: '金钱',							            							           
            position: 'left',							           
            axisLabel: {
                formatter: '{value} 元'
              }				         
	    },
	    series: {
		            name: '营业额',
		            type:'bar', 
		            barGap:'-100%',
                    barCategoryGap:'60%',
		            itemStyle: {
		                normal: {
		                    color: "#FF6666"
		                  }
		                },
		            data: ordwcList
		       }

       }
    myGraph.setOption(optionGragh);
}