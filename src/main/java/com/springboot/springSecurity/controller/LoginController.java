package com.springboot.springSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@GetMapping(path = "/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("home");
		return modelAndView;
	}
	
	@GetMapping(path = "/login")
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		
		ModelAndView modelAndView = new ModelAndView("login");
		
		if(!StringUtils.isEmpty(error)) {
			modelAndView.addObject("error", "Invalid Credentials provided.");
		}
		
		if(!StringUtils.isEmpty(logout)) {
			modelAndView.addObject("message", "Logout Sucessfully.");
		}
		
		return modelAndView;
	}
	
	@GetMapping(path = "/dashboard")
	public ModelAndView dashboard() {
		ModelAndView modelAndView = new ModelAndView("dashboard");
		return modelAndView;
	}
	
	@GetMapping(path = "/logout-success")
	public String logoutSuccess() {
		System.out.println("logout-success");
		return "redirect:/home";
	}
}
