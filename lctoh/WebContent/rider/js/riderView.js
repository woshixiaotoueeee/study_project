//地图
var map;
var riderMapMk;
window.onload=function(){
	setMap();
	dingwei();
	getdispatching();
	var timeid = window.setInterval(function(){
		dingwei();
		//getdispatching();
	},30000);
	//订单详情显示
	//order_details();
}

function setDispatchingzzps(dispatchingzzpsList){
	var sendOrder= $('.being_order').find('.order_list');
	sendOrder.html('');
	var strHtm='';
	var res;
	var address;
	for(var i=0;i<dispatchingzzpsList.length;i++){
		res=dispatchingzzpsList[i].dispatchingOrder.orderRestaurant;
		address=dispatchingzzpsList[i].dispatchingOrder.orderHarvestAddress;
		strHtm+="<div class='order_one'  order_id='"+dispatchingzzpsList[i].dispatchingOrder.orderId+"'><div class='order_same order_top'><div class='img_shop'>" +
				"<img src='../"+res.restaurantImage+"'></div> <div class='order_title'>"+res.restaurantName+"</div>" +
				"</div><div class='order_same order_bot' ><div class='order_id'>订单号:<span class='order_number'>"+dispatchingzzpsList[i].dispatchingOrder.orderId+"</span></div>" +
				"<div class='order_tm'>下单时间：<span>"+dateFtt("yyyy-MM-dd hh:mm",new Date(dispatchingzzpsList[i].dispatchingOrder.orderCreatTime))+"</span></div>" +
				"</div><div class='order_same order_cont'><div class='order_custer'>顾客：<span>" +address.harvestAddressName+
				"</span></div><div class='order_custer_address'>地址：<span>" +address.harvestAddressInfo+
				"</span></div></div><div class='order_same order_cont'><div class='order_store_address'>商家地址：<span>"+res.restaurantAddressInfo+"</span></div></div></div>";
	}
	sendOrder.html(strHtm);
	//可配送订单  .being_order
	$('.being_order').find('.order_list .order_one').click(function(){
		var id=$(this).find('.order_same .order_id .order_number').html();
		$.ajax({
			   type: "post",
			   data:{'dispatchingId':id},
			   url:Common.getDispatchingById,
			   dataType: "json",
			   success:function(data){
				   if(data.state==1){
					   setDispatchingzzpstoMap(data.responseInfo);
				   }
				   else{
					   layer.msg(data.responseInfo, {time:2500});
				   }
			   },
			   error:function(errordate){
				   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
			   }
			})
	})
	
}

