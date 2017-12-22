package org.jxau.lctoh.position.location.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jxau.lctoh.position.location.domain.Location;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.Config;

@Controller
@RequestMapping("/LocationController") 
public class LocationController {
	/**定位*/
	@ResponseBody
	@RequestMapping(value="/setLocation",produces=Config.produces)
	public String setLocation(Location location,HttpSession session){
		/**
		 * 判断定位信息是否为空
		 * */
		if(location==null||location.getLatitude()==null||location.getLongitude()==null){
			return Config.fail;
		}
		session.setAttribute(Config.locationSession, location);
		return Config.success;
	}
	
	/**前台获取定位信息的接口*/
	@ResponseBody
	@RequestMapping(value="/getLocation",produces=Config.produces)
	public String getLocation(HttpSession session){
		/**
		 * 判断定位信息是否为空
		 * */
		Object location =session.getAttribute(Config.locationSession);
		if(location==null){
			return null;
		}
		return Tools.gson.toJson(location);
	}
}
