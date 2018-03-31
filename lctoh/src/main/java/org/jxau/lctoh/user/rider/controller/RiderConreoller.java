package org.jxau.lctoh.user.rider.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.jxau.lctoh.position.region.domain.City;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.rider.domain.Rider;
import org.jxau.lctoh.user.rider.exception.RiderException;
import org.jxau.lctoh.user.rider.service.DispatchingService;
import org.jxau.lctoh.user.rider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/RiderConreoller")
public class RiderConreoller extends BaseController {
	
	@Autowired
	private DispatchingService dispatchingService;
	
	@Autowired
	private RiderService riderService;
	/**
	 * 根据骑手和订单配送状态获取配送信息
	 * @param stateId 状态ID Integer 整形数字
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Dispatching&gt; 类型对象具体属性参考 Dispatching实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.rider.domain.Dispatching
	 */
	@ResponseBody
	@RequestMapping(value="/getDispatchingByRiderAndState",produces=EncodingConfig.produces)
	public String getDispatchingByRiderAndState(Rider rider,Integer stateId,HttpSession session){
		try {
			rider=getRiderInSession(session);
			if(stateId==null){
				responseData.failInfo(ErrorMSG.notKnow);
			}else{
				responseData.successInfo(dispatchingService.getDispatchingByRiderAndState(stateId, rider.getRiderId()));
			}
		} catch (RiderException e) {
			e.printStackTrace();
			responseData.failInfo(e.getMessage());
		}catch (Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.notKnow);
		}
		return toGsonString();
	}
	
	
	/**
	 * 根据状态获取配送信息
	 * @param stateId 状态ID Integer 整形数字
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Dispatching&gt; 类型对象具体属性参考 Dispatching实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.rider.domain.Dispatching
	 */
	@ResponseBody
	@RequestMapping(value="/getDispatchingByState",produces=EncodingConfig.produces)
	public String getDispatchingByState(Integer stateId){
		try {
			if(stateId==null){
				responseData.failInfo(ErrorMSG.notKnow);
			}else{
				responseData.successInfo(dispatchingService.getDispatchingByState(stateId));
			}
		}catch (Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.notKnow);
		}
		return toGsonString();
	}
	
	
	/**
	 * 根据骑手获取配送信息
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Dispatching&gt; 类型对象具体属性参考 Dispatching实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.rider.domain.Dispatching
	 */
	@ResponseBody
	@RequestMapping(value="/getDispatchingByRider",produces=EncodingConfig.produces)
	public String getDispatchingByRider(Rider rider,HttpSession session){
		try {
			rider=getRiderInSession(session);
			responseData.successInfo(dispatchingService.getDispatchingByRider(rider.getRiderId()));
		} catch (RiderException e) {
			e.printStackTrace();
			responseData.failInfo(e.getMessage());
		}catch (Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.notKnow);
		}
		return toGsonString();
	}
	/**
	 * 根据配送识别码获取配送信息
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Dispatching&gt; 类型对象具体属性参考 Dispatching实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.rider.domain.Dispatching
	 */
	@ResponseBody
	@RequestMapping(value="/getDispatchingById",produces=EncodingConfig.produces)
	public String getDispatchingById(String  dispatchingId,HttpSession session){
		try {
			if(dispatchingId==null){
				responseData.failInfo(ErrorMSG.notKnow);
			}else{
				responseData.successInfo(dispatchingService.getDispatchingById(dispatchingId));
			}
		}catch (Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.notKnow);
		}
		return toGsonString();
	}
	
	/**
	 * 根据更新订单配送状态配送信息
	 * @param dispatchingId String 字符串 配送信息识别码
	 * @param state	Integer 状态
	 * <pre>
	 * state 说明
	 * 	1 接单
	 * 	2 取餐
	 * 	3 送达
	 * </pre>
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为成功信息 String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.rider.domain.Dispatching
	 */
	@ResponseBody
	@RequestMapping(value="/updateDispatchingState",produces=EncodingConfig.produces)
	public String updateDispatchingState(Rider rider,String  dispatchingId,Integer state,HttpSession session){
		try {
			rider=getRiderInSession(session);
			if(dispatchingId==null||state==null){
				responseData.failInfo(ErrorMSG.notKnow);
			}else{
				switch(state){
					case 1:
						dispatchingService.receiveOrders(dispatchingId, rider);
						break;
					case 2:
						dispatchingService.takeMeal(dispatchingId);
						break;
					case 3:
						dispatchingService.delivery(dispatchingId);
						break;
				}
				responseData.successInfo(SuccessMSG.successMSG);
			}
		} catch (RiderException e) {
			e.printStackTrace();
			responseData.failInfo(e.getMessage());
		}catch (Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.notKnow);
		}
		return toGsonString();
	}
	
	
	
	/**
	 * 更新骑手位置信息
	 * @param riderLongitude BigDecimal 数字 经纬度
	 * @param riderLatitude BigDecimal 数字 经纬度
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为成功信息 String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/riderLocation",produces=EncodingConfig.produces)
	public String riderLocation(Rider rider,HttpSession session){
		try {
			Rider _rider=getRiderInSession(session);
			if(rider.getRiderLatitude()==null||rider.getRiderLongitude()==null){
				responseData.failInfo(ErrorMSG.notKnow);
			}else{
				_rider.setRiderLatitude(rider.getRiderLatitude());
				_rider.setRiderLongitude(rider.getRiderLongitude());
				session.setAttribute(ConversationConfig.riderSession, _rider);
				ServletContext servletContext=session.getServletContext();
				Map map=(Map) servletContext.getAttribute(ConversationConfig.riderContext);
				map.put(_rider.getRiderId(), _rider);
				servletContext.setAttribute(ConversationConfig.riderContext, map);
				responseData.successInfo(SuccessMSG.successMSG);
			}
		} catch (RiderException e) {
			e.printStackTrace();
			responseData.failInfo(e.getMessage());
		}catch (Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.notKnow);
		}
		return toGsonString();
	}
	
	
	/**
	 * 
	 * @param stateType Integer 状态 1：上班  2：下班
	 * @return
	 * @throws RiderException
	 */
	@ResponseBody
	@RequestMapping(value="/updateRiderState",produces=EncodingConfig.produces)
	private String updateRiderState(Rider rider,Integer stateType, HttpSession session){
		try {
			rider=getRiderInSession(session);
			if(stateType==null){
				responseData.failInfo(ErrorMSG.parameterIsNull);
			}else{
				if(stateType==1){
					rider.setRiderState(new State(130001));
					session.setAttribute(ConversationConfig.riderSession, rider);
					ServletContext servletContext=session.getServletContext();
					Map map=(Map) servletContext.getAttribute(ConversationConfig.riderContext);
					map.put(rider.getRiderId(), rider);
					servletContext.setAttribute(ConversationConfig.riderContext, map);
					
				}else if(stateType==2){
					rider.setRiderState(new State(130002));
					session.setAttribute(ConversationConfig.riderSession, rider);
					ServletContext servletContext=session.getServletContext();
					Map map=(Map) servletContext.getAttribute(ConversationConfig.riderContext);
					map.remove(rider.getRiderId());
					servletContext.setAttribute(ConversationConfig.riderContext, map);
				}
				responseData.successInfo(SuccessMSG.successMSG);
			}
		} catch (RiderException e) {
			e.printStackTrace();
			responseData.failInfo(e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.notKnow);
		}
		return toGsonString();
	}
	
	
	
	
	
	/**
	 * 更新信息
	 * @param  
	 * <pre>
	 * 骑手姓名 String riderName 
	 * 所属城市String cityId 
	 * 	userEmail String 字符串  邮箱
	 * 	userPhone String 字符串 联系方式
	 * 	userSex String 字符串 性别
	 * </pre>
	 * @return
	 * @throws RiderException
	 */
	@ResponseBody
	@RequestMapping(value="/updateRider",produces=EncodingConfig.produces)
	private String updateRider(Rider rider,City city,User user, HttpSession session){
		try {
			Rider _rider=getRiderInSession(session);
			if(rider.getRiderName()==null||city.getCityId()==null||
					user.getUserEmail()==null||user.getUserPhone()==null||
					user.getUserSex()==null
					){
				responseData.failInfo(ErrorMSG.parameterIsNull);
			}else{
				
				_rider.setRiderName(rider.getRiderName());
				_rider.setRiderCity(city);
				User _user=_rider.getRiderUser();
				_user.setUserEmail(user.getUserEmail());
				_user.setUserPhone(user.getUserPhone());
				_user.setUserSex(user.getUserSex());
				_rider.setRiderUser(_user);
				riderService.updateRider(_rider);
				session.setAttribute(ConversationConfig.riderSession, rider);
				ServletContext servletContext=session.getServletContext();
				Map map=(Map) servletContext.getAttribute(ConversationConfig.riderContext);
				map.remove(rider.getRiderId());
				servletContext.setAttribute(ConversationConfig.riderContext, map);
				responseData.successInfo(SuccessMSG.updateSuccessMSG);
			}
		} catch (RiderException e) {
			e.printStackTrace();
			responseData.failInfo(e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.notKnow);
		}
		return toGsonString();
	}
	
	/**
	 * 获取Session中的骑手信息
	 * @param session
	 * @return
	 * @throws RiderException
	 */
	private Rider getRiderInSession(HttpSession session) throws RiderException{
		Rider rider=(Rider) session.getAttribute(ConversationConfig.riderSession);
		if(rider==null)throw new RiderException(ErrorMSG.loginTimerOut);
		return rider;
	}
	
	
	
}
