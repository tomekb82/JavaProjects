

Project packages
================

Model
------

- package: **eu.tbelina.spring.model**
	- User.java
	- Income.java
	- Contact.java
	- Group.java
	- UserRest.java
	- Expense.java
	- UserForm.java
	- Address.java

Spring controllers
------------------

- package: **eu.tbelina.spring.controller**
	- ExpenseController.java
	- LoginController.java
	- HomeController.java
	- ExpenseRestController.java
	- UserRestController.java

DAO
---
- package: **eu.tbelina.spring.dao**
	- IExpenseDAO.java
		- impl.hibernate.HibernateExpenseDAO.java
		- impl.jdbc.JdbcExpenseDAO.java
		- impl.jdbc.SimpleJdbcExpenseDAO.java
		- impl.jpa.JpaExpenseDAO.java
	- IUserDAO.java
		- impl.hibernate.UserDAO.java

Services
--------

- package: eu.tbelina.spring.service**
	- IExpenseService.java
		- impl.ExpenseService.java
		- impl.UserService.java
	- IUserService.java

Spring AOP:
-----------
- package: **eu.tbelina.spring.aop**
	- Monitor.java

Spring security
---------------
- package **eu.tbelina.spring.security**
	- SecurityLoginController.java

JSF Beans
---------
- package: **eu.tbelina.spring.bean**
	- ExpenseManagedBean.java
	- UserManagedBean.java

Comparators
-----------

- packege: **eu.tbelina.spring.comparator**
	- TestComparing.java
	- ExpenseComparator.java
	- ExpenseComparable.java

Jax-RS
-------
- package: **eu.tbelina.spring.jax_rs**
	- JaxRSClient.java
	- JaxRsExpenseService.java

JAX-WS
------
- package: **eu.tbelina.spring.jax_ws**
	- MyWebService.java
	- IMyWebService.java
	- Client.java
	- MyBObjectImpl.java
	- MyBObject.java

Spring REST-API
---------------
- package: **eu.tbelina.rest.client**
	- UserRestClient.java
	- ExpenseRestClient.java

HttpInvoker
-----------
- package: **eu.tbelina.spring.httpInvoker**
	- Client.java
	- Calculation.java
	- CalculationImpl.java

RMI
---
- package: **eu.tbelina.spring.rmi**
	- RmiExpenseClient.java
	- Host.java
	- Client.java
	- CalculationImpl.java
	- Calculation.java

AngularJS
---------
- package: **eu.tbelina.angularjs**
	- service: IPersonService.java
		- impl.PersonService.java
	- model:
		- Person.java
		- ResponseMessage.java
	- controller:
		- PersonController.java

Tests
-----
- package: **eu.tbelina.spring.test**
	- hibernate tests: TestHibernate.java
	- controller tests: ExpenseControllerTest.java
	- service tests: ExpenseServiceTest.java







