package org.jxau.lctoh.datastatistics.orderstatistics.daomain;




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
	private String stm;
	/**统计结束时间*/
	private String etm;
	/**统计对象客户*/
	private String customerId;
	/**统计对象店家*/
	private String restaurantId;
	
	/**统计对象骑手*/
	private String riderId;
	
	public String getRiderId() {
		return riderId;
	}
	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}
	/**统计类型：1，日统计，2，周统计，3，月统计，4季度统计（全部）*/
	private Integer statisticsType;
	
	public Integer getStatisticsType() {
		return statisticsType;
	}
	public void setStatisticsType(Integer statisticsType) {
		this.statisticsType = statisticsType;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getStm() {
		return stm;
	}
	public void setStm(String stm) {
		this.stm = stm;
	}
	public String getEtm() {
		return etm;
	}
	public void setEtm(String etm) {
		this.etm = etm;
	}
	
}
