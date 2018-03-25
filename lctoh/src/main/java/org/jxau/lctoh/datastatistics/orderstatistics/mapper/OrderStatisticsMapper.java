package org.jxau.lctoh.datastatistics.orderstatistics.mapper;

import java.util.List;

import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatistics;
import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatisticsQureyModel;
import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderTypeCount;

/**
 * @author qdt_PC
 */
public interface OrderStatisticsMapper {
	
	/**
	 * 根据对应信息统计订单信息
	 * @param orderStatisticsQureyModel
	 * @return
	 */
	public List<OrderStatistics> orderStatistics(OrderStatisticsQureyModel orderStatisticsQureyModel );

	public List<OrderTypeCount> orderStatisticsByState(
			OrderStatisticsQureyModel orderStatisticsQureyModel);
	
	/**
	 * 根据对应信息统计配送信息
	 * @param orderStatisticsQureyModel
	 * @return
	 */
	public List<OrderStatistics> dispatchingStatistics(OrderStatisticsQureyModel orderStatisticsQureyModel );

	
	public List<OrderTypeCount> dispatchingStatisticsByState(
			OrderStatisticsQureyModel orderStatisticsQureyModel);
}
