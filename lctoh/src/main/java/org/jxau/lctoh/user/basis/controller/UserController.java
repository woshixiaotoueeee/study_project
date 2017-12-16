package org.jxau.lctoh.user.basis.controller;


import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/UserController")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/test",produces="text/html;charset=utf-8")
	public String test(){
		
		userService.login(new User());
		
		return null;
	}
}
