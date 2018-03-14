package org.jxau.lctoh.user.restaurant.controller;


import javax.servlet.http.HttpSession;

import org.jxau.lctoh.position.location.domain.Location;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 店家操作接口
 * @author qdt_PC
 */
@Controller
@RequestMapping("/RestaurantController")
public class RestaurantController extends BaseController {
	@Autowired
	private RestaurantService restaurantService;
	
	
	/**
	 * 根据cityId获取餐馆信息
	 * @param cityId 城市ID String 字符串
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Restaurant&gt; 类型对象具体属性参考 Restaurant实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.restaurant.domain.Restaurant
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
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	
	/**
	 * 根据cityName获取餐馆信息
	 * @return 
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Restaurant&gt; 类型对象具体属性参考 Restaurant实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.restaurant.domain.Restaurant
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
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	
	/**
	 * 根据restaurantId获取餐馆信息
	 * @param restaurantId 店家识别码 String 字符串
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为Restaurant类型对象具体属性参考 Restaurant实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.restaurant.domain.Restaurant
	 */
	@ResponseBody
	@RequestMapping(value="/getRestaurantByRestaurantId",produces=EncodingConfig.produces)
	public String getRestaurantByRestaurantId(String restaurantId,HttpSession session){
		if(restaurantId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try {
				Location location =(Location)session.getAttribute(ConversationConfig.locationSession);
				responseData.successInfo(restaurantService.findRestaurantService(restaurantId,location));
			}catch (Exception e) {
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	
	/**
	 * 根据城市名和店家分类ID获取餐馆信息
	 * @param restaurantCategoryId 店家分类ID String 字符串
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Restaurant&gt; 类型对象具体属性参考 Restaurant实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.restaurant.domain.Restaurant
	 */
	@ResponseBody
	@RequestMapping(value="/getRestaurantByCityNameAndrcid",produces=EncodingConfig.produces)
	public String getRestaurantByCityNameAndrcid(String restaurantCategoryId,HttpSession session){
		Location location =(Location)session.getAttribute(ConversationConfig.locationSession);
		if(location==null){
			responseData.failInfo(ErrorMSG.getLocationFail);
		}else if(restaurantCategoryId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try {
				responseData.successInfo(restaurantService.getRestaueantByLocationAndrcid(location,restaurantCategoryId));
			}catch (Exception e) {
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	/**
	 * 根据客户查询收藏店家信息
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Restaurant&gt; 类型对象具体属性参考 Restaurant实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.restaurant.domain.Restaurant
	 */
	@ResponseBody
	@RequestMapping(value="/findCollectRestaurant",produces=EncodingConfig.produces)
	public String findCollectRestaurant(HttpSession session){
		Customer customer=(Customer)session.getAttribute(ConversationConfig.customerSession);
		try {
			responseData.successInfo(restaurantService.findCollectRestaurantByCustomer(customer));
		}catch (Exception e) {
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.selectFail);
		}
		return toGsonString();
	}
	/**
	 * 添加询收藏店家
	 * @param restaurantId 店家ID String 字符串
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为成功的信息 String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/addCollectRestaurant",produces=EncodingConfig.produces)
	public String addCollectRestaurant(String restaurantId,HttpSession session){
		Customer customer=(Customer)session.getAttribute(ConversationConfig.customerSession);
		try {
			restaurantService.addCollectRestaurant(customer,restaurantId);
			responseData.successInfo(SuccessMSG.addSuccessMSG);
		}catch (Exception e) {
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.addFail);
		}
		return toGsonString();
	}
	/**
	 * 删除询收藏店家
	 * @param restaurantId 店家ID String 字符串
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为成功的信息 String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/deleteCollectRestaurant",produces=EncodingConfig.produces)
	public String deleteCollectRestaurant(String restaurantId,HttpSession session){
		Customer customer=(Customer)session.getAttribute(ConversationConfig.customerSession);
		try {
			restaurantService.deleteCollectRestaurant(customer,restaurantId);
			responseData.successInfo(SuccessMSG.deleteSuccessMSG);
		}catch (Exception e) {
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.deleteFail);
		}
		return toGsonString();
	}
	
	
	/**
	 * 更新店家地址
	 * @param restaurantId 店家ID String 字符串
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为成功的信息 String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/updateRestaurantAddress",produces=EncodingConfig.produces)
	public String updateRestaurantAddress(HttpSession session){
		
		
		
		
		return toGsonString();
	}
}
