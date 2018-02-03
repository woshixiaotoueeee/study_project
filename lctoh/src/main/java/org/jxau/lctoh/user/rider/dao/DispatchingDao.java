package org.jxau.lctoh.user.rider.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.jxau.lctoh.user.rider.domain.Dispatching;
import org.jxau.lctoh.user.rider.mapper.DispatchingMapper;
import org.springframework.stereotype.Repository;
/**
 * @author qdt_PC
 */
@Repository("DispatchingDao")
public class DispatchingDao extends BaseDao {
	
	private DispatchingMapper dispatchingMapper;
	public DispatchingMapper getDispatchingMapper() {
		return dispatchingMapper;
	}
	public void setDispatchingMapper(DispatchingMapper dispatchingMapper) {
		this.dispatchingMapper = dispatchingMapper;
	}
	@Override
	public void puttMapper() {
		dispatchingMapper=this.getMapper(DispatchingMapper.class);
	}
	
	
	/**
	 * 添加配送信息
	 * @param dispatching
	 * @return
	 */
	public Integer addDispatching(Dispatching dispatching){
		dispatching.setDispatchingUpdateTime(new Date());
		return dispatchingMapper.addDispatching(dispatching);
	}
	
	/**
	 * 更新配送信息
	 * @param dispatching
	 * @return
	 */
	public Integer updateDispatching(Dispatching dispatching){
		dispatching.setDispatchingUpdateTime(new Date());
		return dispatchingMapper.updateDispatching(dispatching);
	}

	
	/**
	 * 根据ID查询配送信息
	 * @param dispatchingId
	 * @return
	 */
	public Dispatching getDispatchingById(String dispatchingId){
		return dispatchingMapper.getDispatchingById(dispatchingId);
	}
	
	/**
	 * 根据状态查询配送信息
	 * @param dispatchingId
	 * @return
	 */
	public List<Dispatching> getDispatchingByState(Integer dispatchingStateId){
		return dispatchingMapper.getDispatchingByState(dispatchingStateId);
	}
	
	/**
	 * 根据骑手查询配送信息
	 * @param dispatchingRiderId
	 * @return
	 */
	public List<Dispatching> getDispatchingByRider(String dispatchingRiderId){
		return dispatchingMapper.getDispatchingByRider(dispatchingRiderId);
	}
	
	/**
	 * 根据骑手和状态查询配送信息
	 * @param dispatchingStateId
	 * @param dispatchingRiderId
	 * @return
	 */
	public List<Dispatching> getDispatchingByRiderAndState(Integer dispatchingStateId,String dispatchingRiderId){
		return dispatchingMapper.getDispatchingByRiderAndState(dispatchingStateId, dispatchingRiderId);
	}

}
