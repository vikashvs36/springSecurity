package com.springboot.springSecurity.config;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		// User Simple plane text password.
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		// Use BCrypt Password Encoder
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * http.cors().disable()
		 * .authorizeRequests().antMatchers("/api/admin/**").hasAnyRole("ADMIN") .and()
		 * .authorizeRequests().antMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
		 * .and().formLogin();
		 */
		
		http.cors().disable()
			.authorizeRequests()
			.antMatchers("/home").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/dashboard").permitAll()
			.and()
			.logout()
			.deleteCookies("remove").invalidateHttpSession(false)
            .logoutUrl("/logout") // Default logoutUrl is /logout, you can add custom logout Url.
            .logoutSuccessUrl("/logout-success").permitAll();
		
	} 
	 
	
	/*
	 * @Bean
	 * 
	 * @Override public UserDetailsService userDetailsService() {
	 * 
	 * Create single User // UserDetails userDetails =
	 * User.withDefaultPasswordEncoder().username("vikash").password("pass").roles(
	 * "USER").build();
	 * 
	 * // Create multiple User List<UserDetails> userDetails = new ArrayList<>();
	 * userDetails.add(User.withDefaultPasswordEncoder().username("user").password(
	 * "pass").roles("USER").build());
	 * userDetails.add(User.withDefaultPasswordEncoder().username("admin").password(
	 * "pass").roles("ADMIN").build());
	 * 
	 * return new InMemoryUserDetailsManager(userDetails); }
	 */

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * auth.inMemoryAuthentication()
	 * .withUser("user").password("{noop}pass").roles("USER") .and()
	 * .withUser("admin").password("{noop}pass").roles("ADMIN"); }
	 */
}
