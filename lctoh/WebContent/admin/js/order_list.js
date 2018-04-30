var project="/takeawayplatform";//项目名
var restaurant;
var orderListData;
//加载初始数据
window.onload=function(){ 
	$.post(project+"/RestaurantController/getRestaurant",{},function(data){
		if(data==null){
			if(top.location.href!=self.location.href){
				top.location.href=project+"/res/login.html";
			}
			top.location.href=project+"/res/login.html";
		}else{
			restaurant=data;
			loadOrderByRestaurant();//根据用户加载订单
		}
		
		
		
	},"JSON");
	
}

//根据店家加载订单
function loadOrderByRestaurant(){
	$.post(project+"/Orders/getRetOrdersById",{"rtid":restaurant.rtid},function(data){
		putOrder(data);
	
		
		//orderListData=data;
	},"JSON");
	
}


//加载地址信息带页面
function putOrder(orderList){
	//遍历加载
	var msg="<tr><th>订单编号</th><th>下单时间</th><th>订单状态</th><th>收件人地址</th><th>订单金额</th></tr>";
	for(var i=0;i<orderList.length&&orderList[i].ostate!=5;i++){
		msg+="<tr><td class='center'><a href='order_detail.html?oid="+orderList[i].oid+"' >"+orderList[i].oid+"</a></td>"+
			"<td>"+orderList[i].otime+"</td><td>";
	     "<tr><td>订单状态：";
	 	if(orderList[i].ostate==1){
	 		msg+="未付款"	;
	 	}else if(orderList[i].ostate==2){
	 		msg+="已付款，待发货"	;
	 	}else if(orderList[i].ostate==3){
	 		msg+="已发货"	;
	 	}
	 	else if(orderList[i].ostate==4){
	 		msg+="已完成"	;
	 	}
		msg+="</td><td><address>"+orderList[i].oadress+"</address></td>"+
			"<td class='center'><strong class='rmb_icon'>"+orderList[i].ototalprice+"</strong></td></tr>";
	} 
	$(".list-style").html(msg);
}

