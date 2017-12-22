package org.jxau.lctoh.user.basis.controller;


import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.jxau.lctoh.tool.config.Config;
import org.jxau.lctoh.user.admin.domain.Admin;
import org.jxau.lctoh.user.admin.service.AdminService;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.basis.service.UserService;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.customer.service.CustomerService;
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
	
	
	@ResponseBody
	@RequestMapping(value="/login",produces=Config.produces)
	public String login(HttpSession session){
		
		Integer type =0;
		
		User user=new User();
		user.setUserAccount("100001");
		user.setUserPassword("100001");
		
		switch(type){
			case 1:
				try {
					Customer customer=customerService.login(user);
					session.setAttribute(Config.customerSession, customer);
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return Config.notKnowError;
				}
				break;
			case 2:
				try {
					Admin admin=adminService.login(user);
					session.setAttribute(Config.adminSession, admin);
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return Config.notKnowError;
				}
				break;
			case 3:
				try {
					Rider rider=riderService.login(user);
					session.setAttribute(Config.riderSession, rider);
					ServletContext servletContext=session.getServletContext();
					Map riderMap=(Map)servletContext.getAttribute(Config.riderContext);
					riderMap.put(rider.getRiderId(), rider);
					synchronized(this){
						servletContext.setAttribute(Config.riderContext, riderMap);
					}
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return Config.notKnowError;
				}
				break;
			case 4:
				try {
					riderService.login(user);
					//throw new UserException("店家登陆尚未实现");
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return Config.notKnowError;
				}
				break;
			default : return Config.notKnowUserError;
		}
		
		return "1";
	}
	
	
	
	
	@RequestMapping(value="/test",produces=Config.produces)
	public String test(){
		
		userService.login(new User());
		
		return null;
	}
}
