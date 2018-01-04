package org.jxau.lctoh.position.region.controller;



import org.jxau.lctoh.position.region.service.ProvinceService;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.EncodingConfig;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/ProvinceController") 
public class ProvinceController extends BaseController{
	@Autowired
	private ProvinceService provinceService;
	
	/**
	 * 获取所有省份信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAllProvince",produces=EncodingConfig.produces)
	public String getAllProvince(){
		try{
			return Tools.gson.toJson(responseData.successInfo(provinceService.getAllProvince()));
		}catch(Exception e){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowError));
		}
		
	}
	
}
