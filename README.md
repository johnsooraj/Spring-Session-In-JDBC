# Spring Boot with Session In JDBC

A tutorial project for microservice system design, Here the session keeps in MySQL Db.

## POM.XML
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.session</groupId>
        <artifactId>spring-session-core</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.session</groupId>
        <artifactId>spring-session-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>


    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

## To save sessions in custom DB, we need to create as SPRING_SESSION

```sql
CREATE SCHEMA IF NOT EXISTS TESTDB;
SET SCHEMA TESTDB;
CREATE TABLE TESTDB.SPRING_SESSION (
PRIMARY_ID CHAR(36) NOT NULL,
SESSION_ID CHAR(36) NOT NULL,
CREATION_TIME BIGINT NOT NULL,
LAST_ACCESS_TIME BIGINT NOT NULL,
MAX_INACTIVE_INTERVAL INT NOT NULL,
EXPIRY_TIME BIGINT NOT NULL,
PRINCIPAL_NAME VARCHAR(100),
CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);
CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON TESTDB.SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON TESTDB.SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON TESTDB.SPRING_SESSION (PRINCIPAL_NAME);
CREATE TABLE TESTDB.SPRING_SESSION_ATTRIBUTES (
SESSION_PRIMARY_ID CHAR(36) NOT NULL,
ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
ATTRIBUTE_BYTES BLOB NOT NULL,
CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES TESTDB.SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);
```

![alt text](https://user-images.githubusercontent.com/23372515/88884577-8cd3d480-d254-11ea-9525-5f3c411f5cdb.png)

## To make a secure call, Use BASIC AUTH in posman, use username and password
![alt text](https://user-images.githubusercontent.com/23372515/88884581-8e050180-d254-11ea-85d3-7ef51a8596c5.png)

## After a successfull reqeust, Spring will saves the sessionID in Mysql DB and same sessionID will be in our Cookies
![alt text](https://user-images.githubusercontent.com/23372515/88884584-8e9d9800-d254-11ea-9163-60c4c2e6a40d.png)


 
