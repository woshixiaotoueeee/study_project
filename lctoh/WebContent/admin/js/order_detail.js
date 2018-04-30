var project="/takeawayplatform";//项目名
var restaurant;
var orderListData;
var oid;
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
			oid=GetQueryString("oid");
			
			if(oid==null||oid.length==0)return ;
			
			$("div.wrap div.page-title span.fl em").html("订单编号："+oid);
			loadOrderByRestaurant(oid);//根据用户加载订单
		}
		
		
		
	},"JSON");
	
}

//根据店家加载订单
function loadOrderByRestaurant(oid){

	$.post(project+"/Orders/getOrdersById",{"oid":oid},function(data){
		putOrderItem(data);
	},"JSON");
}


//加载订单详细信息到页面
function putOrderItem(orders){
	
	var info=$("div.wrap dl.orderDetail dd ul li span.h-left");
	info[0].innerHTML=orders.customer.ctname;
	info[1].innerHTML=orders.customer.ctphone;
	info[2].innerHTML=orders.oadress;
	
	var dingdan =$("div.wrap dl.orderDetail dd div.BatchOperation");
	
	
	if(orders.ostate==1){
		info[3].innerHTML="未付款";
		dingdan.html("<input type='button' onclick='deleteOrder()' value='取消订单' class='btnStyle'/>");
	}else if(orders.ostate==2){
		info[3].innerHTML="待发货";
		dingdan.html("<input type='button' onclick='fahuo()' value='发货' class='btnStyle'/>");
		
	}else if(orders.ostate==3){
		info[3].innerHTML="待确认";
		dingdan.html("");
	}
	else if(orders.ostate==4){
		info[3].innerHTML="已完成";
		dingdan.html("<input type='button' onclick='deleteOrder()' value='删除订单' class='btnStyle'/>");
	}
	info[4].innerHTML=orders.otime;
	info[5].innerHTML=orders.odeliverytime;
	//加载条目
	loadItem(orders)
	$("div.wrap dl.orderDetail dd table.list-style tbody tr td div.fr span b").html(orders.ototalprice);
}



function loadItem(orders){
	var itemElement=$("div.wrap dl.orderDetail dd table.list-style tbody tr");
	//alert(item.length);
	
	for(var i=0;i<orders.orderItemList.length;i++){
		var item=$('<tr>');
		item.html("<td><img src='"+project+"/information/"+orders.orderItemList[i].dish.dimage+"' class='thumbnail'/></td><td>"+orders.orderItemList[i].dish.dname+"</td>"+
	      "<td><span><i>￥</i><em>"+orders.orderItemList[i].dish.dprice+"</em></span> </td><td>"+orders.orderItemList[i].oicount+"</td><td><span><i>￥</i><em>"+orders.orderItemList[i].oisum+"</em></span></td>")
	      itemElement.eq(0).after(item)
	}
}

function fahuo(){
	updateOrder(3);
}

function deleteOrder(){
	updateOrder(5);
}

function updateOrder(state){
	$.post(project+"/Orders/updateOrderstateByOid",{"oid":oid,"ostate":state},function(data){
		if(data==0)alert("操作失败");
		if(data==1){
			alert("操作成功");
			setTimeout(function(){self.location=project+"/restaurant_admin/order_list.html"},1000);
		}
		
		
	},"JSON");
}







//获取地址栏参数

/*
 * 这是用户获取首页跳转进来时传进来的店家ID方法
 * 
 * */

function GetQueryString(name)
{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
}
