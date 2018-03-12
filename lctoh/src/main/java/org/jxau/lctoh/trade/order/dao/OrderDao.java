package org.jxau.lctoh.trade.order.dao;

import java.util.Date;
import java.util.List;

import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatisticsQureyModel;
import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.jxau.lctoh.trade.order.domain.Order;
import org.jxau.lctoh.trade.order.mapper.OrderItemMapper;
import org.jxau.lctoh.trade.order.mapper.OrderMapper;
import org.springframework.stereotype.Repository;
/**
 * @author qdt_PC
 */
@Repository("OrderDao")
public class OrderDao extends BaseDao {
	
	private OrderMapper orderMapper;
	private OrderItemMapper orderItemMapper;
	public OrderMapper getOrderMapper() {
		return orderMapper;
	}
	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}
	public OrderItemMapper getOrderItemMapper() {
		return orderItemMapper;
	}
	public void setOrderItemMapper(OrderItemMapper orderItemMapper) {
		this.orderItemMapper = orderItemMapper;
	}
	@Override
	public void puttMapper() {
		orderMapper=this.getMapper(OrderMapper.class);
		orderItemMapper=this.getMapper(OrderItemMapper.class);
	}
	
	/**
	 * 根据订单ID查询订单
	 * @param orderId
	 * @return Order
	 */
	public Order findOrderByOrderId(String orderId){
		Order order =orderMapper.findOrderByOrderId(orderId);
		if(order==null)return order;
		return loadOrderItem(order);
	}
	/**
	 * 加载单个订单条目信息
	 * @param order
	 * @return
	 */
	private Order loadOrderItem(Order order){
		order.setOrderItemList(orderItemMapper.findOrderItemByOrderId(order.getOrderId()));
		return order;
	}
	
	/**
	 * 根据客户识别码查询订单
	 * @param custmerId
	 * @return
	 */
	public List<Order> findOrderByCustmerId(String custmerId){
		return orderMapper.findOrderByCustmerId(custmerId);
	}
	
	/**
	 * 根据店家识别码查询订单
	 * @param restaurantId
	 * @return
	 */
	public List<Order> findOrderByRestaurantId(String restaurantId){
		return orderMapper.findOrderByRestaurantId(restaurantId);
	}
	
	
	/**
	 * 更新订单
	 * @param order
	 * @return Integer
	 */
	public Integer updateOrder(Order order){
		order.setOrderUpdateTiame(new Date());
		return orderMapper.updateOrder(order);
	}
	
	/**
	 * 添加订单
	 * @param order
	 * @return Integer
	 */
	public Integer addOrder(Order order){
		order.setOrderUpdateTiame(new Date());
		return orderMapper.addOrder(order);
	}
	
	public List<Order> findOrder(OrderStatisticsQureyModel osqm){
		return orderMapper.findOrder(osqm);
	}
}
