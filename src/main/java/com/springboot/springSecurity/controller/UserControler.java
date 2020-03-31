package com.springboot.springSecurity.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.springSecurity.model.User;
import com.springboot.springSecurity.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserControler {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@GetMapping(value = "/{id}")
	public User findById(@PathVariable Integer id) {
		return userService.findbyId(id);
	}
	
	@ResponseBody
	@GetMapping()
	public User findByUsername(@PathParam("username") String username) {
		return userService.findByUsername(username);
	}

}
