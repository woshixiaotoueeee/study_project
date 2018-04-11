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
import org.jxau.lctoh.trade.order.service.OrderService;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 购物车相关操作
 * @author qdt_PC
 */
@Controller
@RequestMapping("/CartController")
public class CartController extends BaseController{
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	/**
	 * 获取session中的购物车
	 * @param session
	 * @return
	 * @throws CartException
	 */
	private Cart getCartInSession(HttpSession session) throws CartException{
		Cart cart=(Cart) session.getAttribute(ConversationConfig.cartSession);
		if(cart==null)throw new CartException(ErrorMSG.cartTimerOut);
		return cart;
	}
	
	/**
	 * 获取购物车信息
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  Cart 类型对象具体属性参考 Cart实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.trade.cart.domain.Cart
	 */
	@ResponseBody
	@RequestMapping(value="/getCart",produces=EncodingConfig.produces)
	public String getCart(HttpSession session){
		try {
			responseData.successInfo(getCartInSession(session));
		} catch (CartException e) {
			e.printStackTrace();
			responseData.failInfo(e.getMessage());
		}
		return toGsonString();
	}
	
	/**
	 * 往购物车中添加商品
	 * @param dishId 需要加入购物车的菜肴的识别码 String 字符串
	 * @param dishCount 加入数量  Integer 整形数字
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
	@RequestMapping(value="/addDishCart",produces=EncodingConfig.produces)
	public String addDishCart(Cart cart,String dishId,Integer dishCount,HttpSession session){
		try {
			cart = getCartInSession(session);
		} catch (CartException e) {
			e.printStackTrace();
			return responseData.failInfo(e.getMessage()).toGsonString();
		}
		if(dishCount==null||dishId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try {
				cart=cartService.addDishToCart(cart,dishId,dishCount);
				session.setAttribute(ConversationConfig.cartSession, cart);
				responseData.successInfo(SuccessMSG.addSuccessMSG);
			} catch (CartException e) {
				e.printStackTrace();
				responseData.failInfo(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.addFail);
			}
		}
		return toGsonString();
	}
	
	/**
	 * 更新购物车中商品数量
	 * @param dishId 需要更新购物车的菜肴的识别码 String 字符串
	 * @param dishCount 更新后的数量  Integer 整形数字
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
	@RequestMapping(value="/updateDishCart",produces=EncodingConfig.produces)
	public String updateDishCart(Cart cart,String dishId,Integer dishCount,HttpSession session){
		try {
			cart = getCartInSession(session);
		} catch (CartException e) {
			e.printStackTrace();
			return responseData.failInfo(e.getMessage()).toGsonString();
		}
		if(dishCount==null||dishId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try {
				cart=cartService.updateDishToCart(cart,dishId,dishCount);
				session.setAttribute(ConversationConfig.cartSession, cart);
				responseData.successInfo(SuccessMSG.updateSuccessMSG);
			} catch (CartException e) {
				e.printStackTrace();
				responseData.failInfo(e.getMessage());
			}catch (Exception e) {
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.updateFail);
			}
		}
		return toGsonString();
	}
	
	/**
	 * 删除购物车中商品
	 * @param dishId 需要删除购物车的菜肴的识别码 String 字符串
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
	@RequestMapping(value="/deleteDishCart",produces=EncodingConfig.produces)
	public String deleteDishCart(Cart cart,String dishId,HttpSession session){
		try {
			cart = getCartInSession(session);
		} catch (CartException e) {
			e.printStackTrace();
			return responseData.failInfo(e.getMessage()).toGsonString();
		}
		if(cart==null||dishId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try {
				cart.deleteDish(dishId);
				session.setAttribute(ConversationConfig.cartSession, cart);
				responseData.successInfo(SuccessMSG.deleteSuccessMSG);
			} catch (Exception e) {
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.deleteFail);
			}
		}
		return toGsonString();
	}
	
	
	/**
	 * 
	 * @param session
	 * @return
	 */
	/**
	 * 根据购物车生成订单
	 * @param addressId 客户配送地址的识别码 String 字符串
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：订单信息
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/cartToOrder",produces=EncodingConfig.produces)
	public String cartToOrder(Cart cart,HttpSession session){
		try {
			cart = getCartInSession(session);
			Customer orderCustomer=(Customer)session.getAttribute(ConversationConfig.customerSession);
			if(orderCustomer==null){
				responseData.failInfo(ErrorMSG.loginTimerOut);
			}else{
				String id=cartService.putCartToOrder(cart, orderCustomer);
				responseData.successInfo(orderService.findOrderByOrderId(id));
			}
		} catch (CartException e) {
			e.printStackTrace();
			return responseData.failInfo(e.getMessage()).toGsonString();
		}catch (Exception e) {
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.operationFail);
		}
		return toGsonString();
	}
}
