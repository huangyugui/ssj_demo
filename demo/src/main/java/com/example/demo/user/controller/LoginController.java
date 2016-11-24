package com.example.demo.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@RequestMapping(value = "/page4login", method = { RequestMethod.GET })
	public String page4login() {
		return "/login";
	}
	
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index() {
		return "/index";
	}
	
	@RequestMapping(value = "/anonymous", method = { RequestMethod.GET })
	public String anonymous() {
		return "/anonymous";
	}

}
