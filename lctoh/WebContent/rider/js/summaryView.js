 //骑手订单管理页面
var rider;
initDate();
$(function(){
	//初始化时间  必须写在外面  日期控件依赖    lctoh/Common/easyui/jquery.min.js
	// initDate();
	 
	 
	init_rider();
    //点击查询按钮事件
    query_order_chart();
    //订单列表选项卡
    order_list_query();
    
    
    qureyLineInfo();
	 /*骑手订单统计饼图*/
	// pie_chart();
	 /*骑手订单统计过程线*/
	 //line_chart();	
	
    
 	//
    
 })
 function init_rider(){
	$.ajax({
		   type: "post",
		   data:null,
		   url:Common.getRiderLoginInfo,
		   dataType: "json",
		   async: false,
		   success:function(data){
			   if(data.state==1){
				   rider=data.responseInfo;
				   //setRidertoHtml(rider);
			   }
			   else{
				   layer.msg(data.responseInfo, {time:2500});
				   top.location.href=projectDirectory+"/Login/login.html";
			   }
		   },
		   error:function(errordate){}
		})
}
 
 
 
 
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
/*
 * 点击查询事件
 * */
function query_order_chart(){  //点击事件 查询订单画过程线及饼状图

	$('#btn_qurey').click(function(){
        qureyLineInfo();
	})
}
function qureyLineInfo(){
	/*获取时间*/
    var data=getTm();
    data.riderId=rider.riderId;
    //过程线
    $.ajax({
		   type: "post",
		   data:data,
		   url:Common.dispatchingStatistics,
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   line_chart(data.responseInfo);
			   }
			   else{
				   layer.msg(data.responseInfo, {time:2500});
			   }
		   },
		   error:function(errordate){}
		})
	//饼状
    $.ajax({
		   type: "post",
		   data:data,
		   url:Common.dispatchingStatisticsByState,
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   pie_chart(data.responseInfo);
			   }
			   else{
				   layer.msg(data.responseInfo, {time:2500});
			   }
		   },
		   error:function(errordate){}
		})
    //列表
    $.ajax({
		   type: "post",
		   data:data,
		   url:Common.findDispatching,
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   dispatchingToHtml(data.responseInfo);
			   }
			   else{
				   layer.msg(data.responseInfo, {time:2500});
			   }
		   },
		   error:function(errordate){}
		})
}

function dispatchingToHtml(dispatchList){
	var zzps=$("#zzps .tab_content .order_detaile");
	var zzpsStr='';
	var pswc=$("#pswc .tab_content .order_detaile");
	var pswcStr='';
	var ycdd=$("#ycdd .tab_content .order_detaile");
	var ycddStr='';
	
	var order;
	var res;
	var state;
	for(var i=0;i<dispatchList.legth;i++){
		order=dispatchList[i].dispatchingOrder;
		state=dispatchList[i].dispatchingState;
		res=order.orderRestaurant;
		var str;
		str="<div class='order_one' ><div class='ord_time'><div class='time_show'>" +
			"<span>"+getDate(order.orderCreatTime)+"</span><span>"+getTime(order.orderCreatTime)+"</span>" +
			"</div></div><div class='pic_img'></div><div class='border_bot'><div class='ord_cont'> " +
			"<div class='ord_title'>"+res.restaurantName+"</div><div class='ord_inf'></div><div class='ord_number'>" +
			"<span>订单号：</span> <span >"+order.orderId+"</span></div></div><div class='ord_state'> <span>"+state.stateInfo+"</span>" +
			"</div><div class='ord_money'><span>"+order.orderPrice+"元</span><span>在线支付</span></div>" +
			"<div class='ord_click ' ><input class='first_ord' id='"+order.orderId+"' type='button' name='' value='订单详情'></div></div></div>";
		if(state.stateId<110004){
			zzpsStr+=str;
		}else if(state.stateId=110004){
			pswcStr+=str;
		}else if(state.stateId>110004){
			ycddStr+=str;
		}
	}
	zzps.html(zzpsStr);
	pswc.html(pswcStr);
	ycdd.html(ycddStr);
	
	//点击显示订单详情
    $('.order_all .ord_click input').click(function(){
    	var orderId=$(this)[0].id;
    	order_detail(orderId);//订单详情弹窗页面显示
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
function  pie_chart(typelist){
	
	var pszDispatching={
            psnm:"配送中",
            num:"0"
         };
	var zapsDispatching={
            psnm:"配送中",
            num:"0"
         };
	var ycDispatching={
            psnm:"异常订单",
            num:"0"
         };
	for(var i=0;i<typelist;i++){
		if(typelist[i].type<110004){
			pszDispatching.num+=typelist[i].count;
		}else if(typelist[i].type=110004){
			zapsDispatching.num+=typelist[i].count;
		}else if(typelist[i].type>110004){
			ycDispatching.num+=typelist[i].count;
		}
		
	 }
	
       var data=[pszDispatching,zapsDispatching,ycDispatching];
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
function  line_chart(dataList){
	var myGraph=echarts.init(document.getElementById('mainGraph'));
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
    		ordwcList.push(item.count) 
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
	 var str='./orderDetail.html?orderId='+orderId;
     layer.open({
		  title: ['订单详情', 'font-size:18px;'],
		  type: 2,
		  shadeClose: true,
		  shade: 0.8,
		  area: ['800px', '630px'],
		  content: str  //iframe的url
	 }); 
}


	
