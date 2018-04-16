$(function(){
	 /*初始化数据*/
	 init();
	 /*统计饼图*/
	 pie_chart();
})
/*
 * 初始化数据
 * */
function init(){
	
}
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
		            psnm:"已取消",
		            tm:"2018-05-13 12:00",
		            num:"3"
		         }

	          ];
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
		    legend: {
		        //orient: 'vertical',
		         bottom: 0,
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