
Useful informations about testing URLs:
=======================================

Spring MVC
----------

- description: Spring MVC views
- url: 
	- http://localhost:8080/spring/jsp/expenses/
	- http://localhost:8080/spring/jsp/expenses/sss
	- http://localhost:8080/spring/jsp/expenses/expense?name=aaa
	- http://localhost:8080/spring/jsp/expenses?new
	- http://localhost:8080/spring/jsp/login2/  (*logging form*)

Login Page (*only for Spring MVC pages*)
--------------------------------------

- description: Spring Security login page.
- url: 
	- http://localhost:8080/spring/jsp/login

- info: valid credentials *filip/filip*

MVC Rest API
------------

- description: Get Spring REST resources
- url: 
	- http://localhost:8080/spring/jsp/rest_expenses/json/
	- http://localhost:8080/spring/jsp/rest_expenses/json/sss

REST JAX-RS (jersey)
--------------------

- description: Get JAX-RS REST resources
- url: 
	- http://localhost:8080/spring/jaxrs/hello/sss

REST JAX-RS(Jersey)
-------------------

- description: REST Jersey convention.
- url: 
	- http://localhost:8080/spring/jaxrs/hello/ss
	- http://localhost:8080/spring/jaxrs/exp
	- http://localhost:8080/spring/jaxrs/exp/sss

JSF/Primafaces pages
--------------------

- description: JSF pages
- url: 
	- http://localhost:8080/spring/jsf_pages/index.xhtml
	- http://localhost:8080/spring/jsf_pages/addExpense.xhtml
	- http://localhost:8080/spring/jsf_pages/showExpenses.xhtml


AngularJS pages
---------------

- description: AngularJS client, collecting data using REST API from server side.
- url:
	- http://localhost:8080/spring/ <==> http://localhost:8080/spring/main.html#/person 

Spring REST API - tests
-----------------------

- test: 
	- deploy project first.
	- run ExpenseRestClient (REST client)
	- run UserRestClient (other REST client) 

Spring RMI
----------

- test1: 
	- run Host.java (server RMI)
	- run Client.java (client RMI)

- test2: 
	- deploy project.
	- run RmiExpenseClient (REST client)

JAX-WS
------

- test: 	
	- deploy project.
	- run eu.tbelina.spring.jax_ws.Client.


