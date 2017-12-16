package org.jxau.lctoh.user.customer.controller;

import org.jxau.lctoh.user.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/CustomerController")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	
	
	@RequestMapping(value="/test",produces="text/html;charset=utf-8")
	public void test(){
		System.out.println(customerService.findCustomerByUserId("123456"));
	}
	
	
	
}