function getdispatching(){
	$.ajax({
		   type: "post",
		   data:{'stateId':110001},
		   url:Common.getDispatchingByState,
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   setDispatchingdps(data.responseInfo);
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
		   data:null,
		   url:Common.getRiderDispatching,
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   setDispatchingzzps(data.responseInfo);
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
function setDispatchingzzpstoMap(dispatchingzzps){
	
	var customerIcon = new BMap.Icon("/lctoh/images/p.png", new BMap.Size(50,50));
	var restaurantIcon = new BMap.Icon("/lctoh/images/shop.png", new BMap.Size(50,50));
	
	var res=dispatchingzzps.dispatchingOrder.orderRestaurant;
	var address=dispatchingzzps.dispatchingOrder.orderHarvestAddress;
	
	var customerPoint=new BMap.Point(address.harvestAddressLongitude,address.harvestAddressLatitude);
	var customermk=new BMap.Marker(customerPoint,{icon:customerIcon});
	
	map.addOverlay(customermk);
	
	var resPoint=new BMap.Point(res.restaurantLongitude,res.restaurantLatitude);
	var resmk=new BMap.Marker(resPoint,{icon:restaurantIcon});
	map.addOverlay(resmk);
	
	
	
	
	var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});
	driving.search(resPoint, customerPoint);
	openlayerzzps(res,address,dispatchingzzps.dispatchingOrder,dispatchingzzps.dispatchingState.stateId);
}
function openlayerzzps(res,address,order,stateId){
	var str_being_order=`<div class='show_order'>
			    <div class='order_infor'>
				   <label>订单号</label>
				   <span class='order_idnum'>#orderId#</span>
			    </div>
			    <div class='order_infor'>
				   <label>顾客信息</label>
				   <div class='order_cust_infor'>
				       <div class='order_cust_name'><span>#customerName#</span><span class='custer_phone'>#customerPhone#</span></div>
				       <div class='order_cust_address'><span>地址:</span><span>#customerAddress#</span></div>
				   </div>	
			    </div>
			    <div class='order_infor'>
				   <label>配送信息</label>
				   <div class='order_dispatching'>
				       <div><span>#restaurantName#</span><span  class='store_phone'>#restaurantPhone#</span></div>
				       <div><span>商家地址:</span><span>#restaurantAddress#</span></div>
				       <div><span>本单总价</span><span  class='order_total'>#orderPrice#元</span></div>
				   </div>	
			    </div>
			    <div class='order_infor'>
				   <label>下单时间</label>
				   <div class='order_tm'>
				      <span>#orderTime#</span>
				   </div>	
			    </div>
			    <div class='order_infor taking_refuse'>
				    <input type='button' statetype='#statetype#' orderId="#order_id#" class='order_taking #order_id#' id='order_taking' value='#button_value#'/>
			    </div>	
			</div>`;
			
		
	str_being_order=str_being_order.replace("#orderId#",order.orderId);
	str_being_order=str_being_order.replace("#order_id#",order.orderId);
	str_being_order=str_being_order.replace("#order_id#",order.orderId);
	str_being_order=str_being_order.replace("#customerName#",address.harvestAddressName);	
	str_being_order=str_being_order.replace("#customerPhone#",address.harvestAddressPhone);	
	str_being_order=str_being_order.replace("#customerAddress#",address.harvestAddressInfo);
	
	str_being_order=str_being_order.replace("#restaurantName#",res.restaurantName);	
	str_being_order=str_being_order.replace("#restaurantPhone#",res.restaurantPhone);
	

	
	str_being_order=str_being_order.replace("#restaurantAddress#",res.restaurantAddressInfo);	
	str_being_order=str_being_order.replace("#orderPrice#",order.orderPrice);
	str_being_order=str_being_order.replace("#orderTime#",dateFtt("yyyy-MM-dd hh:mm",new Date(order.orderCreatTime)));	
	
	
	if(stateId==110002){
		str_being_order=str_being_order.replace("#button_value#",'取餐');	
		str_being_order=str_being_order.replace("#statetype#",2);
	}else if(stateId==110003){
		str_being_order=str_being_order.replace("#button_value#",'确认送达');	
		str_being_order=str_being_order.replace("#statetype#",3);
	}
	
	//正在配送订单
	var layer_order=layer.open({
		title: ['可配送订单详情', 'font-size:14px;'],
		type: 1,
		area: ['344px', '370px'], //宽高
		shade:0.2,
		content: str_being_order
	});
	
	$("."+order.orderId).click(function(){
		var id=$(this).attr('orderId');
		var state=$(this).attr('statetype');
		$.ajax({
			   type: "post",
			   data:{'dispatchingId':id,"state":state},
			   url:Common.updateDispatchingState,
			   dataType: "json",
			   success:function(data){
				   layer.msg(data.responseInfo, {time:2500});
				   if(data.state==1){
					   getdispatching();
				   }
				   layer.close(layer_order);
			   },
			   error:function(errordate){
				   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
				   layer.close(layer_order);
			   }
			})
	})
	
	
}






function setDispatchingdps(dispatchingdpsList){
	var sendOrder= $('.send_order').find('.order_list');
	sendOrder.html('');
	var strHtm='';
	var res;
	var address;
	for(var i=0;i<dispatchingdpsList.length;i++){
		res=dispatchingdpsList[i].dispatchingOrder.orderRestaurant;
		address=dispatchingdpsList[i].dispatchingOrder.orderHarvestAddress;
		strHtm+="<div class='order_one' order_id='"+dispatchingdpsList[i].dispatchingOrder.orderId+"'><div class='order_same order_top'><div class='img_shop'>" +
				"<img src='../"+res.restaurantImage+"'></div> <div class='order_title'>"+res.restaurantName+"</div>" +
				"</div><div class='order_same order_bot' ><div class='order_id'>订单号:<span class='order_number'>"+dispatchingdpsList[i].dispatchingOrder.orderId+"</span></div>" +
				"<div class='order_tm'>下单时间：<span>"+dateFtt("yyyy-MM-dd hh:mm",new Date(dispatchingdpsList[i].dispatchingOrder.orderCreatTime))+"</span></div>" +
				"</div><div class='order_same order_cont'><div class='order_custer'>顾客：<span>" +address.harvestAddressName+
				"</span></div><div class='order_custer_address'>地址：<span>" +address.harvestAddressInfo+
				"</span></div></div><div class='order_same order_cont'><div class='order_store_address'>商家地址：<span>"+res.restaurantAddressInfo+"</span></div></div></div>";
	}
	sendOrder.html(strHtm);
	//可配送订单  .send_order
	$('.send_order').find('.order_list .order_one').click(function(){
		//var id=$(this).attr('order_id');
		var id=$(this).find('.order_same .order_id .order_number').html();
		$.ajax({
			   type: "post",
			   data:{'dispatchingId':id},
			   url:Common.getDispatchingById,
			   dataType: "json",
			   success:function(data){
				   if(data.state==1){
					   setDispatchingdpstoMap(data.responseInfo);
				   }
				   else{
					   layer.msg(data.responseInfo, {time:2500});
				   }
			   },
			   error:function(errordate){
				   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
			   }
			})
	})
}
function setDispatchingdpstoMap(dispatchingdps){

	var customerIcon = new BMap.Icon("/lctoh/images/p.png", new BMap.Size(50,50));
	var restaurantIcon = new BMap.Icon("/lctoh/images/shop.png", new BMap.Size(50,50));
	
	
	var res=dispatchingdps.dispatchingOrder.orderRestaurant;
	var address=dispatchingdps.dispatchingOrder.orderHarvestAddress;
	var customerPoint=new BMap.Point(address.harvestAddressLongitude,address.harvestAddressLatitude);
	var customermk=new BMap.Marker(customerPoint,{icon:customerIcon});
	map.addOverlay(customermk);
	
	var resPoint=new BMap.Point(res.restaurantLongitude,res.restaurantLatitude);
	var resmk=new BMap.Marker(resPoint,{icon:restaurantIcon});
	map.addOverlay(resmk);
	var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});
	driving.search(resPoint, customerPoint);
	openlayer(res,address,dispatchingdps.dispatchingOrder);
}
function openlayer(res,address,order){
	var str_send_order=`<div class='show_order'>
			    <div class='order_infor'>
				   <label>订单号</label>
				   <span class='order_idnum'>#orderId#</span>
			    </div>
			    <div class='order_infor'>
				   <label>顾客信息</label>
				   <div class='order_cust_infor'>
				       <div class='order_cust_name'><span>#customerName#</span><span class='custer_phone'>#customerPhone#</span></div>
				       <div class='order_cust_address'><span>地址:</span><span>#customerAddress#</span></div>
				   </div>	
			    </div>
			    <div class='order_infor'>
				   <label>配送信息</label>
				   <div class='order_dispatching'>
				       <div><span>#restaurantName#</span><span  class='store_phone'>#restaurantPhone#</span></div>
				       <div><span>商家地址:</span><span>#restaurantAddress#</span></div>
				       <div><span>本单总价</span><span  class='order_total'>#orderPrice#元</span></div>
				   </div>	
			    </div>
			    <div class='order_infor'>
				   <label>下单时间</label>
				   <div class='order_tm'>
				      <span>#orderTime#</span>
				   </div>	
			    </div>
			    <div class='order_infor taking_refuse'>
				    <input type='button' orderId="#order_id#" class='order_taking #order_id#' id='order_taking' value='接单'/>
			    </div>	
			</div>`;
			
		
	str_send_order=str_send_order.replace("#orderId#",order.orderId);
	str_send_order=str_send_order.replace("#order_id#",order.orderId);
	str_send_order=str_send_order.replace("#order_id#",order.orderId);
	str_send_order=str_send_order.replace("#customerName#",address.harvestAddressName);	
	str_send_order=str_send_order.replace("#customerPhone#",address.harvestAddressPhone);	
	str_send_order=str_send_order.replace("#customerAddress#",address.harvestAddressInfo);
	
	str_send_order=str_send_order.replace("#restaurantName#",res.restaurantName);	
	str_send_order=str_send_order.replace("#restaurantPhone#",res.restaurantPhone);
	

	
	str_send_order=str_send_order.replace("#restaurantAddress#",res.restaurantAddressInfo);	
	str_send_order=str_send_order.replace("#orderPrice#",order.orderPrice);
	str_send_order=str_send_order.replace("#orderTime#",dateFtt("yyyy-MM-dd hh:mm",new Date(order.orderCreatTime)));	

	//可配送订单  .send_order
	var layer_order=layer.open({
		title: ['可配送订单详情', 'font-size:14px;'],
		type: 1,
		area: ['344px', '370px'], //宽高
		shade:0.2,
		content: str_send_order
	});
	
	$("."+order.orderId).click(function(){
		var id=$(this).attr('orderId');
		$.ajax({
			   type: "post",
			   data:{'dispatchingId':id,"state":1},
			   url:Common.updateDispatchingState,
			   dataType: "json",
			   success:function(data){
				   layer.msg(data.responseInfo, {time:2500});
				   if(data.state==1){
					   getdispatching();
				   }
				   layer.close(layer_order);
			   },
			   error:function(errordate){
				   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
				   layer.close(layer_order);
			   }
			})
	})
	
	
}

