 //管理员统计中心页面
var countCenter;
initDate();//初始化时间  必须写在外面  日期控件依赖    lctoh/Common/easyui/jquery.min.js
$(function(){
	

	//初始化商家个人中心的信息数据
	init_countCenter();

    //点击查询统计信息按钮事件
    query_chart();
  
	 /*订单统计饼图*/
	 // pie_chart();
	 /*用户统计饼图*/
	 // pie_user();
	 /*订单统计过程线*/
	 //line_chart();	
	 /*营业额统计柱状图*/
	 //bar_chart();
     
    
 })
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
function init_countCenter(){//初始化数据
    

}
/*
 *统计管理函数
 **/
function query_chart(){  //点击统计管理的查询按钮
    qureyChartInfo();//统计管理的查询事件
}
function qureyChartInfo(){//统计管理的查询事件
    /*订单统计饼图*/
	pie_chart();
    /*用户统计饼图*/
	pie_user();
	/*订单统计过程线*/
	line_chart();	
	/*营业额统计柱状图*/
	bar_chart();
}
/*
 * 统计饼图
 * */
function  pie_chart(){
	
	var pszDispatching={
            psnm:"配送中",
            num:"23"
         };
	var zapsDispatching={
            psnm:"配送完成",
            num:"32"
         };
	var ycDispatching={
            psnm:"异常订单",
            num:"10"
         };
    var data=[pszDispatching,zapsDispatching,ycDispatching];
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
	        right:20,
	        top:80,
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
function pie_user(){
	var pszDispatching={
            psnm:"客户",
            num:"93"
         };
	var zapsDispatching={
            psnm:"商家",
            num:"82"
         };
	var ycDispatching={
            psnm:"骑手",
            num:"20"
         };
    var data=[pszDispatching,zapsDispatching,ycDispatching];
    var myChartTwo = echarts.init(document.getElementById('userPie'));
    var optionTwo = {
	    title : {
	        text: '用户量饼图',
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
	        right:40,
	        top:80,
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
                         	 color: "#fa7c03"
                             }
                         }
                    },
	                {value:data[1].num, name:data[1].psnm,
                        itemStyle: {
                         normal: {
                         	 color: "#00FF00"
                             }
                         }
	                },
	                {value:data[2].num, name:data[2].psnm,
                         itemStyle: {
                            normal: {
                         	   color: "#ffaa1e"
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
function  line_chart(){
	var myGraph=echarts.init(document.getElementById('lineGraph'));
    var dataList=[
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
       ]; 
       //数据处理
    	var ordwcList = [];    //已完成的订单
    	var tms = [];
    	var item;
    	for(var i=0;i<dataList.length;i++){
    		item = dataList[i];
    		ordwcList.push(item.ordps); 
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
		                    color: "green" //#00FF66
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
function  bar_chart(){

   var myGraph=echarts.init(document.getElementById('barGraph'));
    var dataList=[
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
       ]; 
       //数据处理
    	var ordwcList = [];    //已完成的订单
    	var tms = [];
    	var item;
    	for(var i=0;i<dataList.length;i++){
    		item = dataList[i];
    		ordwcList.push(item.price); 
    		tms.push(item.tm);
    	}
      var optionGragh={
       	 title:{
            text:'营业额柱状图',
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
		                    color: "#fa7c03"
		                  }
		                },
		            data: ordwcList
		       }

       }
    myGraph.setOption(optionGragh);
}