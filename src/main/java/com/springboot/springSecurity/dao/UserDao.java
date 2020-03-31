package com.springboot.springSecurity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.springSecurity.model.User;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsername(String username);

}
