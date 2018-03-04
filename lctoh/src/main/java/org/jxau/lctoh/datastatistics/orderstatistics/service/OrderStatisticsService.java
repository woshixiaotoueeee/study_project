package org.jxau.lctoh.datastatistics.orderstatistics.service;

import org.jxau.lctoh.datastatistics.orderstatistics.dao.OrderStatisticsDao;
import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatistics;
import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatisticsQureyModel;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.statistical.Statistical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qdt_PC
 */
@Service("OrderStatisticsService")
public class OrderStatisticsService {
	@Autowired
	private OrderStatisticsDao orderStatisticsDao;
	
	
	/**
	 * 根据对应信息统计订单信息
	 * @param orderStatisticsQureyModel
	 * @return
	 */
	public OrderStatistics orderStatistics(OrderStatisticsQureyModel orderStatisticsQureyModel){
		long timeDifference;
		try{
			timeDifference=Tools.getTimeDifferenceFromDate(orderStatisticsQureyModel.getStm(), orderStatisticsQureyModel.getEtm());
		}catch(Exception e){
			timeDifference=1000*60*60*24*366;
		}
		if(timeDifference<=Statistical.dayNode){
			orderStatisticsQureyModel.setStatisticsType(1);
		}else if(timeDifference>Statistical.dayNode&&timeDifference<Statistical.weekNode){
			orderStatisticsQureyModel.setStatisticsType(2);
		}else if(timeDifference>Statistical.weekNode&&timeDifference<Statistical.mouthNode){
			orderStatisticsQureyModel.setStatisticsType(3);
		}else if(timeDifference>Statistical.mouthNode){
			orderStatisticsQureyModel.setStatisticsType(4);
		}
		return orderStatisticsDao.orderStatistics(orderStatisticsQureyModel);
	}
	
	
}
