package org.jxau.lctoh.position.region.controller;



import org.jxau.lctoh.position.region.service.ProvinceService;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
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
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Province&gt; 类型对象具体属性参考 Province实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.position.region.domain.Province
	 */
	@ResponseBody
	@RequestMapping(value="/getAllProvince",produces=EncodingConfig.produces)
	public String getAllProvince(){
		try{
			responseData.successInfo(provinceService.getAllProvince());
		}catch(Exception e){
			e.getMessage();
			responseData.failInfo(ErrorMSG.selectFail);
		}
		return Tools.gson.toJson(responseData);
	}
	
}
