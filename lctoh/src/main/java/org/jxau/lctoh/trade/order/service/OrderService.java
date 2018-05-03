package org.jxau.lctoh.trade.order.service;

import java.util.Date;
import java.util.List;

import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatisticsQureyModel;
import org.jxau.lctoh.position.address.domain.Address;
import org.jxau.lctoh.position.address.service.AddressService;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.trade.order.dao.HarvestAddressDao;
import org.jxau.lctoh.trade.order.dao.OrderDao;
import org.jxau.lctoh.trade.order.domain.HarvestAddress;
import org.jxau.lctoh.trade.order.domain.Order;
import org.jxau.lctoh.trade.order.exception.OrderException;
import org.jxau.lctoh.user.customer.dao.CustomerDao;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.restaurant.dao.RestaurantDao;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("OrderService")
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private AddressService addressService;
	@Autowired
	private HarvestAddressDao harvestAddressDao;
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
	 * @param address 
	 * @throws OrderException 
	 */
	@Transactional(rollbackFor = Exception.class)
	public Customer payment(String orderId, Customer customer ) throws OrderException {
		Order order=orderDao.findOrderByOrderId(orderId);
		Customer _customer=order.getOrderCustomer();
		
		
		if(!(_customer.getCustomerId().equals(customer.getCustomerId())))
			throw new OrderException(ErrorMSG.noPower);
		if(_customer.getCustomerBalance().doubleValue()<order.getOrderPrice().doubleValue()){
			throw new OrderException(ErrorMSG.insufficienFunds);
		}
		_customer.setCustomerBalance(_customer.getCustomerBalance().subtract(order.getOrderPrice()));
		customerDao.updateCustomer(_customer);
		
		State state=order.getOrderState();
		
		if(state.getStateId()!=100001)throw new OrderException(ErrorMSG.notKnow);
		
		state.setStateId(100002);
		
		order.setOrderState(state);
		
		orderDao.updateOrder(order);
		return _customer;
	}

	/**
	 * 确认订单
	 * @param orderId
	 * @param restaurant 
	 * @throws OrderException 
	 */
	public void confirmationOrder(String orderId, Restaurant restaurant) throws OrderException {
		Order order=orderDao.findOrderByOrderId(orderId);
		Restaurant _restaurant=order.getOrderRestaurant();
		if(!(_restaurant.getRestaurantId().equals(restaurant.getRestaurantId())))
			throw new OrderException(ErrorMSG.noPower);
		
		State state=order.getOrderState();
		
		if(state.getStateId()!=100002)throw new OrderException(ErrorMSG.notKnow);
		
		state.setStateId(100003);
		order.setOrderState(state);
		orderDao.updateOrder(order);
	}


	/**
	 * 确认收货
	 * @param orderId
	 * @param customer
	 * @throws OrderException 
	 */
	@Transactional(rollbackFor = Exception.class)
	public void confirmationReceipt(String orderId, Customer customer) throws OrderException {
		Order order=orderDao.findOrderByOrderId(orderId);
		
		Customer _customer=order.getOrderCustomer();
		if(!(_customer.getCustomerId().equals(customer.getCustomerId())))
			throw new OrderException(ErrorMSG.noPower);
		
		Restaurant restaurant=order.getOrderRestaurant();
		
		restaurant.setRestaurantIncome(restaurant.getRestaurantIncome().add(order.getOrderPrice()));
		
		restaurantDao.updateRestaurant(restaurant);
		
		State state=order.getOrderState();
		if(state.getStateId()!=100003)throw new OrderException(ErrorMSG.notKnow);
		state.setStateId(100004);
		order.setOrderState(state);
		orderDao.updateOrder(order);
	}
	
	public List<Order> findOrder(OrderStatisticsQureyModel osqm){
		return orderDao.findOrder(osqm);
	}
	
}
