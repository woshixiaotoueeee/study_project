package org.jxau.lctoh.user.basis.controller;


import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

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
	@RequestMapping(value="/login",produces="text/html;charset=utf-8")
	public String login(HttpSession session){
		
		Integer type =0;
		
		User user=new User();
		user.setUserAccount("100001");
		user.setUserPassword("100001");
		
		switch(type){
			case 1:
				try {
					Customer customer=customerService.login(user);
					session.setAttribute("customer", customer);
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return "未知错误，请联系管理员";
				}
				break;
			case 2:
				try {
					Admin admin=adminService.login(user);
					session.setAttribute("admin", admin);
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return "未知错误，请联系管理员";
				}
				break;
			case 3:
				try {
					Rider rider=riderService.login(user);
					session.setAttribute("rider", rider);
					ServletContext servletContext=session.getServletContext();
					Map riderMap=(Map)servletContext.getAttribute("riderMap");
					riderMap.put(rider.getRiderId(), rider);
					synchronized(this){
						servletContext.setAttribute("riderMap", riderMap);
					}
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return "未知错误，请联系管理员";
				}
				break;
			case 4:
				try {
					riderService.login(user);
					//throw new UserException("店家登陆尚未实现");
				} catch (UserException e) {
					return e.getMessage();
				} catch (Exception e) {
					return "未知错误，请联系管理员";
				}
				break;
			default : return "身份不明";
		}
		
		return "1";
	}
	
	
	
	
	@RequestMapping(value="/test",produces="text/html;charset=utf-8")
	public String test(){
		
		userService.login(new User());
		
		return null;
	}
}
