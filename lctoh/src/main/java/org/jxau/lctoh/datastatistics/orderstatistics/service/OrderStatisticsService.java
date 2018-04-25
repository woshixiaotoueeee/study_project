package org.jxau.lctoh.datastatistics.orderstatistics.service;

import java.util.Date;
import java.util.List;

import org.jxau.lctoh.datastatistics.orderstatistics.dao.OrderStatisticsDao;
import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatistics;
import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatisticsQureyModel;
import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderTypeCount;
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
	public List<OrderStatistics> orderStatistics(OrderStatisticsQureyModel orderStatisticsQureyModel){
		return orderStatisticsDao.orderStatistics(getorderStatisticsQureyModel(orderStatisticsQureyModel));
	}


	public List<OrderTypeCount> orderStatisticsByState(
			OrderStatisticsQureyModel orderStatisticsQureyModel) {
		return orderStatisticsDao.orderStatisticsByState(orderStatisticsQureyModel);
	}
	
	/**
	 * 根据对应信息统计配送信息
	 * @param orderStatisticsQureyModel
	 * @return
	 */
	public List<OrderStatistics> dispatchingStatistics(OrderStatisticsQureyModel orderStatisticsQureyModel){
		return orderStatisticsDao.dispatchingStatistics(getorderStatisticsQureyModel(orderStatisticsQureyModel));
	}
	
	
	public List<OrderTypeCount> dispatchingStatisticsByState(
			OrderStatisticsQureyModel orderStatisticsQureyModel) {
		return orderStatisticsDao.dispatchingStatisticsByState(orderStatisticsQureyModel);
	}
	
	
	/**补全统计信息*/
	private OrderStatisticsQureyModel getorderStatisticsQureyModel(OrderStatisticsQureyModel orderStatisticsQureyModel){
		long timeDifference;
		//补全时间
		try{
			timeDifference=Tools.getTimeDifferenceFromDate(Tools.StringToDate(orderStatisticsQureyModel.getStm()),
					Tools.StringToDate(orderStatisticsQureyModel.getEtm()));
		}catch(Exception e){
			e.printStackTrace();
			Date now=new Date();
			orderStatisticsQureyModel.setEtm(Tools.DateToString(now));
			orderStatisticsQureyModel.setStm(Tools.DateToString(Tools.CalculationDate(now,-1,Tools.year)));
			timeDifference=1000*60*60*24*366;
		}
		//设置统计方式
		if(timeDifference<=Statistical.dayNode){
			orderStatisticsQureyModel.setStatisticsType(1);
		}else if(timeDifference>Statistical.dayNode&&timeDifference<Statistical.weekNode){
			orderStatisticsQureyModel.setStatisticsType(2);
		}else if(timeDifference>Statistical.weekNode&&timeDifference<Statistical.mouthNode){
			orderStatisticsQureyModel.setStatisticsType(3);
		}else if(timeDifference>Statistical.mouthNode){
			orderStatisticsQureyModel.setStatisticsType(4);
		}
		return orderStatisticsQureyModel;
	}
}
