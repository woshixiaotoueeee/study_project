package org.jxau.lctoh.user.rider.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatisticsQureyModel;
import org.jxau.lctoh.user.rider.domain.Dispatching;

public interface DispatchingMapper {
	
	/**
	 * 添加配送信息
	 * @param dispatching
	 * @return
	 */
	public Integer addDispatching(Dispatching dispatching);
	
	/**
	 * 更新配送信息
	 * @param dispatching
	 * @return
	 */
	public Integer updateDispatching(Dispatching dispatching);

	
	/**
	 * 根据ID查询配送信息
	 * @param dispatchingId
	 * @return
	 */
	public Dispatching getDispatchingById(@Param("dispatchingId")String dispatchingId);
	
	/**
	 * 根据状态查询配送信息
	 * @param dispatchingId
	 * @return
	 */
	public List<Dispatching> getDispatchingByState(@Param("dispatchingStateId")Integer dispatchingStateId);
	
	/**
	 * 根据骑手查询配送信息
	 * @param dispatchingRiderId
	 * @return
	 */
	public List<Dispatching> getDispatchingByRider(@Param("dispatchingRiderId")String dispatchingRiderId);
	
	/**
	 * 根据骑手和状态查询配送信息
	 * @param dispatchingStateId
	 * @param dispatchingRiderId
	 * @return
	 */
	public List<Dispatching> getDispatchingByRiderAndState(@Param("dispatchingStateId")Integer dispatchingStateId,@Param("dispatchingRiderId")String dispatchingRiderId);

	public List<Dispatching> getRiderDispatching(@Param("dispatchingRiderId")String dispatchingRiderId);

	public List<Dispatching> findDispatching(OrderStatisticsQureyModel osqm);
}
