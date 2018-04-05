$(function(){
	 /*初始化数据*/
	 init();
	 pie_chart();
	 line_chart();
})
/*
 * 初始化数据
 * */
function init(){
	
}
/*
 * 获取时间
 * */
function getTm(){
	
}
/*
 * 统计饼图
 * */
function  pie_chart(){
	   var data=[
	              {
		            psnm:"配送中",
		            tm:"2018-05-13 12:00",
		            num:"23"
		         },
		         {
		            psnm:"已完成配送",
		            tm:"2018-05-13 12:00",
		            num:"13"
		         },
		         {
		            psnm:"未完成配送",
		            tm:"2018-05-13 12:00",
		            num:"3"
		         }

	          ];
	       //alert(data[1].psnm);
	       var myChartTwo = echarts.init(document.getElementById('mainTwo'));
	       var optionTwo = {
		    title : {
		        text: '配送订单饼图',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: [data[0].psnm,data[1].psnm,data[2].psnm]
		    },
		    series : [
		        {
		            name: '配送信息',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[
		                {value:data[0].num, name:data[0].psnm},
		                {value:data[1].num, name:data[1].psnm},
		                {value:data[2].num, name:data[2].psnm}
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
	var myGraph=echarts.init(document.getElementById('mainGraph'));
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
    	for(var i=0;i<dataList.length;i++){
    		var item = dataList[i];
    		ordwcList.push(item.ordwc) 
    		tms.push(item.tm);
    	}
      var optionGragh={
       	 title:{
            text:'完成订单过程线',
            x:'center',
            top: '5px',
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


