package org.jxau.lctoh.datastatistics.orderstatistics.daomain;

import org.apache.ibatis.type.Alias;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 订单统计类
 * @author qdt_PC
 */
@Component
@Alias("State")
@Scope("prototype")
public class OrderStatistics {
	
	/**数据时间段第几天，第几周，或第几个月*/
	private String tm;
	/**对应时间段的数据条数*/
	private Integer count;
	/**对应时间段的数据数额（如营业额，消费额）*/
	private Integer amount;
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
