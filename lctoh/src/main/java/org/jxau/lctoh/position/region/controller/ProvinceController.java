package org.jxau.lctoh.position.region.controller;

import javax.servlet.http.HttpSession;

import org.jxau.lctoh.position.location.domain.Location;
import org.jxau.lctoh.position.region.service.ProvinceService;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.EncodingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ProvinceController") 
public class ProvinceController {
	@Autowired
	private ProvinceService provinceService;
	
	/**获取所有省份信息*/
	@ResponseBody
	@RequestMapping(value="/getAllProvince",produces=EncodingConfig.produces)
	public String getAllProvince(){
		return Tools.gson.toJson(provinceService.getAllProvince());
	}
	
}
