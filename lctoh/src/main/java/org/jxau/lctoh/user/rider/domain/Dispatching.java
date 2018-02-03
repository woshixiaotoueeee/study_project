package org.jxau.lctoh.user.rider.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.trade.order.domain.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 配送表
 * @author qdt_PC
 */
@Component
@Alias("Dispatching")
@Scope("prototype")
public class Dispatching {
	private Order dispatchingOrder;
	private Rider dispatchingRider;
	private Date dispatchingUpdateTime;
	private State dispatchingState;
	public State getDispatchingState() {
		return dispatchingState;
	}
	public void setDispatchingState(State dispatchingState) {
		this.dispatchingState = dispatchingState;
	}
	public Order getDispatchingOrder() {
		return dispatchingOrder;
	}
	public void setDispatchingOrder(Order dispatchingOrder) {
		this.dispatchingOrder = dispatchingOrder;
	}
	public Rider getDispatchingRider() {
		return dispatchingRider;
	}
	public void setDispatchingRider(Rider dispatchingRider) {
		this.dispatchingRider = dispatchingRider;
	}
	public Date getDispatchingUpdateTime() {
		return dispatchingUpdateTime;
	}
	public void setDispatchingUpdateTime(Date dispatchingUpdateTime) {
		this.dispatchingUpdateTime = dispatchingUpdateTime;
	}
	
	
	
	
	
}
