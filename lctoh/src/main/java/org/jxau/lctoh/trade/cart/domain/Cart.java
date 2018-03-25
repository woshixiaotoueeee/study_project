package org.jxau.lctoh.trade.cart.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.trade.cart.exception.CartException;
import org.jxau.lctoh.trade.order.domain.HarvestAddress;
import org.jxau.lctoh.trade.order.domain.Order;
import org.jxau.lctoh.trade.order.domain.OrderItem;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * 购物车
 * @author qdt_PC
 */
@Component
@Alias("Cart")
@Scope("prototype")
public class Cart {
	/**
	 * 携带一个标记位
	 * @see org.jxau.lctoh.user.restaurant.domain.Restaurant
	 */
	private Restaurant restaurant;
	/**
	 * 购物车商品价格+配送费
	 * */
	private BigDecimal total;
	/**
	 * 购物车中的条目
	 * @see org.jxau.lctoh.trade.cart.domain.CartItem
	 * */
	private Map<String ,CartItem> map=new LinkedHashMap<String,CartItem>();
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setRestaurantId(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	
	
	
	/**
	 * 根据购物车中商品计算总价
	 * @return BigDecimal
	 */
	public BigDecimal getTotal(){
		return total;
	}
	
	public void putTotal() throws CartException{
		try{
			BigDecimal sum=restaurant.getRestaurantDeliveryFee();
			for(CartItem cartItem : map.values()){
				sum=sum.add(cartItem.getSubtotal());
			}
			total=sum;
		}catch(Exception e){
			e.printStackTrace();
			throw new CartException(ErrorMSG.putCartTotalFail);
		}
	}
	
	/**
	 * 添加商品
	 * @throws Exception 
	 */
	public void addDish(CartItem cartItem) throws Exception{
		/**
		 * 判断添加的商品在购物车中是否存在
		 */
		//商品在购物车中有条目
		if(map.containsKey(cartItem.getDish().getDishId())){
			//存在的条目
			CartItem _cartItem=map.get(cartItem.getDish().getDishId());
			//存在条目的数量+1
			_cartItem.setDishCount(_cartItem.getDishCount()+cartItem.getDishCount());
			_cartItem.putSubtotal();
			map.put(cartItem.getDish().getDishId(), _cartItem);
		}
		//商品在购物车中没有条目
		else{
			/*
			 * 判断购物车中的商家标记是否存在
			 * 	不存在 则添加然后将商品放入购物车
			 * 	存在则判断是否相同
			 * 		不同则清空购物车重新设置商家标记
			 * 		相同则直接将商品放入购物车
			 * */
			if(restaurant==null||restaurant.getRestaurantId()==null){
				restaurant=cartItem.getDish().getDishCategory().getDishCategoryRestaurant();
			}else if(!(restaurant.getRestaurantId().equals(cartItem.getDish().getDishCategory().getDishCategoryRestaurant().getRestaurantId()))){
				restaurant=cartItem.getDish().getDishCategory().getDishCategoryRestaurant();
				this.clear();
			}
			map.put(cartItem.getDish().getDishId(), cartItem);
		}
		this.putTotal();
	}
	
	/**
	 * 更新购物车
	 * @throws Exception 
	 */
	public void updateDish(CartItem cartItem) throws Exception{
		if(restaurant==null||(!(restaurant.getRestaurantId().equals(cartItem.getDish().getDishCategory().getDishCategoryRestaurant().getRestaurantId())))){
			throw new Exception();
		}
		map.put(cartItem.getDish().getDishId(), cartItem);
		this.putTotal();
	}
	/**
	 * 购物车中存在多个条目
	 */
	public Collection<CartItem> getCartItemList(){
		return map.values();
	}
	
	/**
	 * 删除商品、清空购物车
	 * @throws CartException 
	 */
	public void deleteDish(String dishId) throws CartException{
		map.remove(dishId);
		this.putTotal();
	} 
	public void clear(){
		restaurant=null;
		total=null;
		map.clear();
	}
	
	
	/**
	 * 根据购物车生成订单
	 * @param orderCustomer
	 * @param harvestAddress
	 * @return Order
	 */
	public Order toOrder(Customer orderCustomer){
		// 创建订单对象
		Order order = new Order();
		order.setOrderId(Tools.getRandomString(32));// 设置订单编号
		order.setOrderCreatTime(new Date());		// 下单时间
		order.setOrderPrice(total);	//订单价格
		State orderState=new State(100001);			//订单状态
		order.setOrderState(orderState);			//设置未付款状态
		
		order.setOrderCustomer(orderCustomer);		//订单所属用户
		//order.setOrderHarvestAddress(harvestAddress);//配送地址
		order.setOrderRestaurant(restaurant);		//订单所属餐馆
		// 创建订单条目
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		order.setOrderPrice(this.getTotal());
		for(CartItem cartItem:map.values()){
			orderItemList.add(cartItem.toOrderItem(order));
		}
		order.setOrderItemList(orderItemList);
		return order;
	}
}
