package org.jxau.lctoh.state.mapper;

import org.apache.ibatis.annotations.Param;

import org.jxau.lctoh.state.domain.State;

/**
 * 状态类代理接口
 * @author qdt_PC
 */
public interface StateMapper {
	
	
	/**
	 * 根据状态码查询状态信息
	 * @param stateId 状态Id
	 * @return State 状态类
	 */
	public State findStateByStateId(@Param("stateId")Integer stateId);
	
	/**
	 * 根据状态Id更新状态信息
	 * @param state 需要更新的对象
	 * @return Integer 更新数据的条数
	 */
	public Integer updateState(State state);
	
	
	
}
