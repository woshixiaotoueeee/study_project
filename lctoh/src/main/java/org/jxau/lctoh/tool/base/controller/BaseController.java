package org.jxau.lctoh.tool.base.controller;


import org.jxau.lctoh.tool.domain.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/ProvinceController") 
public class BaseController {
	@Autowired
	protected ResponseData responseData;
	
	
	/**
	 * 将responseData转换成Gson类型的字符串
	 * @return
	 */
	public String toGsonString(){
		return responseData.toGsonString();
	}
}
