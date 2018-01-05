package org.jxau.lctoh.trade.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.trade.order.domain.OrderItem;

/**
 * @author qdt_PC
 *
 */
public interface OrderItemMapper {
	
	
	/**
	 * 根据订单识别码查询订单条目
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> findOrderItemByOrderId(@Param("orderId")String orderId);
	
	/**
	 * 根据订单条目识别码查询订单条目
	 * @param orderId
	 * @return
	 */
	public OrderItem findOrderItemByOrderItemId(@Param("orderItemId")String orderItemId);
	
	
	
	/**
	 * 更新订单条目
	 * @param orderItem
	 * @return Integer
	 */
	public Integer updateOrderItem(OrderItem orderItem);
	
	/**
	 * 添加订单条目
	 * @param orderItem
	 * @return Integer
	 */
	public Integer addOrderItem(OrderItem orderItem);
	
	
}
