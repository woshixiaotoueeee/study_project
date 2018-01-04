package org.jxau.lctoh.user.customer.controller;

import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.user.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/CustomerController")
public class CustomerController  extends BaseController{
	@Autowired
	private CustomerService customerService;
	
	
	
	
}
