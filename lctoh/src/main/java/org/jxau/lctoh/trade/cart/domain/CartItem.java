package org.jxau.lctoh.trade.cart.domain;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.trade.dish.domain.Dish;
import org.jxau.lctoh.trade.order.domain.Order;
import org.jxau.lctoh.trade.order.domain.OrderItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 购物车条目
 * @author qdt_PC
 */
@Component
@Alias("CartItem")
@Scope("prototype")
public class CartItem {
	private Dish dish;			//商品
	private Integer dishCount;	// 数量的属性，在条目中默认是1
	private BigDecimal subtotal;//小计
	public Dish getDish() {
		return dish;
	}
	public Integer getDishCount() {
		return dishCount;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	public void setDishCount(Integer dishCount) {
		this.dishCount = dishCount;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	/**计算小计金额*/
	public void putSubtotal(){
		subtotal=dish.getDishPrice().multiply(BigDecimal.valueOf(dishCount));
	}
	@Override
	public String toString() {
		return "CartItem [dish=" + dish + ", dishCount=" + dishCount + ", subtotal="
				+ subtotal + "]";
	}
	
	/**
	 * 根据购物车条目生成订单条目
	 * @param order
	 * @return
	 */
	public OrderItem toOrderItem(Order order){
		OrderItem orderItem=new OrderItem();
		orderItem.setOrderItemid(Tools.getRandomString(32));
		orderItem.setOrderItemSum(subtotal);
		orderItem.setOrderItemCount(dishCount);
		orderItem.setOrderItemDish(dish);
		orderItem.setOrderItemOrder(order);
		return orderItem;
	}
	
}
