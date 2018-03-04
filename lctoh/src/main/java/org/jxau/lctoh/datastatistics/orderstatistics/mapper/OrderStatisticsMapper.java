package org.jxau.lctoh.datastatistics.orderstatistics.mapper;

import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatistics;
import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatisticsQureyModel;

/**
 * @author qdt_PC
 */
public interface OrderStatisticsMapper {
	
	/**
	 * 根据对应信息统计订单信息
	 * @param orderStatisticsQureyModel
	 * @return
	 */
	public OrderStatistics orderStatistics(OrderStatisticsQureyModel orderStatisticsQureyModel );
	
	
	
}
