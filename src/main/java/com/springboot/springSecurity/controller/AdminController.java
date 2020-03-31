package com.springboot.springSecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springSecurity.model.User;
import com.springboot.springSecurity.service.UserService;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/user") 
	public List<User> findAll() {
		return userService.findAll();
	}
}
