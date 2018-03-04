package org.jxau.lctoh.datastatistics.orderstatistics.daomain;


import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author qdt_PC
 */
@Component
@Alias("OrderStatisticsQureyModel")
@Scope("prototype")
public class OrderStatisticsQureyModel{
	/**统计开始时间*/
	private Date stm;
	/**统计结束时间*/
	private Date etm;
	/**统计对象客户*/
	private String customerId;
	/**统计对象店家*/
	private String restaurantId;
	/**统计类型：1，日统计，2，周统计，3，月统计，4季度统计（全部）*/
	private Integer statisticsType;
	
	public Integer getStatisticsType() {
		return statisticsType;
	}
	public void setStatisticsType(Integer statisticsType) {
		this.statisticsType = statisticsType;
	}
	public Date getStm() {
		return stm;
	}
	public Date getEtm() {
		return etm;
	}
	public String getCustomerId() {
		return customerId;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setStm(Date stm) {
		this.stm = stm;
	}
	public void setEtm(Date etm) {
		this.etm = etm;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
}
