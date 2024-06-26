## Spring Boot 3.X basic project with Spring Security & JWT & Flyway

### controller filter

- JDK17 required.
- Lombok imported.
- Slf4j imported.
---
* add jpa, database, flyway dependency to pom.
  * jpa
  * postgreSQL
  * flyway


* Flyway
  * naming convention for migration script files are likes < Prefix >< Version >__< Description >.sql
  * e.g. V1__initial.sql
  * config could be configured under application.yml


* add spring-boot-starter-security dependency to pom.
  * add security config (SecurityConfig.java)
    * let api path /public/** and post method permitAll.
    * else authenticated required.

* filter
  we can use ordered filters.
  ![](https://github.com/benjamin-kuo/images/blob/main/turtorial/SpringFilter.png?raw=true)

Spring Security with JWT

1. SecurityConfig
2. JwtTokenService   
3. implements UserDetailsService   
4. implements UserDetails
5. JwtAuthFilter


- JWT
  - iss（Issuer）：簽發者。
  - sub（Subject）：所面向的使用者或實體。
  - aud（Audience）：受眾，即預期接收JWT的一方。
  - exp（Expiration Time）：過期時間。在這之後，JWT將不再有效。
  - nbf（Not Before）：生效時間，即在此時間之前JWT將不被接受。
  - iat（Issued At）：簽發時間。
  - jti（JWT ID）：唯一標識符。
