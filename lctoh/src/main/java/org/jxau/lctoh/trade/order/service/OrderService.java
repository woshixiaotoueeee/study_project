package org.jxau.lctoh.trade.order.service;

import java.util.Date;
import java.util.List;

import org.jxau.lctoh.trade.order.dao.OrderDao;
import org.jxau.lctoh.trade.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OrderService")
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	
	
	
	
	/**
	 * 根据订单ID查询订单
	 * @param orderId
	 * @return Order
	 */
	public Order findOrderByOrderId(String orderId){
		return orderDao.findOrderByOrderId(orderId);
	}

	
	/**
	 * 根据客户识别码查询订单
	 * @param custmerId
	 * @return
	 */
	public List<Order> findOrderByCustmerId(String custmerId){
		return orderDao.findOrderByCustmerId(custmerId);
	}
	
	/**
	 * 根据店家识别码查询订单
	 * @param restaurantId
	 * @return
	 */
	public List<Order> findOrderByRestaurantId(String restaurantId){
		return orderDao.findOrderByRestaurantId(restaurantId);
	}
	
	
	/**
	 * 更新订单
	 * @param order
	 * @return Integer
	 */
	public Integer updateOrder(Order order){
		return orderDao.updateOrder(order);
	}
	
	/**
	 * 添加订单
	 * @param order
	 * @return Integer
	 */
	public Integer addOrder(Order order){
		return orderDao.addOrder(order);
	}
	
	
	
}
