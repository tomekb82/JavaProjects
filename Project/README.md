
Spring3 Web Project 
===================

Spring MVC framework:
---------------------

- MVC controllers
- dispatcher servlet: appServlet
- support for jsp pages
- form controllers, form validation

Spring DI and AOP:
-------------------
- annotation based DI (*@Autowired()*)
- AOP: around aspect only for efficiency tests 

Spring transactions:
--------------------
- declarative transactions support
- annotation based 

Spring JDBC, ORM:
------------------

- database types: 
	- JDBC(*JdbcTemplate,SimpleJdbcTemplate*), 
	- Hibernate, 
	- JPA 
- one service for different DAOs
- database properties configuration in external file
- initializing database with default values: .sql files
- Hibernate: 
	- relations: *@OneToOne, @OneToMany, @ManyToMany*
	- unit tests for all relations

Spring security:
-----------------

- configuration in Spring context
- creating custom login page
- no support for securing methods

REST API:
---------

- implementing Spring REST API
- preparing clients for Spring REST tests (using *RestTemplate*)
- implementing JAX-RS (*Jersey*) + test client
	- dispatcher servlet: jersey-serlvet
	- mapping: /jaxrs/*

Remote services:
----------------

- support for: 
	- RMI, 
	- HttpInvoker:
		- dispatcher servlet: httpInvoker 
		- mapping: /services/*
	- JAX-WS:
		- dispatcher servlet: jaxws-servlet
		- mapping: /jaxws-spring
- preparing clients for tests

Frontend:
---------

- view types:
	-  jsp: 
		- dispatcher servlet: Faces Servlet
		- mapping: /jsp/*
		- using Tiles template
	-  jsf: 
		- dispatcher servlet: Faces Servlet
		- mapping: *.jsf, *.xhtml
		- forms, support for primafaces implementation
	-  AngularJS views 
		- dispatcher servlet: action
		- mapping: /action/*
		- collecting data from REST API, 
		- I18N support (*pl,en()*), 
		- boostrap support

Utils:
------

- comparator vs comparable

Tests:
------

- unit tests: Junit,Mockito
- tests for mvc controllers, services
- testing clients for REST API, RMI, JAX-WS
- more info about URLs to test in INFO.md file

Build & deploy:
---------------

- using Maven to build all sources
- servlet container: tomcat7
	

