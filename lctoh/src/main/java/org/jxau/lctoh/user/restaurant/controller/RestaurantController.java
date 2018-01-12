package org.jxau.lctoh.user.restaurant.controller;


import javax.servlet.http.HttpSession;

import org.jxau.lctoh.position.location.domain.Location;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
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
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try {
				Location location =(Location)session.getAttribute(ConversationConfig.locationSession);
				responseData.successInfo(restaurantService.getRestaueantByCityId(cityId, location));
			} catch (Exception e) {
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	
	
	/**
	 * 根据cityName获取餐馆信息
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getRestaurantByCityName",produces=EncodingConfig.produces)
	public String getRestaurantByCityName(HttpSession session){
		Location location =(Location)session.getAttribute(ConversationConfig.locationSession);
		if(location==null){
			responseData.failInfo(ErrorMSG.getLocationFail);
		}else{
			try {
				responseData.successInfo(restaurantService.getRestaueantByLocation(location));
			}catch (Exception e) {
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
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
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try {
				responseData.successInfo(restaurantService.findRestaurantService(restaurantId));
			}catch (Exception e) {
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
}
