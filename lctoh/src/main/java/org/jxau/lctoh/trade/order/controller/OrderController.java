package org.jxau.lctoh.trade.order.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.ConversationMSG;
import org.jxau.lctoh.tool.config.EncodingConfig;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.trade.cart.domain.Cart;
import org.jxau.lctoh.trade.order.domain.Order;
import org.jxau.lctoh.trade.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/OrderController")
public class OrderController extends BaseController{
	@Autowired
	private OrderService orderService;
	
	
	/**
	 * 根据订单识别码获取订单信息
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findOrderByOrderId",produces=EncodingConfig.produces)
	public String findOrderByOrderId(String orderId){
		
		if(orderId==null){
			responseData.failInfo(ErrorMSG.parameterIsNullError);
		}else{
			try{
				responseData.successInfo(orderService.findOrderByOrderId(orderId));
			}catch(Exception e){
				responseData.failInfo(e.getMessage());
			}
		}
		return Tools.gson.toJson(responseData);
	}
	/**
	 * 根据客户识别码查询订单
	 * @param custmerId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findOrderByCustmerId",produces=EncodingConfig.produces)
	public String findOrderByCustmerId(String custmerId){
		
		if(custmerId==null){
			responseData.failInfo(ErrorMSG.parameterIsNullError);
		}else{
			try{
				responseData.successInfo(orderService.findOrderByCustmerId(custmerId));
			}catch(Exception e){
				responseData.failInfo(e.getMessage());
			}
		}
		return Tools.gson.toJson(responseData);
	}

	/**
	 * 根据客户识别码查询订单
	 * @param restaurantId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findOrderByRestaurantId",produces=EncodingConfig.produces)
	public String findOrderByRestaurantId(String restaurantId){
		
		if(restaurantId==null){
			responseData.failInfo(ErrorMSG.parameterIsNullError);
		}else{
			try{
				responseData.successInfo(orderService.findOrderByRestaurantId(restaurantId));
			}catch(Exception e){
				responseData.failInfo(e.getMessage());
			}
		}
		return Tools.gson.toJson(responseData);
	}
	
	
}
