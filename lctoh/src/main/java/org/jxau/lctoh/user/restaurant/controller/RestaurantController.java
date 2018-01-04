package org.jxau.lctoh.user.restaurant.controller;


import javax.servlet.http.HttpSession;

import org.jxau.lctoh.position.location.domain.Location;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.ConversationMSG;
import org.jxau.lctoh.tool.config.EncodingConfig;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.tool.domain.ResponseData;
import org.jxau.lctoh.tool.exception.GetInfoException;
import org.jxau.lctoh.user.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/RestaurantController")
public class RestaurantController extends BaseController {
	@Autowired
	private RestaurantService restaurantService;
	
	/**
	 * 根据cityId获取餐馆信息
	 * @param cityId
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getRestaurantCityId",produces=EncodingConfig.produces)
	public String getRestaurantByCityId(String cityId,HttpSession session){
		if(cityId==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.parameterIsNullError));
		}
		Location location =(Location)session.getAttribute(ConversationMSG.locationSession);
		if(location==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.getLocationFail));
		}
		try {
			responseData.successInfo(restaurantService.getRestaueantByCityId(cityId, location));
		} catch (Exception e) {
			responseData.failInfo(ErrorMSG.notKnowError);
		}
		return Tools.gson.toJson(responseData);
	}
	
	
	/**
	 * 根据cityName获取餐馆信息
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getRestaurantByCityName",produces=EncodingConfig.produces)
	public String getRestaurantByCityName(HttpSession session){
		Location location =(Location)session.getAttribute(ConversationMSG.locationSession);
		if(location==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.getLocationFail));
		}
		try {
			responseData.successInfo(restaurantService.getRestaueantByLocation(location));
		} catch (GetInfoException e) {
			responseData.failInfo(e.getMessage());
		}catch (Exception e) {
			responseData.failInfo(ErrorMSG.notKnowError);
		}
		return Tools.gson.toJson(responseData);
	}
	
	/**
	 * 根据restaurantId获取餐馆信息
	 * @param restaurantId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getRestaurantByRestaurantId",produces=EncodingConfig.produces)
	public String getRestaurantByRestaurantId(String restaurantId){
		if(restaurantId==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.parameterIsNullError));
		}
		/*
		try {
			responseData.successInfo(restaurantService.findRestaurantService(restaurantId));
		} catch (GetInfoException e) {
			responseData.failInfo(e.getMessage());
		}catch (Exception e) {
			responseData.failInfo(ErrorMSG.notKnowError);
		}
		*/
		return Tools.gson.toJson(responseData);
	}
}
