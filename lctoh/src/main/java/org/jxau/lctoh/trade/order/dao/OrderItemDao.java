package org.jxau.lctoh.trade.order.dao;

import java.util.Date;
import java.util.List;

import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.jxau.lctoh.trade.order.domain.OrderItem;
import org.jxau.lctoh.trade.order.mapper.OrderItemMapper;
import org.springframework.stereotype.Repository;
/**
 * @author qdt_PC
 */
@Repository("OrderItemDao")
public class OrderItemDao  extends BaseDao{
	
	private OrderItemMapper orderItemMapper;
	
	@Override
	public void puttMapper() {
		orderItemMapper=this.getMapper(OrderItemMapper.class);
	}
	public OrderItemMapper getOrderItemMapper() {
		return orderItemMapper;
	}
	public void setOrderItemMapper(OrderItemMapper orderItemMapper) {
		this.orderItemMapper = orderItemMapper;
	}
	/**
	 * 添加单个订单条目
	 * @param orderItem
	 * @return Integer
	 */
	public Integer addOrderItem(OrderItem orderItem) {
		orderItem.setOrderItemUpdateTime(new Date());
		return orderItemMapper.addOrderItem(orderItem);
	}
	
	/**
	 * 添加多个订单条目
	 * @param orderItemList
	 * @return Integer
	 */
	public Integer addOrderItemList(List<OrderItem> orderItemList) {
		Date updateTime=new Date();
		for(int i=0;i<orderItemList.size();i++){
			orderItemList.get(i).setOrderItemUpdateTime(updateTime);
		}
		return orderItemMapper.addOrderItemList(orderItemList);
	}
	

}
