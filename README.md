# Sample TODO Application
* Java 8
* Maven
* Spring Boot
* Spring Data JPA - Hibernate
* Spring Security
* Thymeleaf
* JUnit

## Usage

There are multiple profiles, the default profile uses hsql db.

```sh
mvn spring-boot:run
```

### HSQLDB Profile 
	it is same as default. Uses jdbc:hsqldb:mem:testdb

```sh
mvn spring-boot:run -Dspring.profiles.active=hsql
```

### MYSQL Profile
```sh
mvn spring-boot:run -Dspring.profiles.active=mysql -DJDBC_DATABASE_USERNAME=your_username -DJDBC_DATABASE_PASSWORD=your_password -DJDBC_DATABASE_URL=connection_url
```
>JDBC_DATABASE_URL parameter is optional. Default connection url is :
```sh
jdbc:mysql://localhost:3306/tododb?useUnicode=yes&characterEncoding=UTF-8
```
>MYSQL profile uses same mysql parameters using in Heroku

### Preview
	http://localhost:8082