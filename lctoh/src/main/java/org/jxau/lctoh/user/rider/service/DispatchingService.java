package org.jxau.lctoh.user.rider.service;


import java.util.List;

import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.trade.order.exception.OrderException;
import org.jxau.lctoh.user.rider.dao.DispatchingDao;
import org.jxau.lctoh.user.rider.domain.Dispatching;
import org.jxau.lctoh.user.rider.domain.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qdt_PC
 */
@Service("DispatchingService")
public class DispatchingService {
	@Autowired
	private DispatchingDao dispatchingDao;
	
	
	
	
	
	
	/**
	 * 接单
	 * @param dispatching
	 * @param rider
	 * @return
	 * @throws OrderException 
	 */
	public Integer receiveOrders(String dispatchingId,Rider rider) throws OrderException{
		Dispatching dispatching=dispatchingDao.getDispatchingById(dispatchingId);
		dispatching.setDispatchingRider(rider);
		State state=dispatching.getDispatchingState();
		
		if(state.getStateId()!=110001)throw new OrderException(ErrorMSG.notKnow);
		state.setStateId(110002);
		
		dispatching.setDispatchingState(state);
		return dispatchingDao.updateDispatching(dispatching);
	}
	
	/**
	 * 取餐
	 * @param dispatching
	 * @return
	 * @throws OrderException 
	 */
	public Integer takeMeal(String dispatchingId) throws OrderException{
		Dispatching dispatching=dispatchingDao.getDispatchingById(dispatchingId);
		
		State state=dispatching.getDispatchingState();
		
		if(state.getStateId()!=110002||dispatching.getDispatchingOrder().getOrderState().getStateId()!=100003)throw new OrderException(ErrorMSG.notKnow);
		state.setStateId(110003);
		
		dispatching.setDispatchingState(state);
		return dispatchingDao.updateDispatching(dispatching);
	}
	
	/**
	 * 确认送达
	 * @param dispatching
	 * @return
	 * @throws OrderException 
	 */
	public Integer delivery(String dispatchingId) throws OrderException{
		Dispatching dispatching=dispatchingDao.getDispatchingById(dispatchingId);
		
		State state=dispatching.getDispatchingState();
		
		if(state.getStateId()!=110003)throw new OrderException(ErrorMSG.notKnow);
		state.setStateId(110004);
		
		dispatching.setDispatchingState(state);
		return dispatchingDao.updateDispatching(dispatching);
	}
	

	
	/**
	 * 根据ID查询配送信息
	 * @param dispatchingId
	 * @return
	 */
	public Dispatching getDispatchingById(String dispatchingId){
		return dispatchingDao.getDispatchingById(dispatchingId);
	}
	
	/**
	 * 根据状态查询配送信息
	 * @param dispatchingId
	 * @return
	 */
	public List<Dispatching> getDispatchingByState(Integer dispatchingStateId){
		return dispatchingDao.getDispatchingByState(dispatchingStateId);
	}
	
	/**
	 * 根据骑手查询配送信息
	 * @param dispatchingRiderId
	 * @return
	 */
	public List<Dispatching> getDispatchingByRider(String dispatchingRiderId){
		return dispatchingDao.getDispatchingByRider(dispatchingRiderId);
	}
	
	
	/**
	 * 根据骑手和状态查询配送信息
	 * @param dispatchingStateId
	 * @param dispatchingRiderId
	 * @return
	 */
	public List<Dispatching> getDispatchingByRiderAndState(Integer dispatchingStateId,String dispatchingRiderId){
		return dispatchingDao.getDispatchingByRiderAndState(dispatchingStateId, dispatchingRiderId);
	}
}
