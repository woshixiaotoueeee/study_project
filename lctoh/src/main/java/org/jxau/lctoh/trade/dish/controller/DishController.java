package org.jxau.lctoh.trade.dish.controller;

import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.EncodingConfig;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.trade.dish.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/DishController")
public class DishController extends BaseController{
	@Autowired
	private DishService dishService;
	
	/**
	 * 根据ID获取菜肴信息
	 * @param dishId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findDishByDishId",produces=EncodingConfig.produces)
	public String findDishByDishId(String dishId){
		if(dishId==null)return Tools.gson.toJson(responseData.failInfo(ErrorMSG.parameterIsNullError));
		try{
			responseData.successInfo(dishService.findDishByDishId(dishId));
		}catch(Exception e){
			responseData.failInfo(ErrorMSG.notKnowError);
		}
		return Tools.gson.toJson(responseData);
	}
	
	/**
	 * 根据菜肴分类ID获取菜肴信息
	 * @param dishCategoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findDishByDishCategoryId",produces=EncodingConfig.produces)
	public String findDishByDishCategoryId(String dishCategoryId){
		if(dishCategoryId==null)return Tools.gson.toJson(responseData.failInfo(ErrorMSG.parameterIsNullError));
		try{
			responseData.successInfo(dishService.findDishByDishCategoryId(dishCategoryId));
		}catch(Exception e){
			responseData.failInfo(ErrorMSG.notKnowError);
		}
		return Tools.gson.toJson(responseData);
	}
	
	/**
	 * 根据店家ID获取菜肴信息
	 * @param restaurantId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findDishByRestaurantId",produces=EncodingConfig.produces)
	public String findDishByRestaurantId(String restaurantId){
		if(restaurantId==null)return Tools.gson.toJson(responseData.failInfo(ErrorMSG.parameterIsNullError));
		try{
			responseData.successInfo(dishService.findDishByRestaurantId(restaurantId));
		}catch(Exception e){
			responseData.failInfo(ErrorMSG.notKnowError);
		}
		return Tools.gson.toJson(responseData);
	}
	
	
	
	
}
