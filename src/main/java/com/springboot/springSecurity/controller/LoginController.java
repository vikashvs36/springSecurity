package com.springboot.springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.springSecurity.model.User;
import com.springboot.springSecurity.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("home");
		return modelAndView;
	}
	
	@GetMapping(path = "/register")
	public ModelAndView register() {
		System.out.println("com.springboot.springSecurity.controller.LoginController.register() : ");
		ModelAndView modelAndView = new ModelAndView("register");
		return modelAndView;
	}
	
	@PostMapping(path = "/register")
	public ModelAndView saveUser(User user) {
		System.out.println("com.springboot.springSecurity.controller.LoginController.saveUser(User)");
		userService.save(user);
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("message", "You have successfully register, Please signin.");
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
