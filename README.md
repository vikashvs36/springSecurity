# Spring Security in Spring Boot

## Project Setup

>### Added Dependency 

 * Web
 * JPA
 * H2
 * DevTool
 
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

 