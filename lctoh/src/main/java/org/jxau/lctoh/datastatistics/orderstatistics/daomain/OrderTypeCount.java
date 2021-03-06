package org.jxau.lctoh.datastatistics.orderstatistics.daomain;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 订单统计类
 * @author qdt_PC
 */
@Component
@Alias("OrderTypeCount")
@Scope("prototype")
public class OrderTypeCount {
	
	/**
	 * 订单信息：
	 * 100001 :已下单未付款
	 * 100002 :已付款未发货
	 * 100003 :配送中
	 * 100004 :已送达
	 * 100005 :异常订单
	 * 
	 * 配送信息：
	 * 110001 :待接单
	 * 110002 :待取餐
	 * 110003 :待配送
	 * 110004 :已完成
	 * 110005 :异常订单
	 * 
	 * */
	private Integer type;
	/**对应时间段的数据条数*/
	private Integer count;
	public Integer getType() {
		return type;
	}
	public Integer getCount() {
		return count;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
}
