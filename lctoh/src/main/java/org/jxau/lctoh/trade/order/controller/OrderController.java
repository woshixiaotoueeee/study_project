package org.jxau.lctoh.trade.order.controller;



import javax.servlet.http.HttpSession;

import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatisticsQureyModel;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.trade.order.exception.OrderException;
import org.jxau.lctoh.trade.order.service.OrderService;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单操作接口
 * @author qdt_PC
 */
@Controller
@RequestMapping("/OrderController")
public class OrderController extends BaseController{
	@Autowired
	private OrderService orderService;
	
	
	/**
	 * 根据订单识别码获取订单信息
	 * @param orderId String 字符串  订单识别码
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  Order 类型对象具体属性参考 Order实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.trade.order.domain.Order
	 */
	@ResponseBody
	@RequestMapping(value="/findOrderByOrderId",produces=EncodingConfig.produces)
	public String findOrderByOrderId(String orderId){
		if(orderId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try{
				responseData.successInfo(orderService.findOrderByOrderId(orderId));
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	
	/**
	 * 根据客户识别码查询订单
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Order&gt; 类型对象具体属性参考 Order实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.trade.order.domain.Order
	 */
	@ResponseBody
	@RequestMapping(value="/findOrderByCustmerId",produces=EncodingConfig.produces)
	public String findOrderByCustmerId(HttpSession session){
		Customer customer= (Customer)session.getAttribute(ConversationConfig.customerSession);
		if(customer==null){
			responseData.failInfo(ErrorMSG.loginTimerOut);
		}else{
			try{
				responseData.successInfo(orderService.findOrderByCustmerId(customer.getCustomerId()));
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}

	/**
	 * 根据店家识别码查询订单
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Order&gt; 类型对象具体属性参考 Order实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.trade.order.domain.Order
	 */
	@ResponseBody
	@RequestMapping(value="/findOrderByRestaurantId",produces=EncodingConfig.produces)
	public String findOrderByRestaurantId(HttpSession session){
		Restaurant restaurant= (Restaurant) session.getAttribute(ConversationConfig.restaurantSession);
		if(restaurant==null){
			responseData.failInfo(ErrorMSG.loginTimerOut);
		}else{
			try{
				responseData.successInfo(orderService.findOrderByRestaurantId(restaurant.getRestaurantId()));
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	
	
	
	/**
	 * 付款
	 * @param orderId String 字符串  订单识别码
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为成功的信息 String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/payment",produces=EncodingConfig.produces)
	public String payment(String orderId, HttpSession session){
		if(orderId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			Customer customer= (Customer) session.getAttribute(ConversationConfig.customerSession);
			if(customer==null){
				responseData.failInfo(ErrorMSG.loginTimerOut);
			}else{
				try{
					customer=orderService.payment(orderId,customer);
					session.setAttribute(ConversationConfig.customerSession, customer);
					responseData.successInfo(SuccessMSG.successMSG);
				}catch(OrderException e){
					e.printStackTrace();
					responseData.failInfo(e.getMessage());
				}catch(Exception e){
					e.printStackTrace();
					responseData.failInfo(ErrorMSG.operationFail);
				}
			}
		}
		return toGsonString();
	}
	
	/**
	 * 确认订单
	 * @param orderId String 字符串  订单识别码
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为成功的信息 String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/confirmationOrder",produces=EncodingConfig.produces)
	public String confirmationOrder(String orderId, HttpSession session){
		if(orderId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			Restaurant restaurant= (Restaurant) session.getAttribute(ConversationConfig.restaurantSession);
			if(restaurant==null){
				responseData.failInfo(ErrorMSG.loginTimerOut);
			}else{
				try{
					orderService.confirmationOrder(orderId,restaurant);
					responseData.successInfo(SuccessMSG.successMSG);
				}catch(OrderException e){
					e.printStackTrace();
					responseData.failInfo(e.getMessage());
				}catch(Exception e){
					e.printStackTrace();
					responseData.failInfo(ErrorMSG.operationFail);
				}
			}
		}
		return toGsonString();
	}
	
	/**
	 * 确认收货
	 * @param orderId String 字符串  订单识别码
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为成功的信息 String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/confirmationReceipt",produces=EncodingConfig.produces)
	public String confirmationReceipt(String orderId, HttpSession session){
		if(orderId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			Customer customer= (Customer) session.getAttribute(ConversationConfig.customerSession);
			if(customer==null){
				responseData.failInfo(ErrorMSG.loginTimerOut);
			}else{
				try{
					orderService.confirmationReceipt(orderId,customer);
					responseData.successInfo(SuccessMSG.successMSG);
				}catch(OrderException e){
					e.printStackTrace();
					responseData.failInfo(e.getMessage());
				}catch(Exception e){
					e.printStackTrace();
					responseData.failInfo(ErrorMSG.operationFail);
				}
			}
		}
		return toGsonString();
	}
	

	/**
	 * 查询订单（根据时间）
	 * @param orderStatisticsQureyModel
	 * <pre>
	 * orderStatisticsQureyModel 说明
	 * orderStatisticsQureyModel{
	 * 	stm String 统计开始时间	格式：2011-12-31 00:00:00
	 * 	etm Date 统计结束时间 格式：2011-12-31 00:00:00
	 * 	customerId String 统计对象客户
	 * 	restaurantId String 统计对象店家
	 * }
	 * </pre>
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Order&gt; 类型对象具体属性参考 Order实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.trade.order.domain.Order
	 */
	@ResponseBody
	@RequestMapping(value="/findOrder",produces=EncodingConfig.produces)
	public String findOrder(OrderStatisticsQureyModel osqm){
		try{
			responseData.successInfo(orderService.findOrder(osqm));
		}catch(Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.selectFail);
		}
			
		
		return toGsonString();
	}
	
	
	
}
