# GreenStitchAssignment

### 1. Clone the repository
``` https://github.com/Sanchit2001/GreenStitchAssignment ```
### 2. Run using maven command
``` mvn spring-boot:run ```

### The process will start on localhost:8080

The project consists of signin api, signup api and spring security using JWT tokens

Major Dependencies used:
1. spring-web
2. jjwt
3. h2 database
4. spring-data-jpa
5. lombok
6. spring-security
7. http-security

# H2 Database Configuration

The project uses the H2 in-memory database by default.

The application is configured to use the H2 database. The configuration can be done in `application.properties` file:

```
# Server Port Configuration
server.port=8081

# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sanchit
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

```
