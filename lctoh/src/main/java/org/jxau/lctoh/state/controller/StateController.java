package org.jxau.lctoh.state.controller;



import org.jxau.lctoh.state.service.StateService;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/StateController")
public class StateController extends BaseController{
	
	@Autowired
	private StateService stateService;
	
	
}
