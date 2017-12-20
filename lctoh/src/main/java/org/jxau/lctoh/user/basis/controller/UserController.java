package org.jxau.lctoh.user.basis.controller;


import org.jxau.lctoh.user.admin.service.AdminService;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.basis.service.UserService;
import org.jxau.lctoh.user.customer.service.CustomerService;
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
	
	
	@ResponseBody
	@RequestMapping(value="/login",produces="text/html;charset=utf-8")
	public String login(){
		User user=new User();
		user.setUserAccount("100001");
		user.setUserPassword("11111");
		try {
			customerService.login(user);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			adminService.login(user);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return "index";
	}
	
	
	
	
	@RequestMapping(value="/test",produces="text/html;charset=utf-8")
	public String test(){
		
		userService.login(new User());
		
		return null;
	}
}
