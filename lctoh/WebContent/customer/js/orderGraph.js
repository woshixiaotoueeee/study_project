//初始化时间  必须写在外面  日期控件依赖    lctoh/Common/easyui/jquery.min.js
initDate();
$(function(){
	
	 /*初始化数据*/
	 init();
	 
	 /*统计饼图*/
	 pie_chart();
	 /*统计过程线*/
	 line_chart();	
	
	 $("#btn_query").click(function(){
		 init();
		});
})
/*
 * 初始化数据
 * */
function init(){
	var requestdata=getTm();
	requestdata.customerId=GetQueryString('customerId');
	$.ajax({
		   type: "post",
		   url:Common.orderStatistics,
		   data:requestdata, 
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   line_chart(data.responseInfo);
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
/*
 * 获取时间
 * */
function getTm(){
	var timeObj={};
	timeObj.etm=dateFtt("yyyy-MM-dd hh:mm:ss",new Date("23:59:59 "+$('#etm').val()));
	timeObj.stm=dateFtt("yyyy-MM-dd hh:mm:ss",new Date($('#stm').val()));
	return timeObj;
}
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
    $('#stm').val(strs);
}
/*
 * 统计饼图
 * */
function  pie_chart(dataList){
	   /*var data=[
	              {
		            psnm:"配送中",
		            num:"23"
		         },
		         {
		            psnm:"已完成配送",
		            num:"13"
		         },
		         {
		            psnm:"异常订单",
		            num:"3"
		         }

	          ];*/
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
		for(var i=0;i<typelist;i++){
			if(typelist[i].type<100004){
				pszDispatching.num+=typelist[i].count;
			}else if(typelist[i].type=100004){
				zapsDispatching.num+=typelist[i].count;
			}else if(typelist[i].type>100004){
				ycDispatching.num+=typelist[i].count;
			}
			
		}
		
		var data=[pszDispatching,zapsDispatching,ycDispatching];
	       var myChartTwo = echarts.init(document.getElementById('mainTwo'));
	       var optionTwo = {
		    title : {
		        text: '配送订单饼图',
		        x:'center',
		        y:'3px',
		        textStyle: {
		            fontSize: 16,
		            //fontWeight: 'bolder',
		            color: '#333'          // 主标题文字颜色
		        }
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        //orient: 'vertical',
		        bottom: 6,
                left: 'center',
		        data: [data[0].psnm,data[1].psnm,data[2].psnm]
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
	var myGraph=echarts.init(document.getElementById('mainGraph'));
   /*
	var dataList=[
          {
             "ordps": 36, 
             "ordww": 3, 
		     "ordwc": 145, 
		     "ordprice": 165, 
		     "ordid": "30427800", 
		     "tm": "2018-03-21  "
		 },
		  {
             "ordps": 26, 
             "ordww": 0, 
		     "ordwc": 165, 
		     "ordprice": 155, 
		     "ordid": "30427800", 
		     "tm": "2018-03-22 "
		 },
		 {
             "ordps": 37, 
             "ordww": 6, 
		     "ordwc": 145, 
		     "ordprice": 145, 
		     "ordid": "30427800", 
		     "tm": "2018-03-23"
		 },
		  {
             "ordps": 7, 
             "ordww": 0, 
		     "ordwc": 135, 
		     "ordprice": 115, 
		     "ordid": "30427800", 
		     "tm": "2018-03-24"
		 },
		 {
             "ordps": 9, 
             "ordww": 0, 
		     "ordwc": 145, 
		     "ordprice": 145, 
		     "ordid": "30427800", 
		     "tm": "2018-03-25"
		 },
		 {
             "ordps": 6, 
             "ordww": 0, 
		     "ordwc": 125, 
		     "ordprice": 185, 
		     "ordid": "30427800", 
		     "tm": "2018-03-26"
		 }
       ]; */
       //数据处理
    	var ordwcList = [];    //已完成的订单
    	var ordprice=[];       //消费额
    	var tms = [];
    	for(var i=0;i<dataList.length;i++){
    		var item = dataList[i];
    		ordwcList.push(item.count);
    		ordprice.push(item.amount);
    		tms.push(item.tm);
    	}
      var optionGragh={
       	 title:{
            text:'完成订单过程线',
            x:'center',
            top: '0px',
            left: 'center',
            textStyle: {
                fontSize: 16,
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
  		    						  + '<td>' + series[1].name + '</td>'	
  		    						  + '</tr>';
  		    				for(var i=0,l=axisData.length; i<l; i++){
  		    					table += '<tr>'
  		    						  + '<td>' + axisData[i] + '</td>'
  		    						  + '<td>' + series[0].data[i] + '</td>'
  		    						  + '<td>' + series[1].data[i] + '</td>' 
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
	        data: ['已完成订单','消费额']
	    },
	    xAxis: {
	    	type: 'category',
	    	name: '时间',							            							           
            position: 'right',
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
	    series:[ {
		            name: '已完成订单',
		            type:'line', 
		            itemStyle: {
		                normal: {
		                    color: "#008000"
		                  }
		                },
		            data: ordwcList
		       },
		       {
		            name: '消费额',
		            type:'line', 
		            itemStyle: {
		                normal: {
		                    color: "#FF6666"
		                  }
		                },
		             data: ordprice
		         }
		       ]

       }
    myGraph.setOption(optionGragh);
}


