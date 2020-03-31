package com.springboot.springSecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			.withUser("user").password("{noop}pass").roles("USER")
			.and()
			.withUser("admin").password("{noop}pass").roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().disable()
			.authorizeRequests().antMatchers("/api/admin/**").hasAnyRole("ADMIN")
			.and().authorizeRequests().antMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
			.and().formLogin();
	}
}
