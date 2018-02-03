package org.jxau.lctoh.state.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 状态类
 * @author qdt_PC
 */
@Component
@Alias("State")
@Scope("prototype")
public class State {
	/**状态码*/
	private Integer stateId;
	/**状态信息*/
	private String stateInfo;
	/**最近更新信息*/
	private Date stateUpdateTime;
	public State(){}
	public State(Integer stateId){this.stateId=stateId;}
	
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public Date getStateUpdateTime() {
		return stateUpdateTime;
	}
	public void setStateUpdateTime(Date stateUpdateTime) {
		this.stateUpdateTime = stateUpdateTime;
	}
	@Override
	public String toString() {
		return "State [stateId=" + stateId + ", stateInfo=" + stateInfo
				+ ", stateUpdateTime=" + stateUpdateTime + "]";
	}
	
	
}
