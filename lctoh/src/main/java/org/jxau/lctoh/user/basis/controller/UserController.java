package org.jxau.lctoh.user.basis.controller;


import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.ConversationMSG;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.tool.config.EncodingConfig;
import org.jxau.lctoh.tool.domain.ResponseData;
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

@Controller
@RequestMapping("/UserController")
public class UserController {
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
	@Autowired
	private ResponseData responseData;
	/**
	 * 账号密码登陆
	 * @param user 登陆账号密码
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login",produces=EncodingConfig.produces)
	public String login(User user,Integer type,HttpSession session){
		if(type==null){
			return ErrorMSG.notKnowUserError;
		}
		//验证信息
		if(user==null){
			return ErrorMSG.accountAndPasswordIsNullError;
		}
		if(user.getUserAccount()==null){
			return ErrorMSG.accountIsNullError;
		}
		if(user.getUserPassword()==null){
			return ErrorMSG.passwordIsNullError;
		}
		type=0;
		user.setUserAccount("100001");
		user.setUserPassword("100001");
		//判断登陆的角色
		switch(type){
			case 1:
				try {
					Customer customer=customerService.login(user);
					session.setAttribute(ConversationMSG.customerSession, customer);
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return ErrorMSG.notKnowError;
				}
				break;
			case 2:
				try {
					Admin admin=adminService.login(user);
					session.setAttribute(ConversationMSG.adminSession, admin);
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return ErrorMSG.notKnowError;
				}
				break;
			case 3:
				try {
					Rider rider=riderService.login(user);
					session.setAttribute(ConversationMSG.riderSession, rider);
					ServletContext servletContext=session.getServletContext();
					Map riderMap=(Map)servletContext.getAttribute(ConversationMSG.riderContext);
					riderMap.put(rider.getRiderId(), rider);
					synchronized(this){
						servletContext.setAttribute(ConversationMSG.riderContext, riderMap);
					}
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return ErrorMSG.notKnowError;
				}
				break;
			case 4:
				try {
					Restaurant restaurant=restaurantService.login(user);
					session.setAttribute(ConversationMSG.restaurantSession, restaurant);
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return ErrorMSG.notKnowError;
				}
				break;
			default : return ErrorMSG.notKnowUserError;
		}
		
		return ErrorMSG.success;
	}
	
	
	/**
	 * 根据 账号 验证码 登陆
	 * @param userAccount
	 * @param code
	 * @param type
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/loginByCode",produces=EncodingConfig.produces)
	public String loginByCode(String userAccount,String code,Integer type,HttpSession session){
		
		if(type==null){
			return ErrorMSG.notKnowUserError;
		}
		//验证信息
		
		if(userAccount==null){
			return ErrorMSG.accountIsNullError;
		}
		if(code==null){
			return ErrorMSG.codeError;
		}
		type=0;
		userAccount="100001";
		
		switch(type){
			case 1:
				try {
					Customer customer=customerService.loginByCode(userAccount,code);
					session.setAttribute(ConversationMSG.customerSession, customer);
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return ErrorMSG.notKnowError;
				}
				break;
			case 2:
				try {
					Admin admin=adminService.loginByCode(userAccount,code);
					session.setAttribute(ConversationMSG.adminSession, admin);
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return ErrorMSG.notKnowError;
				}
				break;
			case 3:
				try {
					Rider rider=riderService.loginByCode(userAccount,code);
					session.setAttribute(ConversationMSG.riderSession, rider);
					ServletContext servletContext=session.getServletContext();
					Map riderMap=(Map)servletContext.getAttribute(ConversationMSG.riderContext);
					riderMap.put(rider.getRiderId(), rider);
					synchronized(this){
						servletContext.setAttribute(ConversationMSG.riderContext, riderMap);
					}
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return ErrorMSG.notKnowError;
				}
				break;
			case 4:
				try {
					Restaurant restaurant=restaurantService.loginByCode(userAccount,code);
					session.setAttribute(ConversationMSG.restaurantSession, restaurant);
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return ErrorMSG.notKnowError;
				}
				break;
			default : return ErrorMSG.notKnowUserError;
		}
		return ErrorMSG.success;
	}
	
	
	/**
	 * 根据 账号 验证码 登陆
	 * @param userAccount
	 * @param code
	 * @param type
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/verificationByCode",produces=EncodingConfig.produces)
	public String verificationByCode(String userEmail,String code,HttpSession session){
		//验证信息
		if(userEmail==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.emailIsNullError));
		}
		if(code==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.codeIsNullError));
		}
		/*
		 * 邮箱格式验证
		if(userEmail==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.emailIsNullError));
		}
		*/
		try {
			User user=userService.findByEmailAndCode(userEmail,code);
			session.setAttribute(ConversationMSG.userSession, user);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			return Tools.gson.toJson(responseData.failInfo(e.getMessage()));
		}catch (Exception e) {
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowError));
		}
		return Tools.gson.toJson(responseData.successInfo(null));
	}
	
	
	
	/**
	 * 修改密码
	 * @param password
	 * @param _password
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updatePassword",produces=EncodingConfig.produces)
	public String updatePassword(String password,String _password,HttpSession session){
		//验证信息
		if(password==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.passwordIsNullError));
		}
		if(!password.equals(_password)){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.passwordNotSameError));
		}
		User user=(User)session.getAttribute(ConversationMSG.userSession);
		if(user==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowError));
		}
		user.setUserPassword(password);
		try {
			return Tools.gson.toJson(responseData.successInfo(userService.updateUser(user)));
		} catch (UserException e) {
			return Tools.gson.toJson(responseData.failInfo(e.getMessage()));
		} catch (Exception e) {
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowError));
		}
	}
	
	/**
	 * 根据账号获取验证码
	 * */
	@ResponseBody
	@RequestMapping(value="/getCodeByUserAccount",produces=EncodingConfig.produces)
	public String getCodeByUserAccount(String userAccount){
		if(userAccount==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.accountIsNullError));
		}
		try {
			return Tools.gson.toJson(responseData.successInfo(verificationCodeService.setCode(userAccount)));
		} catch (VerificationCodeException e) {
			return Tools.gson.toJson(responseData.failInfo(e.getMessage()));
		}catch(Exception e){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowError));
		}
	}
	
	/**
	 * 根据账号获取验证码
	 * */
	@ResponseBody
	@RequestMapping(value="/getCodeByUserEmail",produces=EncodingConfig.produces)
	public String getCodeByUserEmail(String userEmail){
		if(userEmail==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.emailIsNullError));
		}
		/*
		 * 邮箱格式验证
		if(userEmail==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.emailError));
		}
		*/
		try {
			return Tools.gson.toJson(responseData.successInfo(verificationCodeService.setCodeByUserEmail(userEmail)));
		} catch (VerificationCodeException e) {
			return Tools.gson.toJson(responseData.failInfo(e.getMessage()));
		}catch(Exception e){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowError));
		}
	}
	
	
	
	/**
	 * 注册
	 * */
	@ResponseBody
	@RequestMapping(value="/register",produces=EncodingConfig.produces)
	public String register(User user,String _userPassword){
		//验证信息
		if(user==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowError));
		}
		if(user.getUserPassword()==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.passwordIsNullError));
		}
		if(user.getUserSex()==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.sexIsNullError));
		}
		if(user.getUserPhone()==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.phoneError));
		}
		if(user.getUserEmail()==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.emailIsNullError));
		}
		/*
		 * 邮箱格式判断
		if(user.getUserEmail()==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.emailError));
		}
		*/
		if(!user.getUserPassword().equals(_userPassword)){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.passwordNotSameError));
		}
		
		try{
			user=customerService.register(user);
		}catch(UserException e){
			return Tools.gson.toJson(responseData.failInfo(e.getMessage()));
		}catch(Exception e){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowError));
		}
		return Tools.gson.toJson(responseData.successInfo(user));
	}
	
}