/**自动定位*/
function dingwei(){
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			//清除覆盖物
			map.removeOverlay(riderMapMk);
			var myIcon = new BMap.Icon("/lctoh/images/gogo.png", new BMap.Size(50,50));
			
			riderMapMk = new BMap.Marker(r.point,{icon:myIcon});
			if(r.address.province==''||r.address.city==''){
				alert("请选择正确的位置");
				return;
			}
			var _location={};
			
			
			
			
			//map.clearOverlays();
			map.addOverlay(riderMapMk);
			//map.panTo(r.point);
			riderMapMk.setAnimation(BMAP_ANIMATION_BOUNCE);
			_location.riderLongitude=r.point.lng;
			_location.riderLatitude=r.point.lat;
			sendTosession(_location);
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
}
function sendTosession(locationInfo){
	$.ajax({
		   type: "post",
		   data:locationInfo,
		   url:Common.riderLocation,
		   dataType: "json",
		   success:function(data){},
		   error:function(errordate){}
		})
}



function setMap(){
	map=new BMap.Map("rider_map");//初始化地图
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,11);
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	//添加地图类型控件
	map.addControl(new BMap.MapTypeControl({
		mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
           ]}));
	//自动定位城市
	var myCity = new BMap.LocalCity();
	myCity.get(myFun);
	//自动定位城市
	function myFun(result){
		map.setCenter(result.name);
	}
}
	
