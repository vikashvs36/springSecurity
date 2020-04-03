package com.springboot.springSecurity.service;

import java.util.List;

import com.springboot.springSecurity.model.User;

public interface UserService {

	User findbyId(Integer id);
	
	User findByUsername(String username);

	List<User> findAll();
	
	User save(User user);

}
