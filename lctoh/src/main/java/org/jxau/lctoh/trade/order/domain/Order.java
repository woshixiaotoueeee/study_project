package org.jxau.lctoh.trade.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.jxau.lctoh.user.rider.domain.Dispatching;


/**
 * 订单
 * @author qdt_PC
 */
public class Order {
	/**订单ID*/
	private String orderId;
	/**下单时间*/
	private Date orderCreatTime;
	/**订单价格*/
	private BigDecimal orderPrice;
	/**订单更新时间*/
	private Date orderUpdateTiame;
	/**订单交付时间（完成）*/
	private Date orderDeliveryTime;
	/**
	 * 订单状态
	 * @see org.jxau.lctoh.state.domain.State
	 * */
	private State orderState;
	/**
	 * 配送地址
	 * @see org.jxau.lctoh.trade.order.domain.HarvestAddress
	 * */
	private HarvestAddress orderHarvestAddress;
	/**
	 * 订单所属用户
	 * @see org.jxau.lctoh.user.customer.domain.Customer
	 * */
	private Customer orderCustomer;
	/**
	 * 订单所属店家
	 * @see org.jxau.lctoh.user.restaurant.domain.Restaurant
	 * */
	private Restaurant orderRestaurant;
	/**
	 * 订单下所有条目
	 * @see org.jxau.lctoh.trade.order.domain.OrderItem
	 * */
	private List<OrderItem> orderItemList;
	
	/**
	 * 派送信息
	 * @see org.jxau.lctoh.trade.order.domain.OrderItem
	 * */
	private Dispatching orderDispatching;
	
	
	public Dispatching getOrderDispatching() {
		return orderDispatching;
	}
	public void setOrderDispatching(Dispatching orderDispatching) {
		this.orderDispatching = orderDispatching;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getOrderCreatTime() {
		return orderCreatTime;
	}
	public void setOrderCreatTime(Date orderCreatTime) {
		this.orderCreatTime = orderCreatTime;
	}
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Date getOrderUpdateTiame() {
		return orderUpdateTiame;
	}
	public void setOrderUpdateTiame(Date orderUpdateTiame) {
		this.orderUpdateTiame = orderUpdateTiame;
	}
	public Date getOrderDeliveryTime() {
		return orderDeliveryTime;
	}
	public void setOrderDeliveryTime(Date orderDeliveryTime) {
		this.orderDeliveryTime = orderDeliveryTime;
	}
	public State getOrderState() {
		return orderState;
	}
	public void setOrderState(State orderState) {
		this.orderState = orderState;
	}
	public HarvestAddress getOrderHarvestAddress() {
		return orderHarvestAddress;
	}
	public void setOrderHarvestAddress(HarvestAddress orderHarvestAddress) {
		this.orderHarvestAddress = orderHarvestAddress;
	}
	public Customer getOrderCustomer() {
		return orderCustomer;
	}
	public void setOrderCustomer(Customer orderCustomer) {
		this.orderCustomer = orderCustomer;
	}
	public Restaurant getOrderRestaurant() {
		return orderRestaurant;
	}
	public void setOrderRestaurant(Restaurant orderRestaurant) {
		this.orderRestaurant = orderRestaurant;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderCreatTime="
				+ orderCreatTime + ", orderPrice=" + orderPrice
				+ ", orderUpdateTiame=" + orderUpdateTiame
				+ ", orderDeliveryTime=" + orderDeliveryTime + ", orderState="
				+ orderState + ", orderHarvestAddress=" + orderHarvestAddress
				+ ", orderCustomer=" + orderCustomer + ", orderRestaurant=" + orderRestaurant
				+ ", orderItemList=" + orderItemList + "]";
	}
	
	
}
