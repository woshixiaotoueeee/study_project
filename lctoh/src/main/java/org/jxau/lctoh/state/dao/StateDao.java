package org.jxau.lctoh.state.dao;


import java.util.Date;

import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.state.mapper.StateMapper;
import org.jxau.lctoh.tool.base.BaseDao;
import org.springframework.stereotype.Repository;

@Repository("StateDao")
public class StateDao extends BaseDao {
	
	
	/**状态代理接口*/
	private StateMapper stateMappeer;
	
	public StateMapper getStateMappeer() {
		return stateMappeer;
	}
	public void setStateMappeer(StateMapper stateMappeer) {
		this.stateMappeer = stateMappeer;
	}

	
	@Override
	public void puttMapper() {
		stateMappeer=getMapper(StateMapper.class);
	}
	
	/**
	 * 根据状态码查询状态信息
	 * @param stateId 状态Id
	 * @return State 状态类
	 */
	public State findStateByStateId(Integer stateId){
		return stateMappeer.findStateByStateId(stateId);
	}
	
	/**
	 * 根据状态Id更新状态信息
	 * @param state 需要更新的对象
	 * @return Integer 更新数据的条数
	 */
	public Integer updateState(State state){
		state.setStateUpdateTime(new Date());
		return stateMappeer.updateState(state);
	}
	
}
