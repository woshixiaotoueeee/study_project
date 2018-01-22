package org.jxau.lctoh.trade.order.service;

import java.util.List;

import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.trade.order.dao.OrderDao;
import org.jxau.lctoh.trade.order.domain.Order;
import org.jxau.lctoh.trade.order.exception.OrderException;
import org.jxau.lctoh.user.customer.dao.CustomerDao;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OrderService")
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	
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

	
	/**
	 * 付款
	 * @param orderId
	 * @param customer
	 * @throws OrderException 
	 */
	public Customer payment(String orderId) throws OrderException {
		Order order=orderDao.findOrderByOrderId(orderId);
		Customer customer=order.getOrderCustomer();
		if(order.getOrderCustomer().getCustomerBalance().doubleValue()<order.getOrderPrice().doubleValue()){
			throw new OrderException(ErrorMSG.insufficienFunds);
		}
		customer.setCustomerBalance(customer.getCustomerBalance().subtract(order.getOrderPrice()));
		customerDao.updateCustomer(customer);
		orderDao.updateOrder(order);
		return customer;
	}
	
	
	
}
