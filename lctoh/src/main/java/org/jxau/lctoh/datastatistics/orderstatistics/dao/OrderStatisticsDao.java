package org.jxau.lctoh.datastatistics.orderstatistics.dao;

import java.util.List;

import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatistics;
import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatisticsQureyModel;
import org.jxau.lctoh.datastatistics.orderstatistics.mapper.OrderStatisticsMapper;
import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author qdt_PC
 */
@Repository("OrderStatisticsDao")
public class OrderStatisticsDao extends BaseDao {
	
	private OrderStatisticsMapper orderStatisticsMapper;
	
	public OrderStatisticsMapper getOrderStatisticsMapper() {
		return orderStatisticsMapper;
	}
	public void setOrderStatisticsMapper(OrderStatisticsMapper orderStatisticsMapper) {
		this.orderStatisticsMapper = orderStatisticsMapper;
	}
	@Override
	public void puttMapper() {
		this.orderStatisticsMapper = this.getMapper(OrderStatisticsMapper.class);
	}
	/**
	 * 根据对应信息统计订单信息
	 * @param orderStatisticsQureyModel
	 * @return
	 */
	public List<OrderStatistics> orderStatistics(OrderStatisticsQureyModel orderStatisticsQureyModel){
		return orderStatisticsMapper.orderStatistics(orderStatisticsQureyModel);
	}

	
	
	
	
	
}
