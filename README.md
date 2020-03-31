# Spring Security in Spring Boot

## Project Setup

>### Modules used for Application 

 * Java 8
 * Spring Boot
 * H2 database
 * Maven
 * Git

>### Added Dependency 

 * Web
 * JPA
 * H2
 * DevTool
 * Spring Security 
>### application.properties

	# Configure H2 database
	spring.datasource.url=jdbc:h2:mem:testdb
	spring.datasource.driverClassName=org.h2.Driver
	spring.datasource.username=sa
	spring.datasource.password=
	spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

**Note :** We can get the records on /h2-console to manage the database. 

>### Created API

There are created three API. First two is for User role and the last and third one is created for Admin role.

1. Find the User by UserId - USER_ROLE
	
	> http://localhost:8080/api/user/1
2. Find the User By Username - USER_ROLE
	
	> http://localhost:8080/api/user?username=vikash
3. Find All User details. - ADMIN_ROLE

	> http://localhost:8080/api/admin/user
	
**Note :** data.sql file is created for insert user details in user table at initial time.

>### Spring Security Dependency

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
	</dependency>
	
As you will add the Spring-boot-starter-security, the whole application will be secure that means, you can't access any page or Url without given Username and Password.
The Spring security will give the default login page with default url **/login**. e.g - [http://localhost:8080/login](#http://localhost:8080/login) 

![](images/loginPage.PNG)

The Username will be by default **user** and we can get the password from console, given like that : 

	  Using generated security password: 937e8fe0-cff5-4b1c-bc00-59c3c44d6c2a
	  
You can customize the **Username** and **password** from **application.properties** which is given below :

	spring.security.user.name= vikash
	spring.security.user.password= pass


>### Security in a Spring MVC Application

We can either implements the interface called WebSecurityConfigurer or extend the more convenient class called WebSecurityConfigurerAdapter. The advantage of extending the adapter class is that we can configure Web security by overriding only those parts that we are interested in; others can remain their default form.

	// The annotation @EnableWebSecurity enables Web security; otherwise, it remains disabled by default.
	@Configuration
	@EnableWebSecurity
	public class SecurityConfig extends WebSecurityConfigurerAdapter {
	}

**There are three different type of the configure method that can be override to configure and secure the application :**

* void configure( AuthenticationManagerBuilder auth): To configure user details services
* void configure( HttpSecurity http): To configure how requests are secured by interceptors
* void configure( WebSecurity web): To configure Spring Security's filter chain

> **configure( AuthenticationManagerBuilder auth)**

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.inMemoryAuthentication()
			.withUser("admin").password("{noop}pass").roles("ADMIN");
			.and()
			.withUser("user").password("{noop}pass").roles("USER")
	}
	
> **configure( HttpSecurity http)**

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().disable()
			.authorizeRequests().antMatchers("/api/admin/**").hasAnyRole("ADMIN")
			.and().authorizeRequests().antMatchers("/api/user/**").hasAnyRole("USER")
			.and().formLogin();
	}
	
> **configure( WebSecurity web)**

**Note :** The default filter chain is fine for most needs.

>### UserDetailsService userDetailsService(); 
 
* Core interface which loads user-specific data. 
* It is used throughout the framework as a user DAO and is the strategy used by the DaoAuthenticationProvider.
* The interface requires only one read-only method, which simplifies support for new data-access strategies.
* It can be use **UserDetailsService userDetailsService()** in place of **void configure(AuthenticationManagerBuilder auth)**

> Way to use UserDetailsService interface

	@Bean
	@Override
	public UserDetailsService userDetailsService() {

		/* Create single User */
		// UserDetails userDetails = User.withDefaultPasswordEncoder().username("vikash").password("pass").roles("USER").build();

		// Create multiple User
		List<UserDetails> userDetails = new ArrayList<>();
		userDetails.add(User.withDefaultPasswordEncoder().username("user").password("pass").roles("USER").build());
		userDetails.add(User.withDefaultPasswordEncoder().username("admin").password("pass").roles("ADMIN").build());

		return new InMemoryUserDetailsManager(userDetails);
	}	 

