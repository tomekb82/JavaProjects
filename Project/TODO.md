
Lista TODO:

====================================================================================

- Dodac kontrolery od obslugi formularzy MVC(POST)

Ogarnac temat z date: jest w modelu, jak ustawic aby mozliwy null,
zglaszany blad "date is null violation constraint".

Jak ustawiac walidatory dla pol o typie Integer (dzialaja tylko dla String)?

====================================================================================

- Zaimplementowac REST API w kontrolerach

Poprawic/dodac testy dla PUT.
Sama funkcja update w serwisie dziala prawidlowo.
Nie dziala PUT, dostaje blad:

WARN : org.springframework.web.client.RestTemplate - DELETE request for "http://localhost:8080/spring/rest_expenses/json/393216" r
esulted in 500 (Internal Server Error); invoking error handler
Exception in thread "main" org.springframework.web.client.HttpServerErrorException: 500 Internal Server Erro

====================================================================================

- Doprowadzic do dzialania AOP.
Application.java to testuje.
Dodano konfiguracje z aop-context.xml.
Wolane sa funkcje, lecz Monitor.java nie przechwytuje zdarzen na wykonywanych metodach.

INFO: dziala dla xml, problem tylko z anotacjami

====================================================================================

- Dodac zdalne uslugi: HttpInvoker + klient do testow

Doprowadzic do dzialania HttpInvoker, bo jest problem z URLami:
Exception in thread "main" org.springframework.remoting.RemoteAccessException: Could not access HTTP invoker remote service at [ht
tp://localhost:8080/httpinvoker/Calculation.http]; nested exception is java.io.IOException: Did not receive successful HTTP respon
se: status code = 404, status message = [Not Found]

Projekt httpInvoker z http://www.javatpoint.com/spring-remoting-by-http-invoker-example.

====================================================================================

- JPA

Doprowadzic do dzialania JPA, Test_JPA rzuca bledem

====================================================================================

- Dodac klienta AngularJS
(zobaczyc przyklad Tomka S.)

====================================================================================

- JDBC
Jak tworzyc(create) i usuwac(drop) tabele z wykorzystaniem SimpleJdbcTemplate(). 

====================================================================================

- Przeniesc do plikow wlasciwosci konfiguracje DataSource oraz podpinanie DAO dla jdbc/simplejdbc/hibernate.


