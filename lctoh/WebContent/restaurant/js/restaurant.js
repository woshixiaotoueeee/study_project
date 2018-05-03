var restaurant;
$(function(){
	 /*初始化数据*/
	 init();
})
/*
 * 初始化数据
 * */
function init(){
	getLogininfo();
	
	edit_notice();      //修改公告代码
	
	load_data();        //获取加载数据（订单数据和今日总览）
	
}
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
				   $('#restaurantNotice').html(restaurant.restaurantNotice);
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


function  edit_notice(){ //修改公告代码
	$("#delete_notice").unbind("click"); 
	$("#delete_notice").click(function(){
		$('#restaurantNotice').html("");
 	})
 	$("#save_notice").unbind("click"); 
	$("#save_notice").click(function(){
		$.ajax({
			   type: "post",
			   data:{'notice':$('#restaurantNotice').html()},
			   url: Common.updateRestaurantNotice,
			   dataType: "json",
			   async: false,
			   success:function(data){
				   layer.msg(data.responseInfo, {time:2500});
				   if(data.state==1){
					   getLogininfo();
				   }
			   },
			   error:function(errordate){
				   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
			   }
		})
 	})
}
/*
 * 获取时间
 * */
function getTodayTm(){
	var timeObj={};
	var nowdate=new Date();
	var datestr=nowdate.getFullYear()+'-'+(nowdate.getMonth()+1)+'-'+nowdate.getDate();
	timeObj.etm=datestr+" 23:59:59";
	timeObj.stm=datestr+" 00:00:00";
	return timeObj;
}
function load_data(){   //获取加载数据（订单数据和今日总览）
	var requestdata=getTodayTm();
	requestdata.restaurantId=restaurant.restaurantId;
	$.ajax({
		   type: "post",
		   url:Common.orderStatistics,
		   data:requestdata, 
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   $(".now_order").html(data.responseInfo[0].count);
				   $(".now_turnover").html(data.responseInfo[0].amount);
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
			   data:requestdata, 
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
		$(".new_order").html(newDispatching.num);
		$(".send_order").html(pszDispatching.num);
		$(".back_order").html(ycDispatching.num);
		
		
		var data=[newDispatching,pszDispatching,zapsDispatching,ycDispatching];
	   
	   
	   
	       //alert(data[1].psnm);
	       var myChartTwo = echarts.init(document.getElementById('mainTwo'));
	       var optionTwo = {
		    title : {
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    /*
		    legend: {
		        //orient: 'vertical',
		         bottom: 0,
                 left: 'center',
		         data: [data[0].psnm,data[1].psnm,data[2].psnm,data[3].psnm]
		    },*/
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
                             	 color: "#3cb82c"
                                 }
                             }
                        },
		                {value:data[1].num, name:data[1].psnm,
                            itemStyle: {
                             normal: {
                             	 color: "#ef4a1e"
                                 }
                             }
		                },
		                {value:data[2].num, name:data[2].psnm,
                             itemStyle: {
                                normal: {
                             	   color: "#888888"
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