package org.jxau.lctoh.state.service;

import org.jxau.lctoh.state.dao.StateDao;
import org.jxau.lctoh.state.domain.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StateService")
public class StateService {
	@Autowired
	private StateDao statedao;
	
	
	/**
	 * 根据状态码查询状态信息
	 * @param stateId 状态Id
	 * @return State 状态类
	 */
	public State findStateByStateId(Integer stateId){
		if(stateId==null)return null;
		return statedao.findStateByStateId(stateId);
	}
	
	/**
	 * 根据状态Id更新状态信息
	 * @param state 需要更新的对象
	 * @return Integer 更新数据的条数
	 */
	public Integer updateState(State state){
		if(state==null)return null;
		return statedao.updateState(state);
	}
	
	
}
