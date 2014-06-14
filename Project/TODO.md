
Lista TODO:

- dodac kontrolery od obslugi formularzy MVC(POST)

Ogarnac temat z date: jest w modelu, jak ustawic aby mozliwy null,
zglaszany blad "date is null violation constraint".

Jak ustawiac walidatory dla pol o typie Integer (dzialaja tylko dla String)?

- Zaimplementowac REST API w kontrolerach

Poprawic/dodac testy dla PUT/DELETE/POST.
Nie dziala wolanie serwisu ExpenseService, dostaje blad:


WARN : org.springframework.web.client.RestTemplate - DELETE request for "http://localhost:8080/spring/rest_expenses/json/163840" r
esulted in 500 (Internal Server Error); invoking error handler


links:
http://learningviacode.blogspot.com/2013/06/using-springs-resttemplate-class.html
http://learningviacode.blogspot.in/2013/05/rest-example-with-spring.html

- dodac security

- Doprowadzic do dzialania AOP.
Application.java to testuje.
Dodano konfiguracje z aop-context.xml.
Wolane sa funkcje, lecz Monitor.java nie przechwytuje zdarzen na wykonywanych metodach.

INFO: dziala dla xml, problem tylko z anotacjami

- JPA

Doprowadzic do dzialania JPA, Test_JPA rzuca bledem

- Dodac klienta AngularJS
(zobaczyc przyklad Tomka S.)

- JDBC
Jak tworzyc(create) i usuwac(drop) tabele z wykorzystaniem SimpleJdbcTemplate(). 

- Przeniesc do plikow wlasciwosci konfiguracje DataSource oraz podpinanie DAO dla jdbc/simplejdbc/hibernate.


