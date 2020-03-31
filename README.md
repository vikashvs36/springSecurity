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










