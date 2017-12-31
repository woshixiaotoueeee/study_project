package org.jxau.lctoh.user.restaurant.controller;


import javax.servlet.http.HttpSession;

import org.jxau.lctoh.position.location.domain.Location;
import org.jxau.lctoh.tool.Tools;
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

@Controller
@RequestMapping("/RestaurantController")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private ResponseData responseData;
	
	
	/**
	 * 根据cityId获取餐馆信息
	 * */
	@ResponseBody
	@RequestMapping(value="/getRestaurantCityId",produces=EncodingConfig.produces)
	public String getRestaurantByCityId(String cityId,HttpSession session){
		if(cityId==null){
			Tools.gson.toJson(responseData.failInfo(ErrorMSG.parameterIsNullError));
		}
		Location location =(Location)session.getAttribute(ConversationMSG.locationSession);
		
		try {
			return Tools.gson.toJson(responseData.successInfo(restaurantService.getRestaueantByCityId(cityId, location)));
		} catch (Exception e) {
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowError));
		}
	}
	
	
	/**
	 * 根据cityId获取餐馆信息
	 * */
	@ResponseBody
	@RequestMapping(value="/getRestaurantByCityName",produces=EncodingConfig.produces)
	public String getRestaurantByCityName(HttpSession session){
		Location location =(Location)session.getAttribute(ConversationMSG.locationSession);
		if(location==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowError));
		}
		try {
			return Tools.gson.toJson(responseData.successInfo(restaurantService.getRestaueantByLocation(location)));
		} catch (GetInfoException e) {
			return Tools.gson.toJson(responseData.failInfo(e.getMessage()));
		}catch (Exception e) {
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowError));
		}
	}
	
	/**
	 * 根据restaurantId获取餐馆信息
	 * */
	@ResponseBody
	@RequestMapping(value="/getRestaurantByRestaurantId",produces=EncodingConfig.produces)
	public String getRestaurantByRestaurantId(String restaurantId){
		if(restaurantId==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.parameterIsNullError));
		}
		/*
		
		
		Location location =(Location)session.getAttribute(ConversationMSG.locationSession);
		if(location==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowError));
		}
		try {
			return Tools.gson.toJson(responseData.successInfo(restaurantService.getRestaueantByLocation(location)));
		} catch (GetInfoException e) {
			return Tools.gson.toJson(responseData.failInfo(e.getMessage()));
		}catch (Exception e) {
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowError));
		}
		*/
		return restaurantId;
	}
}
