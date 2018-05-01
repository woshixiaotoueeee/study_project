package org.jxau.lctoh.user.restaurant.controller;


import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jxau.lctoh.position.location.domain.Location;
import org.jxau.lctoh.position.region.domain.City;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.imageurl.ImagesUrl;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.jxau.lctoh.user.restaurant.domain.RestaurantCategory;
import org.jxau.lctoh.user.restaurant.domain.RestaurantWebModel;
import org.jxau.lctoh.user.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	 * @param 所属城市ID String city 详细地址信息  String info  经度 BigDecimal longitude 纬度 BigDecimal latitude ;
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
	public String updateRestaurantAddress(Location location,HttpSession session){
		Restaurant restaurant=(Restaurant) session.getAttribute(ConversationConfig.restaurantSession);
		if (restaurant==null||location==null
				||location.getLatitude()==null
				||location.getLongitude()==null
				||location.getInfo()==null
				||location.getCity()==null) {
			responseData.failInfo(ErrorMSG.parameterIsNull);
		} else {
			restaurant.setRestaurantLatitude(location.getLatitude());
			restaurant.setRestaurantLongitude(location.getLongitude());
			restaurant.setRestaurantAddressInfo(location.getInfo());
			restaurant.setRestaurantCity(new City(location.getCity()));
			try{
				restaurantService.updateRestaurant(restaurant);
				responseData.successInfo(SuccessMSG.updateSuccessMSG);
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.updateFail);
			}
		}
		return toGsonString();
	}
	
	/**
	 * 更新店家信息
	 * @param user restaurant
	 * <pre>
	 * 	user 说明：{
	 * 		String userSex 性别
	 *  	String userEmail 邮箱
	 *  	String userPhone 电话
	 * 	}
	 * restaurant{
	 * 	 BigDecimal restaurantDeliveryFee 配送费
	 * 	 String restaurantName 店名
	 * 	 String restaurantPhone 店家联系方式
	 *	 String restaurantNotice 公告
	 * 	}
	 * </pre>
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
	@RequestMapping(value="/updateRestaurant",produces=EncodingConfig.produces)
	public String updateRestaurant(Restaurant restaurant,User user,HttpSession session){
		Restaurant _restaurant=(Restaurant) session.getAttribute(ConversationConfig.restaurantSession);
		if (restaurant==null||user==null
				||restaurant.getRestaurantDeliveryFee()==null
				||restaurant.getRestaurantName()==null
				||restaurant.getRestaurantPhone()==null
				||restaurant.getRestaurantNotice()==null
				||user.getUserEmail()==null
				||user.getUserPhone()==null
				||user.getUserSex()==null
				) {
			responseData.failInfo(ErrorMSG.parameterIsNull);
		} else {
			User _user=_restaurant.getRestaurantUser();
			_user.setUserEmail(user.getUserEmail());
			_user.setUserSex(user.getUserSex());
			_user.setUserPhone(user.getUserPhone());
			_restaurant.setRestaurantUser(_user);
			_restaurant.setRestaurantDeliveryFee(restaurant.getRestaurantDeliveryFee());
			_restaurant.setRestaurantName(restaurant.getRestaurantName());
			_restaurant.setRestaurantPhone(restaurant.getRestaurantPhone());
			_restaurant.setRestaurantNotice(restaurant.getRestaurantNotice());
			try{
				restaurantService.updateRestaurant(restaurant);
				responseData.successInfo(SuccessMSG.updateSuccessMSG);
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.updateFail);
			}
		}
		return toGsonString();
	}
	
	
	/**
	 * 更新店铺图片
	 * @param file 文件
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为成功的信息 String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.customer.domain.Customer
	 */
	@ResponseBody
	@RequestMapping(value="/updateRestaurantPortrait",produces=EncodingConfig.produces)
	public String updateRestaurantPortrait(@RequestParam("file") MultipartFile file, HttpSession session){
		String xpath = session.getServletContext().getRealPath(File.separator).concat(ImagesUrl.RestaurantPortraitUrl.replace("/", File.separator));
		Restaurant restaurant=(Restaurant) session.getAttribute(ConversationConfig.restaurantSession);
		try {
			String fileName = file.getOriginalFilename();
	        //后缀判断
	        if (!fileName.endsWith(".jpg") &&! fileName.endsWith(".jpeg")    
	              &&! fileName.endsWith(".bmp")    
	                &&! fileName.endsWith(".gif")    
	                &&! fileName.endsWith(".png")
	                &&!fileName.endsWith(".JPG") 
	                &&! fileName.endsWith(".JPEG")    
	                &&! fileName.endsWith(".BMP")    
	                &&! fileName.endsWith(".GIF")    
	                &&! fileName.endsWith(".PNG")){
	        	responseData.failInfo(ErrorMSG.fileFormatError);
	        } else{
	        	//获取后缀
		        String prefix=fileName.substring(fileName.lastIndexOf(".")+1);    
		        //新文件名
		        String imagename=session.getId()+ System.currentTimeMillis()+"."+prefix;
		        //数据库路径
		        String sqlurl=ImagesUrl.RestaurantPortraitUrl.concat(imagename);
		        File targetFile = new File(xpath, imagename);
		        if (!targetFile.exists()) {
		            targetFile.mkdirs();
		        }
		        // 保存
		        try {
		            file.transferTo(targetFile);
		            restaurant.setRestaurantImage(sqlurl);
		            restaurantService.updateRestaurant(restaurant);
		            session.setAttribute(ConversationConfig.customerSession, restaurant);
		            responseData.successInfo(SuccessMSG.updateSuccessMSG);
		        }catch (Exception e) {
		        	e.printStackTrace();
		        	responseData.failInfo(ErrorMSG.updateFail);
		        }
	        }
		}catch (Exception e) {
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.notKnow);
		}
		return toGsonString();
	}
	/**
	 * 更新店家状态
	 * @param type Integer 1:营业 2:休息
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
	@RequestMapping(value="/updateRestaurantState",produces=EncodingConfig.produces)
	public String updateRestaurantState(Integer type,HttpSession session){
		Restaurant restaurant=(Restaurant) session.getAttribute(ConversationConfig.restaurantSession);
		if (restaurant==null||type==null) {
			responseData.failInfo(ErrorMSG.parameterIsNull);
		} else {
			if(type==1){
				restaurant.setRestaurantState(new State(70002));
			}else if(type==2){
				restaurant.setRestaurantState(new State(70003));
			}
			try{
				restaurantService.updateRestaurant(restaurant);
				session.setAttribute(ConversationConfig.restaurantSession, restaurant);
				responseData.successInfo(SuccessMSG.updateSuccessMSG);
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.updateFail);
			}
		}
		return toGsonString();
	}
	
	
	/**根据查询所有分类信息*/
	@ResponseBody
	@RequestMapping(value="/findAllRestaurantCategory",produces=EncodingConfig.produces)
	public String findAllRestaurantCategory(){
		responseData.successInfo(restaurantService.findAllRestaurantCategory());
		return toGsonString();
	}
	
	
	
	/**
	 * 根据店家识别码查询店家大概信息
	 * @param restaurantId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findRestaurantWebModel",produces=EncodingConfig.produces)
	public String findRestaurantWebModelByRestaurantId(String restaurantId){
		if(restaurantId==null){
			responseData.failInfo(ErrorMSG.parameterIsNull);
		}else{
			responseData.successInfo(restaurantService.findRestaurantWebModelByRestaurantId(restaurantId));
		}
		return toGsonString();
	}
	
	/**
	 * 更新店家信息
	 * @param user restaurant
	 * <pre>
	 * 	user 说明：{
	 * 		String userSex 性别
	 *  	String userEmail 邮箱
	 *  	String userPhone 电话
	 * 	}
	 * restaurant{
	 * 	 BigDecimal restaurantDeliveryFee 配送费
	 * 	 String restaurantName 店名
	 * 	 String restaurantPhone 店家联系方式
	 *	 String restaurantNotice 公告
	 * 	}
	 * </pre>
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
	@RequestMapping(value="/updateRestaurantNotice",produces=EncodingConfig.produces)
	public String updateRestaurantNotice(String notice,HttpSession session){
		Restaurant _restaurant=(Restaurant) session.getAttribute(ConversationConfig.restaurantSession);
		if (notice==null) {
			responseData.failInfo(ErrorMSG.parameterIsNull);
		} else {
			_restaurant.setRestaurantNotice(notice);
			try{
				restaurantService.updateRestaurant(_restaurant);
				responseData.successInfo(SuccessMSG.updateSuccessMSG);
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.updateFail);
			}
		}
		return toGsonString();
	}
	
	
	/**
	 * 获取登陆信息
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  Restaurant类型对象具体属性参考 Restaurant实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/getRestaurantLoginInfo",produces=EncodingConfig.produces)
	public String getRestaurantLoginInfo(HttpSession session){
		
		Restaurant _restaurant=(Restaurant) session.getAttribute(ConversationConfig.restaurantSession);
		if(_restaurant!=null){
			responseData.successInfo(_restaurant);
		}else{
			responseData.failInfo(ErrorMSG.loginTimerOut);
		}
		return toGsonString();
	}
}
