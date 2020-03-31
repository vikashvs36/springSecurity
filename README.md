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
	
	