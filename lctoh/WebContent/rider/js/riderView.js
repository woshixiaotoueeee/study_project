//骑手的地图显示订单与地图相关联
$(function(){
	//初始化数据 获取可配送订单 配送订单 地图数据
	init_rider_map();
	//订单详情显示
	order_details();
	//订单与地图的交互操作
	edit_order_map();
})

function init_rider_map(){ //初始化数据 获取可配送订单 配送订单 地图数据
   
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
			       <div><span>配送费</span><span  class='order_dist'>6元</span></div>
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
		  shade:0,
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
			       <div><span>配送费</span><span  class='order_dist'>6元</span></div>
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
function edit_order_map(){ //订单与地图的交互操作

}