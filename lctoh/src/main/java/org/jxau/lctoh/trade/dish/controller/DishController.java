package org.jxau.lctoh.trade.dish.controller;

import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.trade.dish.service.DishService;
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
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	
	/**
	 * 根据菜肴分类ID获取菜肴信息（暂时没有实现功能）
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
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	
	
	
	
}
