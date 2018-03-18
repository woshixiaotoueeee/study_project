package org.jxau.lctoh.trade.dish.controller;

import javax.servlet.http.HttpSession;

import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.trade.dish.domain.DishCategory;
import org.jxau.lctoh.trade.dish.service.DishCategoryService;
import org.jxau.lctoh.trade.dish.service.DishService;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 菜肴操作接口
 * @author qdt_PC
 */
@Controller
@RequestMapping("/DishController")
public class DishController extends BaseController{
	@Autowired
	private DishService dishService;
	@Autowired
	private DishCategoryService dishCategoryService;
	
	/**
	 * 根据ID获取菜肴信息
	 * @param dishId 菜肴ID String 字符串
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  Dish 类型对象具体属性参考 Dish实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.trade.dish.domain.Dish
	 */
	@ResponseBody
	@RequestMapping(value="/findDishByDishId",produces=EncodingConfig.produces)
	public String findDishByDishId(String dishId){
		if(dishId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try{
				responseData.successInfo(dishService.findDishByDishId(dishId));
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	
	
	/**
	 * 根据菜肴分类ID获取菜肴信息
	 * @param dishCategoryId 菜肴分类ID String 字符串
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Dish&gt; 类型对象具体属性参考 Dish实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.trade.dish.domain.Dish
	 */
	@ResponseBody
	@RequestMapping(value="/findDishByDishCategoryId",produces=EncodingConfig.produces)
	public String findDishByDishCategoryId(String dishCategoryId){
		if(dishCategoryId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try{
				responseData.successInfo(dishService.findDishByDishCategoryId(dishCategoryId));
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	
	
	
	
	/**
	 * 根据客户查询收藏菜肴信息
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Dish&gt; 类型对象具体属性参考 Restaurant实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.trade.dish.domain.Dish
	 */
	@ResponseBody
	@RequestMapping(value="/findCollectDish",produces=EncodingConfig.produces)
	public String findCollectDish(HttpSession session){
		Customer customer=(Customer)session.getAttribute(ConversationConfig.customerSession);
		try {
			responseData.successInfo(dishService.findCollectDishByCustomer(customer));
		}catch (Exception e) {
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.selectFail);
		}
		return toGsonString();
	}
	/**
	 * 添加询收藏菜肴
	 * @param dishId 菜肴ID String 字符串
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
	@RequestMapping(value="/addCollectDish",produces=EncodingConfig.produces)
	public String addCollectDish(String dishId,HttpSession session){
		Customer customer=(Customer)session.getAttribute(ConversationConfig.customerSession);
		try {
			dishService.addCollectDish(customer, dishId);
			responseData.successInfo(SuccessMSG.addSuccessMSG);
		}catch (Exception e) {
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.addFail);
		}
		return toGsonString();
	}
	/**
	 * 删除询收藏菜肴
	 * @param dishId 菜肴ID String 字符串
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
	@RequestMapping(value="/deleteCollectDish",produces=EncodingConfig.produces)
	public String deleteCollectDish(String dishId,HttpSession session){
		Customer customer=(Customer)session.getAttribute(ConversationConfig.customerSession);
		try {
			dishService.deleteCollectDish(customer, dishId);
			responseData.successInfo(SuccessMSG.deleteSuccessMSG);
		}catch (Exception e) {
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.deleteFail);
		}
		return toGsonString();
	}
	
	
	
	
	/**
	 * 根据店家ID获取菜肴信息
	 * @param restaurantId 店家ID String 字符串
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Dish&gt; 类型对象具体属性参考 Dish实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.trade.dish.domain.Dish
	 */
	@ResponseBody
	@RequestMapping(value="/findDishByRestaurantId",produces=EncodingConfig.produces)
	public String findDishByRestaurantId(String restaurantId){
		if(restaurantId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try{
				responseData.successInfo(dishService.findDishByRestaurantId(restaurantId));
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	
	
	/**
	 * 添加菜肴分类
	 * @param dishCategoryName 菜肴分类名
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为成功的信息  String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.trade.dish.domain.DishCategory
	 */
	@ResponseBody
	@RequestMapping(value="/addDishCategory",produces=EncodingConfig.produces)
	public String addDishCategory(DishCategory dishCategory,HttpSession session){
		if(dishCategory==null||dishCategory.getDishCategoryName()==null){
			responseData.failInfo(ErrorMSG.parameterIsNull);
		}else{
			Restaurant restaurant=(Restaurant) session.getAttribute(ConversationConfig.restaurantSession);
			dishCategory.setDishCategoryRestaurant(restaurant);
			dishCategory.setDishCategoryState(new State(30001));
			dishCategory.setDishCategoryId(Tools.getRandomString(32));
			try{
				
				dishCategoryService.addDishCategory(dishCategory);
				responseData.successInfo(SuccessMSG.addSuccessMSG);
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.addFail);
			}
		}
		return toGsonString();
	}
	
	
	/**
	 * 更新菜肴分类
	 * @param dishCategoryName 菜肴分类名  dishCategoryId 菜肴分类ID
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为成功的信息  String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/updateDishCategory",produces=EncodingConfig.produces)
	public String updateDishCategory(DishCategory dishCategory){
		if(dishCategory==null||dishCategory.getDishCategoryName()==null||dishCategory.getDishCategoryId()==null){
			responseData.failInfo(ErrorMSG.parameterIsNull);
		}else{
			try{
				dishCategoryService.updateDishCategory(dishCategory);
				responseData.successInfo(SuccessMSG.updateSuccessMSG);
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	/**
	 * 更新菜肴分类
	 * @param dishCategoryId 菜肴分类ID
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为成功的信息  String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/deleteDishCategory",produces=EncodingConfig.produces)
	public String deleteDishCategory(DishCategory dishCategory){
		if(dishCategory==null||dishCategory.getDishCategoryId()==null){
			responseData.failInfo(ErrorMSG.parameterIsNull);
		}else{
			try{
				dishCategoryService.deleteDishCategory(dishCategory);
				responseData.successInfo(SuccessMSG.deleteSuccessMSG);
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.deleteFail);
			}
		}
		return toGsonString();
	}
}
