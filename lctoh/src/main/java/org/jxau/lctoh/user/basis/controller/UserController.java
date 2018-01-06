package org.jxau.lctoh.user.basis.controller;


import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.ConversationMSG;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.tool.config.EncodingConfig;
import org.jxau.lctoh.tool.config.SuccessMSG;
import org.jxau.lctoh.tool.domain.ResponseData;
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
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login",produces=EncodingConfig.produces)
	public String login(User user,Integer type,HttpSession session){
		if(type==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowUserError));
		}
		//验证信息
		if(user==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.accountAndPasswordIsNullError));
		}
		if(user.getUserAccount()==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.accountIsNullError));
		}
		if(user.getUserPassword()==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.passwordIsNullError));
		}
		
		//判断登陆的角色
		switch(type){
			case 1:
				try {
					Customer customer=customerService.login(user);
					session.setAttribute(ConversationMSG.customerSession, customer);
					session.setAttribute(ConversationMSG.cartSession, new Cart());
					responseData.successInfo(SuccessMSG.customerSuccessUrl);
				} catch (UserException e) {
					responseData.failInfo(e.getMessage());
				} catch (Exception e) {
					responseData.failInfo(ErrorMSG.notKnowError);
				}
				break;
			case 2:
				try {
					Admin admin=adminService.login(user);
					session.setAttribute(ConversationMSG.adminSession, admin);
					responseData.successInfo(SuccessMSG.adminSuccessUrl);
				} catch (UserException e) {
					responseData.failInfo(e.getMessage());
				} catch (Exception e) {
					responseData.failInfo(ErrorMSG.notKnowError);
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
					responseData.successInfo(SuccessMSG.riderSuccessUrl);
				}  catch (UserException e) {
					responseData.failInfo(e.getMessage());
				} catch (Exception e) {
					responseData.failInfo(ErrorMSG.notKnowError);
				}
				break;
			case 4:
				try {
					Restaurant restaurant=restaurantService.login(user);
					session.setAttribute(ConversationMSG.restaurantSession, restaurant);
					responseData.successInfo(SuccessMSG.restaurantSuccessUrl);
				}  catch (UserException e) {
					responseData.failInfo(e.getMessage());
				} catch (Exception e) {
					responseData.failInfo(ErrorMSG.notKnowError);
				}
				break;
			default : responseData.failInfo(ErrorMSG.notKnowUserError);
		}
		
		return Tools.gson.toJson(responseData);
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
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.notKnowUserError));
		}
		//验证信息
		if(userAccount==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.accountIsNullError));
		}
		if(code==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.codeError));
		}
		
		switch(type){
			case 1:
				try {
					Customer customer=customerService.loginByCode(userAccount,code);
					session.setAttribute(ConversationMSG.customerSession, customer);
					responseData.successInfo(SuccessMSG.customerSuccessUrl);
				} catch (UserException e) {
					responseData.failInfo(e.getMessage());
				} catch (Exception e) {
					responseData.failInfo(ErrorMSG.notKnowError);
				}
				break;
			case 2:
				try {
					Admin admin=adminService.loginByCode(userAccount,code);
					session.setAttribute(ConversationMSG.adminSession, admin);
					responseData.successInfo(SuccessMSG.adminSuccessUrl);
				} catch (UserException e) {
					responseData.failInfo(e.getMessage());
				} catch (Exception e) {
					responseData.failInfo(ErrorMSG.notKnowError);
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
					responseData.successInfo(SuccessMSG.riderSuccessUrl);
				} catch (UserException e) {
					responseData.failInfo(e.getMessage());
				} catch (Exception e) {
					responseData.failInfo(ErrorMSG.notKnowError);
				}
				break;
			case 4:
				try {
					Restaurant restaurant=restaurantService.loginByCode(userAccount,code);
					session.setAttribute(ConversationMSG.restaurantSession, restaurant);
					responseData.successInfo(SuccessMSG.restaurantSuccessUrl);
				} catch (UserException e) {
					responseData.failInfo(e.getMessage());
				} catch (Exception e) {
					responseData.failInfo(ErrorMSG.notKnowError);
				}
				break;
			default : responseData.failInfo(ErrorMSG.notKnowUserError);
		}
		return Tools.gson.toJson(responseData);
	}
	
	
	/**
	 * 根据 邮箱 验证码  ‘登陆’
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
		if(!userEmail.matches("\\w+@\\w+\\.\\w+")){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.emailError));
			
		}
		try {
			User user=userService.findByEmailAndCode(userEmail,code);
			session.setAttribute(ConversationMSG.userSession, user);
			responseData.successInfo(SuccessMSG.sendEmailSuccess);
		} catch (UserException e) {
			responseData.failInfo(e.getMessage());
		}catch (Exception e) {
			responseData.failInfo(ErrorMSG.notKnowError);
		}
		return Tools.gson.toJson(responseData);
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
			responseData.successInfo(userService.updateUser(user));
		} catch (UserException e) {
			responseData.failInfo(e.getMessage());
		} catch (Exception e) {
			responseData.failInfo(ErrorMSG.notKnowError);
		}
		return Tools.gson.toJson(responseData);
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
			responseData.successInfo(verificationCodeService.setCode(userAccount));
		} catch (VerificationCodeException e) {
			responseData.failInfo(e.getMessage());
		}catch(Exception e){
			responseData.failInfo(ErrorMSG.notKnowError);
		}
		return Tools.gson.toJson(responseData);
	}
	
	/**
	 * 根据邮箱获取验证码
	 * */
	@ResponseBody
	@RequestMapping(value="/getCodeByUserEmail",produces=EncodingConfig.produces)
	public String getCodeByUserEmail(String userEmail){
		if(userEmail==null){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.emailIsNullError));
		}
		if(!userEmail.matches("\\w+@\\w+\\.\\w+")){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.emailError));
			
		}
		try {
			responseData.successInfo(verificationCodeService.setCodeByUserEmail(userEmail));
		} catch (VerificationCodeException e) {
			responseData.failInfo(e.getMessage());
		}catch(Exception e){
			responseData.failInfo(ErrorMSG.notKnowError);
		}
		return Tools.gson.toJson(responseData);
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
		if(!user.getUserEmail().matches("\\w+@\\w+\\.\\w+")){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.emailError));
			
		}
		if(!user.getUserPassword().equals(_userPassword)){
			return Tools.gson.toJson(responseData.failInfo(ErrorMSG.passwordNotSameError));
		}
		
		try{
			user=customerService.register(user);
			responseData.successInfo(user);
		}catch(UserException e){
			responseData.failInfo(e.getMessage());
		}catch(Exception e){
			responseData.failInfo(ErrorMSG.notKnowError);
		}
		return Tools.gson.toJson(responseData);
	}
	
}
