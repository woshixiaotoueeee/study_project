package org.jxau.lctoh.position.location.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jxau.lctoh.position.location.domain.Location;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.ConversationMSG;
import org.jxau.lctoh.tool.config.EncodingConfig;
import org.jxau.lctoh.tool.config.ErrorMSG;



@Controller
@RequestMapping("/LocationController") 
public class LocationController {
	/**定位*/
	@ResponseBody
	@RequestMapping(value="/setLocation",produces=EncodingConfig.produces)
	public String setLocation(Location location,HttpSession session){
		/**
		 * 判断定位信息是否为空
		 * */
		if(location==null||location.getCity()==null||location.getLatitude()==null||location.getLongitude()==null){
			return ErrorMSG.fail;
		}
		session.setAttribute(ConversationMSG.locationSession, location);
		
		
		
		
		
		
		
		
		
		
		return ErrorMSG.success;
	}
	
	/**前台获取定位信息的接口*/
	@ResponseBody
	@RequestMapping(value="/getLocation",produces=EncodingConfig.produces)
	public String getLocation(HttpSession session){
		/**
		 * 判断定位信息是否为空
		 * */
		Object location =session.getAttribute(ConversationMSG.locationSession);
		if(location==null){
			return null;
		}
		return Tools.gson.toJson(location);
	}
	
	
}
