package org.jxau.lctoh.state.controller;

import java.util.Date;

import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.state.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/StateController")
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	@ResponseBody
	@RequestMapping(value="/statetest",produces="text/html;charset=utf-8")
	public String test(){
		State state=stateService.findStateByStateId(10000000);
		System.out.println(state);
	//	state.setStateUpdateTime(new Date());
		//System.out.println(stateService.updateState(state));
		return "index";
	}
}
