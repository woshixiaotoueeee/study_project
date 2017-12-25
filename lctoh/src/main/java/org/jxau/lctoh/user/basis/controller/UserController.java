package org.jxau.lctoh.user.basis.controller;


import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.jxau.lctoh.tool.config.ConversationMSG;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.tool.config.EncodingConfig;
import org.jxau.lctoh.user.admin.domain.Admin;
import org.jxau.lctoh.user.admin.service.AdminService;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.basis.service.UserService;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.customer.service.CustomerService;
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
	
	@ResponseBody
	@RequestMapping(value="/login",produces=EncodingConfig.produces)
	public String login(User user,HttpSession session){
		//验证信息
		if(user==null){
			
		}
		
		
		Integer type =0;
		user.setUserAccount("100001");
		user.setUserPassword("100001");
		
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
					restaurantService.login(user);
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
	
	
	
	
	@RequestMapping(value="/test",produces=EncodingConfig.produces)
	public String test(){
		
		userService.login(new User());
		
		return null;
	}
}
