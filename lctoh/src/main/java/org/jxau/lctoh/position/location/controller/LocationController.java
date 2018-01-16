package org.jxau.lctoh.position.location.controller;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jxau.lctoh.position.location.domain.Location;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;




/**
 * 定位信息操作Controller
 * @author qdt_PC
 */
@Controller
@RequestMapping("/LocationController") 
public class LocationController  extends BaseController{
	
	
	/**
	 * 定位
	 * @param location 定位信息
	 * <pre>
	 * location 说明：{
	 * 	省份  String province;
	 *	城市  String city;
	 *	详细地址信息 String info;
	 *	经度 BigDecimal longitude ;
	 *	纬度 BigDecimal latitude ;
	 * }
	 * </pre>
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  Address 类型对象具体属性参考 Addresss实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.position.location.domain.Location
	 */
	@ResponseBody
	@RequestMapping(value="/setLocation",produces=EncodingConfig.produces)
	public String setLocation(Location location,HttpSession session){
		/**
		 * 判断定位信息是否为空
		 * */
		if(location==null||location.getCity()==null||location.getLatitude()==null||location.getLongitude()==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			//设置定位信息
			session.setAttribute(ConversationConfig.locationSession, location);
			responseData.successInfo(SuccessMSG.locationSuccessMSG);
		}
		return toGsonString();
	}
	
	/**
	 * 前台获取定位信息的接口
	 * @return
	 *	<pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  Location 类型对象具体属性参考 Location实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 *  @see org.jxau.lctoh.position.location.domain.Location
	 */
	@ResponseBody
	@RequestMapping(value="/getLocation",produces=EncodingConfig.produces)
	public String getLocation(HttpSession session){
		/**
		 * 判断定位信息是否为空
		 * */
		Object location =session.getAttribute(ConversationConfig.locationSession);
		if(location==null){
			responseData.failInfo(ErrorMSG.getLocationFail);
		}else{
			responseData.successInfo(location);
		}
		return toGsonString();
	}
}
