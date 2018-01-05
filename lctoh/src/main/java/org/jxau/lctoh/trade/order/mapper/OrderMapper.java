package org.jxau.lctoh.trade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.trade.order.domain.Order;

/**
 * @author qdt_PC
 */
public interface OrderMapper {
	
	/**
	 * 根据订单ID查询订单
	 * @param orderId
	 * @return Order
	 */
	public Order findOrderByOrderId(@Param("orderId")String orderId);
	
	/**
	 * 根据客户识别码查询订单
	 * @param custmerId
	 * @return
	 */
	public List<Order> findOrderByCustmerId(@Param("custmerId")String custmerId);
	
	/**
	 * 根据店家识别码查询订单
	 * @param restaurantId
	 * @return
	 */
	public List<Order> findOrderByRestaurantId(@Param("restaurantId")String restaurantId);
	
	
	/**
	 * 更新订单
	 * @param order
	 * @return Integer
	 */
	public Integer updateOrder(Order order);
	
	/**
	 * 添加订单
	 * @param order
	 * @return Integer
	 */
	public Integer addOrder(Order order);
}
