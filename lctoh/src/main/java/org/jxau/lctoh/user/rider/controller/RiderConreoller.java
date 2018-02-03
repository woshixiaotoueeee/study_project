package org.jxau.lctoh.user.rider.controller;

import javax.servlet.http.HttpSession;

import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.user.rider.domain.Rider;
import org.jxau.lctoh.user.rider.exception.RiderException;
import org.jxau.lctoh.user.rider.service.DispatchingService;
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
	
	
	/**
	 * 根据骑手和状态获取配送信息
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
			responseData.failInfo(e.getMessage());
		}catch (Exception e){
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
			responseData.failInfo(e.getMessage());
		}catch (Exception e){
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
			responseData.failInfo(e.getMessage());
		}catch (Exception e){
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
