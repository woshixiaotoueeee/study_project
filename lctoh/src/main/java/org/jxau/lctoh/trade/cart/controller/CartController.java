package org.jxau.lctoh.trade.cart.controller;

import javax.servlet.http.HttpSession;

import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.ConversationMSG;
import org.jxau.lctoh.tool.config.EncodingConfig;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.tool.config.SuccessMSG;
import org.jxau.lctoh.trade.cart.domain.Cart;
import org.jxau.lctoh.trade.cart.exception.CartException;
import org.jxau.lctoh.trade.cart.service.CartService;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/CartController")
public class CartController extends BaseController{
	@Autowired
	private CartService cartService;
	
	/**
	 * 获取购物车信息
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCart",produces=EncodingConfig.produces)
	public String getCart(HttpSession session){
		Cart cart=(Cart) session.getAttribute(ConversationMSG.cartSession);
		if(cart==null){
			responseData.failInfo(ErrorMSG.notKnowError);
		}else{
			responseData.successInfo(cart);
		}
		return Tools.gson.toJson(responseData);
	}
	/**
	 * 往购物车中添加商品
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addDishCart",produces=EncodingConfig.produces)
	public String addDishCart(String dishId,Integer dishCount,HttpSession session){
		Cart cart=(Cart)session.getAttribute(ConversationMSG.cartSession);
		if(cart==null||dishCount==null||dishId==null){
			responseData.failInfo(ErrorMSG.notKnowError);
		}else{
			try {
				cart=cartService.addDishToCart(cart,dishId,dishCount);
				session.setAttribute(ConversationMSG.cartSession, cart);
				responseData.successInfo(SuccessMSG.successMSG);
			} catch (CartException e) {
				responseData.successInfo(e.getMessage());
			}
		}
		return Tools.gson.toJson(responseData);
	}
	/**
	 * 更新购物车中商品数量
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateDishCart",produces=EncodingConfig.produces)
	public String updateDishCart(String dishId,Integer dishCount,HttpSession session){
		Cart cart=(Cart)session.getAttribute(ConversationMSG.cartSession);
		if(cart==null||dishCount==null||dishId==null){
			responseData.failInfo(ErrorMSG.notKnowError);
		}else{
			try {
				cart=cartService.updateDishToCart(cart,dishId,dishCount);
				session.setAttribute(ConversationMSG.cartSession, cart);
				responseData.successInfo(SuccessMSG.successMSG);
			} catch (CartException e) {
				responseData.successInfo(e.getMessage());
			}
		}
		return Tools.gson.toJson(responseData);
	}
	
	/**
	 * 更新购物车中商品数量
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteDishCart",produces=EncodingConfig.produces)
	public String deleteDishCart(String dishId,HttpSession session){
		Cart cart=(Cart)session.getAttribute(ConversationMSG.cartSession);
		if(cart==null||dishId==null){
			responseData.failInfo(ErrorMSG.notKnowError);
		}else{
			try {
				cart.deleteDish(dishId);
				session.setAttribute(ConversationMSG.cartSession, cart);
				responseData.successInfo(SuccessMSG.successMSG);
			} catch (Exception e) {
				responseData.successInfo(ErrorMSG.deleteCartDishError);
			}
		}
		return Tools.gson.toJson(responseData);
	}
	
	
	/**
	 * 根据购物车生成订单
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/cartToOrder",produces=EncodingConfig.produces)
	public String cartToOrder(String addressId,HttpSession session){
		Cart cart=(Cart)session.getAttribute(ConversationMSG.cartSession);
		if(cart==null||addressId==null){
			responseData.failInfo(ErrorMSG.notKnowError);
		}else{
			try {
				Customer orderCustomer=(Customer)session.getAttribute(ConversationMSG.customerSession);
				cartService.putCartToOrder(cart, orderCustomer, addressId);
			} catch (CartException e) {
				responseData.failInfo(e.getMessage());
			} catch (Exception e) {
				responseData.failInfo(ErrorMSG.notKnowError);
			}
		}
		return Tools.gson.toJson(responseData);
	}
}
