package org.jxau.lctoh.trade.cart.controller;

import javax.servlet.http.HttpSession;

import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
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
	
	private Cart getCartInSession(HttpSession session) throws CartException{
		Cart cart=(Cart) session.getAttribute(ConversationConfig.cartSession);
		if(cart==null)throw new CartException(ErrorMSG.cartTimerOut);
		return cart;
	}
	
	/**
	 * 获取购物车信息
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCart",produces=EncodingConfig.produces)
	public String getCart(HttpSession session){
		try {
			responseData.successInfo(getCartInSession(session));
		} catch (CartException e) {
			responseData.failInfo(e.getMessage());
		}
		return toGsonString();
	}
	/**
	 * 往购物车中添加商品
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addDishCart",produces=EncodingConfig.produces)
	public String addDishCart(Cart cart,String dishId,Integer dishCount,HttpSession session){
		try {
			cart = getCartInSession(session);
		} catch (CartException e1) {
			return responseData.failInfo(e1.getMessage()).toGsonString();
		}
		if(dishCount==null||dishId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try {
				cart=cartService.addDishToCart(cart,dishId,dishCount);
				session.setAttribute(ConversationConfig.cartSession, cart);
				responseData.successInfo(SuccessMSG.addSuccessMSG);
			} catch (CartException e) {
				responseData.failInfo(e.getMessage());
			} catch (Exception e) {
				responseData.failInfo(ErrorMSG.addFail);
			}
		}
		return toGsonString();
	}
	/**
	 * 更新购物车中商品数量
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateDishCart",produces=EncodingConfig.produces)
	public String updateDishCart(Cart cart,String dishId,Integer dishCount,HttpSession session){
		try {
			cart = getCartInSession(session);
		} catch (CartException e1) {
			return responseData.failInfo(e1.getMessage()).toGsonString();
		}
		if(dishCount==null||dishId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try {
				cart=cartService.updateDishToCart(cart,dishId,dishCount);
				session.setAttribute(ConversationConfig.cartSession, cart);
				responseData.successInfo(SuccessMSG.updateSuccessMSG);
			} catch (CartException e) {
				responseData.failInfo(e.getMessage());
			}catch (Exception e) {
				responseData.failInfo(ErrorMSG.updateFail);
			}
		}
		return toGsonString();
	}
	
	/**
	 * 更新购物车中商品数量
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteDishCart",produces=EncodingConfig.produces)
	public String deleteDishCart(Cart cart,String dishId,HttpSession session){
		try {
			cart = getCartInSession(session);
		} catch (CartException e1) {
			return responseData.failInfo(e1.getMessage()).toGsonString();
		}
		if(cart==null||dishId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try {
				cart.deleteDish(dishId);
				session.setAttribute(ConversationConfig.cartSession, cart);
				responseData.successInfo(SuccessMSG.deleteSuccessMSG);
			} catch (Exception e) {
				responseData.failInfo(ErrorMSG.deleteFail);
			}
		}
		return toGsonString();
	}
	
	
	/**
	 * 根据购物车生成订单
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/cartToOrder",produces=EncodingConfig.produces)
	public String cartToOrder(Cart cart,String addressId,HttpSession session){
		try {
			cart = getCartInSession(session);
		} catch (CartException e1) {
			return responseData.failInfo(e1.getMessage()).toGsonString();
		}
		if(addressId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try {
				Customer orderCustomer=(Customer)session.getAttribute(ConversationConfig.customerSession);
				if(orderCustomer==null){
					responseData.failInfo(ErrorMSG.loginTimerOut);
				}else{
					cartService.putCartToOrder(cart, orderCustomer, addressId);
					responseData.successInfo(SuccessMSG.successMSG);
				}
			} catch (CartException e) {
				responseData.failInfo(e.getMessage());
			} catch (Exception e) {
				responseData.failInfo(ErrorMSG.operationFail);
			}
		}
		return toGsonString();
	}
}
