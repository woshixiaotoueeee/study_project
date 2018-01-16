package org.jxau.lctoh.trade.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.trade.dish.domain.Dish;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 订单条目
 * @author qdt_PC
 */
@Alias("OrderItem")
@Scope("prototype")
@Component
public class OrderItem {
	/**订单条目ID*/
	private String orderItemid;
	/**条目小计*/
	private BigDecimal orderItemSum;
	/**条目中商品数量*/
	private Integer orderItemCount;
	/**
	 * 条目商品信息
	 * @see org.jxau.lctoh.trade.dish.domain.Dish
	 * */
	private Dish orderItemDish;			//
	/**
	 * 条目所属订单
	 * @see org.jxau.lctoh.trade.order.domain.Order
	 * */
	private Order orderItemOrder;
	/**最近更新时间*/
	private Date orderItemUpdateTime;
	public String getOrderItemid() {
		return orderItemid;
	}
	public void setOrderItemid(String orderItemid) {
		this.orderItemid = orderItemid;
	}
	public BigDecimal getOrderItemSum() {
		return orderItemSum;
	}
	public void setOrderItemSum(BigDecimal orderItemSum) {
		this.orderItemSum = orderItemSum;
	}
	public Integer getOrderItemCount() {
		return orderItemCount;
	}
	public void setOrderItemCount(Integer orderItemCount) {
		this.orderItemCount = orderItemCount;
	}
	public Dish getOrderItemDish() {
		return orderItemDish;
	}
	public void setOrderItemDish(Dish orderItemDish) {
		this.orderItemDish = orderItemDish;
	}
	public Order getOrderItemOrder() {
		return orderItemOrder;
	}
	public void setOrderItemOrder(Order orderItemOrder) {
		this.orderItemOrder = orderItemOrder;
	}
	public Date getOrderItemUpdateTime() {
		return orderItemUpdateTime;
	}
	public void setOrderItemUpdateTime(Date orderItemUpdateTime) {
		this.orderItemUpdateTime = orderItemUpdateTime;
	}
	
	@Override
	public String toString() {
		return "OrderItem [orderItemid=" + orderItemid + ", orderItemSum="
				+ orderItemSum + ", orderItemCount=" + orderItemCount
				+ ", orderItemDish=" + orderItemDish + ", orderItemOrder="
				+ orderItemOrder + ", orderItemUpdateTime="
				+ orderItemUpdateTime + "]";
	}
	
	
}