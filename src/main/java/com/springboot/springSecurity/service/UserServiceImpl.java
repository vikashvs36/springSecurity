package com.springboot.springSecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.springSecurity.dao.UserDao;
import com.springboot.springSecurity.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findbyId(Integer id) {
		Optional<User> optional = userDao.findById(id); 
		return optional.isPresent() ? optional.get() : new User();
	}

	@Override
	public User findByUsername(String username) {
		Optional<User> optional = userDao.findByUsername(username);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User save(User user) {
		if(!StringUtils.isEmpty(user.getPassword())) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		}
		return userDao.save(user);
	}
	
	
	
}
