 //骑手订单管理页面
 $(function(){
     /*初始化数据*/
	 init();
	 /*获取时间*/
	 getTm();
	 /*骑手订单统计饼图*/
	 pie_chart();
	 /*骑手订单统计过程线*/
	 line_chart();	
	//初始化时间  必须写在外面  日期控件依赖    lctoh/Common/easyui/jquery.min.js
	 initDate();
    //点击查询按钮事件
    query_order_chart();
    //订单列表选项卡
 	order_list_query();
    
 })
 initDate();
//订单列表选项卡函数
 function order_list_query(){
    $(".policy-tabs").tabs({
	//暂时用着
    width:$('.policy-tabs').parent().width(),      //选项卡容器宽度
    height:$('.policy-tabs').parent().height(),
    fit:true
             
    });
    $('.tabs-panels').css('border-color','#eee');
    $('.tabs').find('a').css({'height':'30px','line-height':'28px'});
    
    //点击显示订单详情
    $('.order_all .ord_click input').click(function(){
    	var orderId=123;
    	order_detail(orderId);//订单详情弹窗页面显示
    })
    
 }
 /*
 * 初始化数据
 * */
function init(){
	
}
/*
 * 获取时间
 * */
function getTm(){
	/*alert('获取时间');*/
}
/*
 * 点击查询事件
 * */
function query_order_chart(){  //点击事件 查询订单画过程线及饼状图

	$('#btn_qurey').click(function(){
        
         /*获取时间*/
	     getTm();
		 /*骑手订单统计饼图*/
		 pie_chart();
		 /*骑手订单统计过程线*/
		 line_chart();	
	})
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
		        orient: 'vertical',
		        left: 'right',
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

/*显示订单详情*/
function order_detail(orderId){
	 var str='./orderDetail.html';
     layer.open({
		  title: ['订单详情', 'font-size:18px;'],
		  type: 2,
		  shadeClose: true,
		  shade: 0.8,
		  area: ['800px', '630px'],
		  content: str  //iframe的url
	 }); 
}


	