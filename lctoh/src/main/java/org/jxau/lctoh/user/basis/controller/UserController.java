package org.jxau.lctoh.user.basis.controller;


import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.trade.cart.domain.Cart;
import org.jxau.lctoh.user.admin.domain.Admin;
import org.jxau.lctoh.user.admin.service.AdminService;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.basis.exception.VerificationCodeException;
import org.jxau.lctoh.user.basis.service.UserService;
import org.jxau.lctoh.user.basis.service.VerificationCodeService;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.customer.service.CustomerService;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.jxau.lctoh.user.restaurant.service.RestaurantService;
import org.jxau.lctoh.user.rider.domain.Rider;
import org.jxau.lctoh.user.rider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/UserController")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private RiderService riderService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private VerificationCodeService verificationCodeService;
	
	
	
	/**
	 * 账号密码登陆
	 * @param user 登陆账号密码
	 * <pre>
	 * user说明{
	 * 	userAccount:账号 String 字符串;
		userPassword:密码 String 字符串;
	 * }
	 * </pre>
	 * @param type Integer 整形数字 登陆选择
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：登录成功所需跳转的url String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.basis.domain.User
	 */
	@ResponseBody
	@RequestMapping(value="/login",produces=EncodingConfig.produces)
	public String login(User user,Integer type,HttpSession session){
		if(type==null){
			responseData.failInfo(ErrorMSG.notKnowUser);
		}else if(user==null){
			responseData.failInfo(ErrorMSG.accountAndPasswordIsNull);
		}else if(user.getUserAccount()==null){
			responseData.failInfo(ErrorMSG.accountIsNull);
		}else if(user.getUserPassword()==null){
			responseData.failInfo(ErrorMSG.passwordIsNull);
		}else{
			//判断登陆的角色
			try{
				switch(type){
					case 1:
						Customer customer=customerService.login(user);
						session.setAttribute(ConversationConfig.customerSession, customer);
						session.setAttribute(ConversationConfig.cartSession, new Cart());
						responseData.successInfo(SuccessMSG.customerSuccessUrl);
						break;
					case 2:
						Admin admin=adminService.login(user);
						session.setAttribute(ConversationConfig.adminSession, admin);
						responseData.successInfo(SuccessMSG.adminSuccessUrl);
						break;
					case 3:
						Rider rider=riderService.login(user);
						session.setAttribute(ConversationConfig.riderSession, rider);
						ServletContext servletContext=session.getServletContext();
						Map riderMap=(Map)servletContext.getAttribute(ConversationConfig.riderContext);
						riderMap.put(rider.getRiderId(), rider);
						synchronized(this){
							servletContext.setAttribute(ConversationConfig.riderContext, riderMap);
						}
						responseData.successInfo(SuccessMSG.riderSuccessUrl);
						break;
					case 4:
						Restaurant restaurant=restaurantService.login(user);
						session.setAttribute(ConversationConfig.restaurantSession, restaurant);
						responseData.successInfo(SuccessMSG.restaurantSuccessUrl);
						break;
					default : responseData.failInfo(ErrorMSG.notKnowUser);
				}
			}catch (UserException e) {
				e.printStackTrace();
				responseData.failInfo(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.notKnow);
			}
		}
		return toGsonString();
	}
	
	/**
	 * 根据 账号 验证码 登陆
	 * @param userAccount String 字符串  账号
	 * @param code String 字符串 验证码 
	 * @param type Integer 整形数字 登陆选择
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：登录成功所需跳转的url String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/loginByCode",produces=EncodingConfig.produces)
	public String loginByCode(String userAccount,String code,Integer type,HttpSession session){
		//验证信息
		if(type==null){
			responseData.failInfo(ErrorMSG.notKnowUser);
		}else if(userAccount==null){
			responseData.failInfo(ErrorMSG.accountIsNull);
		}else if(code==null){
			responseData.failInfo(ErrorMSG.codeError);
		}else{
			try{
				switch(type){
					case 1:
						Customer customer=customerService.loginByCode(userAccount,code);
						session.setAttribute(ConversationConfig.customerSession, customer);
						responseData.successInfo(SuccessMSG.customerSuccessUrl);
						break;
					case 2:
						Admin admin=adminService.loginByCode(userAccount,code);
						session.setAttribute(ConversationConfig.adminSession, admin);
						responseData.successInfo(SuccessMSG.adminSuccessUrl);
						break;
					case 3:
						Rider rider=riderService.loginByCode(userAccount,code);
						session.setAttribute(ConversationConfig.riderSession, rider);
						ServletContext servletContext=session.getServletContext();
						Map riderMap=(Map)servletContext.getAttribute(ConversationConfig.riderContext);
						riderMap.put(rider.getRiderId(), rider);
						synchronized(this){
							servletContext.setAttribute(ConversationConfig.riderContext, riderMap);
						}
						responseData.successInfo(SuccessMSG.riderSuccessUrl);
						break;
					case 4:
						Restaurant restaurant=restaurantService.loginByCode(userAccount,code);
						session.setAttribute(ConversationConfig.restaurantSession, restaurant);
						responseData.successInfo(SuccessMSG.restaurantSuccessUrl);
						break;
					default : responseData.failInfo(ErrorMSG.notKnowUser);
				}
			}catch (UserException e) {
				e.printStackTrace();
				responseData.failInfo(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.notKnow);
			}
		}
		return toGsonString();
	}
	
	
	
	/**
	 * 根据 邮箱 验证码  ‘登陆’
	 * @param userEmail String 字符串  邮箱
	 * @param code String 字符串 验证码 
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：登录成功的信息 String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/verificationByCode",produces=EncodingConfig.produces)
	public String verificationByCode(String userEmail,String code,HttpSession session){
		//验证信息
		if(userEmail==null){
			responseData.failInfo(ErrorMSG.emailIsNull);
		}else if(code==null){
			responseData.failInfo(ErrorMSG.codeIsNullError);
		}else if(!userEmail.matches("\\w+@\\w+\\.\\w+")){
			responseData.failInfo(ErrorMSG.emailFormatError);
			
		}else{
			try {
				User user=userService.findByEmailAndCode(userEmail,code);
				session.setAttribute(ConversationConfig.userSession, user);
				responseData.successInfo(SuccessMSG.sendEmailSuccess);
			} catch (UserException e) {
				e.printStackTrace();
				responseData.failInfo(e.getMessage());
			}catch (Exception e) {
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.notKnow);
			}
		}
		return toGsonString();
	}
	
	
	
	
	/**
	 * 修改密码
	 * @param password String 字符串 密码
	 * @param _password String 字符串 确认密码
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：更改密码成功  String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/updatePassword",produces=EncodingConfig.produces)
	public String updatePassword(String password,String _password,HttpSession session){
		User user=(User)session.getAttribute(ConversationConfig.userSession);
		//验证信息
		if(password==null){
			responseData.failInfo(ErrorMSG.passwordIsNull);
		}else if(!password.equals(_password)){
			responseData.failInfo(ErrorMSG.passwordNotSameError);
		}else if(user==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			user.setUserPassword(password);
			try {
				responseData.successInfo(userService.updateUser(user));
			} catch (Exception e) {
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.operationFail);
			}
		}
		return toGsonString();
	}
	
	/**
	 * 根据账号获取验证码
	 * @param userAccount String 字符串 账号
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：验证码
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/getCodeByUserAccount",produces=EncodingConfig.produces)
	public String getCodeByUserAccount(String userAccount){
		if(userAccount==null){
			responseData.failInfo(ErrorMSG.accountIsNull);
		}else{
			try {
				responseData.successInfo(verificationCodeService.setCode(userAccount));
			} catch (VerificationCodeException e) {
				e.printStackTrace();
				responseData.failInfo(e.getMessage());
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.operationFail);
			}
		}
		return toGsonString();
	}
	
	
	/**
	 * 根据邮箱获取验证码
	 * @param userEmail String 字符串 邮箱
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：验证码
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/getCodeByUserEmail",produces=EncodingConfig.produces)
	public String getCodeByUserEmail(String userEmail){
		if(userEmail==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.emailIsNull));
		}else if(!userEmail.matches("\\w+@\\w+\\.\\w+")){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.emailFormatError));
			
		}else{
			try {
				responseData.successInfo(verificationCodeService.setCodeByUserEmail(userEmail));
			} catch (VerificationCodeException e) {
				e.printStackTrace();
				responseData.failInfo(e.getMessage());
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.operationFail);
			}
		}
		return toGsonString();
	}
	
	
	
	/**
	 * 注册
	 * */
	/**
	 * 根据邮箱获取验证码
	 * @param user User 注册信息
	 * <pre>
	 * user说明｛
	 * 	userPassword String 字符串 密码 
	 * 	userSex String 字符串 性别
	 * 	userEmail String 字符串 邮箱
	 * 	userPhone String 字符串 电话
	 * ｝
	 * </pre>
	 * @param _userPassword String 确认密码
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：注册成功的用户信息user 参考user实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.basis.domain.User
	 */
	@ResponseBody
	@RequestMapping(value="/register",produces=EncodingConfig.produces)
	public String register(User user,String _userPassword){
		//验证信息
		if(user==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else if(user.getUserPassword()==null){
			responseData.failInfo(ErrorMSG.passwordIsNull);
		}else if(user.getUserSex()==null){
			responseData.failInfo(ErrorMSG.sexIsNull);
		}else if(user.getUserPhone()==null){
			responseData.failInfo(ErrorMSG.phoneIsNull);
		}else if(user.getUserEmail()==null){
			responseData.failInfo(ErrorMSG.emailIsNull);
		}else if(!user.getUserEmail().matches("\\w+@\\w+\\.\\w+")){
			responseData.failInfo(ErrorMSG.emailFormatError);
		}else if(!user.getUserPassword().equals(_userPassword)){
			responseData.failInfo(ErrorMSG.passwordNotSameError);
		}else{
			try{
				user=customerService.addCustomerRegister(user);
				responseData.successInfo(user);
			}catch(UserException e){
				e.printStackTrace();
				responseData.failInfo(e.getMessage());
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.operationFail);
			}
		}
		return toGsonString();
	}
	
	
	
	/**
	 * 注销
	 * @param type Integer 整形数字，注销类型：1，客户；2，店家；3，骑手；4，admin
	 * @return
	 * @throws UserException
	 */
	@ResponseBody
	@RequestMapping(value="/logout",produces=EncodingConfig.produces)	
	private String logout(Integer type,HttpSession session){
		try{
			switch(type){
			case 1:
				session.setAttribute(ConversationConfig.customerSession, null);
				responseData.successInfo(SuccessMSG.successMSG);
				break;
			case 2:
				session.setAttribute(ConversationConfig.restaurantSession, null);
				responseData.successInfo(SuccessMSG.successMSG);
				break;
			case 3:
				Rider rider=(Rider) session.getAttribute(ConversationConfig.riderSession);
				session.setAttribute(ConversationConfig.riderSession, null);
				ServletContext servletContext=session.getServletContext();
				Map riderMap=(Map)servletContext.getAttribute(ConversationConfig.riderContext);
				riderMap.remove(rider.getRiderId());
				responseData.successInfo(SuccessMSG.successMSG);
				break;
			case 4:
				session.setAttribute(ConversationConfig.adminSession, null);
				responseData.successInfo(SuccessMSG.successMSG);
				break;
			default : responseData.failInfo(ErrorMSG.notKnowUser);
				
			}
		}catch(Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.notKnow);
		}
		
		return toString();
	}
	

	/**
	 * 更改密码
	 * @param userId 用户ID  oldPassword 原密码  newPassword 新密码  _newPassword 确认密码
	 * @return
	 * @throws UserException
	 */
	@ResponseBody
	@RequestMapping(value="/updatePasswordByUserId",produces=EncodingConfig.produces)	
	private String updatePasswordByUserId(String userId,String oldPassword,String newPassword, String _newPassword){
		try{
			if(userId==null||oldPassword==null||newPassword==null){
				responseData.failInfo(ErrorMSG.parameterIsNull);
			}else if(newPassword.endsWith(_newPassword)){
				userService.updateUserPassword(userId, oldPassword, _newPassword);
				responseData.successInfo(SuccessMSG.updateSuccessMSG);
			}else{
				responseData.failInfo(ErrorMSG.passwordNotSameError);
			}
		}catch(UserException e){
			e.printStackTrace();
			responseData.failInfo(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.notKnow);
		}
		return toString();
	}
}