function order_details(){ //点击订单列表显示订单详情
	var str_send_order=`<div class='show_order'>
		    <div class='order_infor'>
			   <label>订单号</label>
			   <span class='order_idnum'>328344sss2226dsdsds67787874857</span>
		    </div>
		    <div class='order_infor'>
			   <label>顾客信息</label>
			   <div class='order_cust_infor'>
			       <div class='order_cust_name'><span>刘贵平</span><span class='custer_phone'>17610825887</span></div>
			       <div class='order_cust_address'><span>地址:</span><span>昌平沙河老牛湾6号道7栋301</span></div>
			   </div>	
		    </div>
		    <div class='order_infor'>
			   <label>配送信息</label>
			   <div class='order_dispatching'>
			       <div><span>老王</span><span  class='store_phone'>17610867887</span></div>
			       <div><span>商家地址:</span><span>昌平沙河老牛湾6号道7栋301</span></div>
			       <div><span>本单总价</span><span  class='order_total'>27元</span></div>
			   </div>	
		    </div>
		    <div class='order_infor'>
			   <label>下单时间</label>
			   <div class='order_tm'>
			      <span>2018-05-12 11:50</span>
			   </div>	
		    </div>
		    <div class='order_infor taking_refuse'>
			    <input type='button' class='order_taking' id='order_taking' value='接单'/>

		    </div>	
		</div>`;
		
   //可配送订单  .send_order
   $('.send_order').find('.order_list .order_one').click(function(){
	
   	   var layer_order=layer.open({
		  title: ['可配送订单详情', 'font-size:14px;'],
		  type: 1,
		  area: ['344px', '370px'], //宽高
		  shade:0.2,
		  content: str_send_order
		});
   	   receipt_refuse(layer_order)  //接单或拒绝订单

   })   
   //正在配送订单弹窗信息
   var str_being_order=`<div class='show_order'>
		    <div class='order_infor'>
			   <label>订单号</label>
			   <span class='order_idnum'>32874857</span>
		    </div>
		    <div class='order_infor'>
			   <label>顾客信息</label>
			   <div class='order_cust_infor'>
			       <div class='order_cust_name'><span>刘贵平</span><span class='custer_phone'>17610825887</span></div>
			       <div class='order_cust_address'><span>地址:</span><span>昌平沙河老牛湾6号道7栋301</span></div>
			   </div>	
		    </div>
		    <div class='order_infor'>
			   <label>配送信息</label>
			   <div class='order_dispatching'>
			       <div><span>老王</span><span  class='store_phone'>17610867887</span></div>
			       <div><span>商家地址:</span><span>昌平沙河老牛湾6号道7栋301</span></div>
			       <div><span>本单总价</span><span  class='order_total'>27元</span></div>
			   </div>	
		    </div>
		    <div class='order_infor'>
			   <label>下单时间</label>
			   <div class='order_tm'>
			      <span>2018-05-12 11:50</span>
			   </div>	
		    </div>
		    <div class='order_infor taking_refuse'>
			    <input type='button' class='order_taking' id='order_taking' value='接单'/>

		    </div>	
		</div>`;
		//点击正在配送订单列表
     $('.being_order').find('.order_list .order_one').click(function(){
   	   var layer_order=layer.open({
		  title: ['正在配送订单详情', 'font-size:14px;'],
		  type: 1,
		  area: ['344px', '370px'], //宽高
		  shade:0,
		  content: str_being_order
		});
   	   receipt_refuse(layer_order)  //接单或拒绝订单

   })   
   
 
}
function  receipt_refuse(layer_order){  //接单或拒绝订单
	//接单
    $('#order_taking').click(function(){
       alert('接单成功');
       layer.close(layer_order);
    })
    
}
