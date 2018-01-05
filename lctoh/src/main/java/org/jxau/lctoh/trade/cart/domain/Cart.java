package org.jxau.lctoh.trade.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.trade.cart.exception.CartException;
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
	 */
	private Restaurant restaurant;
	//private BigDecimal total;
	/**购物车中的条目*/
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
		BigDecimal sum=new BigDecimal("0");
		for(CartItem cartItem : map.values()){
			sum=sum.add(cartItem.getSubtotal());
		}
		return sum;
	}
	/**
	 * 添加商品
	 */
	public void addDish(CartItem cartItem){
		/**
		 * 判断添加的商品在购物车中是否存在
		 */
		//商品在购物车中有条目
		if(map.containsKey(cartItem.getDish().getDishId())){
			//存在的条目
			CartItem _cartItem=map.get(cartItem.getDish().getDishId());
			//存在条目的数量+1
			_cartItem.setDishCount(_cartItem.getDishCount()+cartItem.getDishCount());
			_cartItem.putSubtotal();;
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
	}
	
	/**
	 * 更新购物车
	 * @throws CartException 
	 */
	public void updateDish(CartItem cartItem) throws CartException{
		if(restaurant==null||(!(restaurant.getRestaurantId().equals(cartItem.getDish().getDishCategory().getDishCategoryRestaurant().getRestaurantId())))){
			throw new CartException("购物车物品更新失败");
		}
		map.put(cartItem.getDish().getDishId(), cartItem);
	}
	/**
	 * 购物车中存在多个条目
	 */
	public Collection<CartItem> getCartItemList(){
		return map.values();
	}
	
	/**
	 * 删除商品、清空购物车
	 */
	public void deleteDish(String did){
		map.remove(did);
	} 
	public void clear(){
		map.clear();
	}
	
	public void toOrder(){
		
	}
}
