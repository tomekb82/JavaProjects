
Spring3 Web Project contains:

- Spring MVC framework (jsp pages, form validation)
- Spring DI and AOP (around aspect for efficiency tests only)
- declarative transactions (using annotations) 
- databases: JDBC(JdbcTemplate,SimpleJdbcTemplate), Hibernate, JPA -> one service for different DAOs
- support for jsp, jsf/primafaces and AngularJS views (3 different dispatcher servlets)
- unit tests (Junit,Mockito,sql files) for: mvc controllers, services
- Jsp views: Tiles template
- JSF: forms, primafaces support
- AngularJS: collecting data from REST API, I18N support (pl,en), boostrap support
- Spring Security support (configuration in Spring context, custom login page, no support for methods security)
- spring REST API + rest client for tests(RestTemplate)
- build tool: Maven
- extracting configuration to an external file (database properties)
- Remote services: RMI/HttpInvoker
