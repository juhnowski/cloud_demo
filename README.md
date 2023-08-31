# Demo
Тестовое приложение

## Run spring boot initializer
Dependencies:
- Spring Data JPA
- Spring Web
- H2 Database
- Rest Repositories

## First run
Download generated demo.zip, unzip it, and execute
```
mvn clean install
```
and
```
mvn spring-boot:run
```
then app is started on http://localhost:8080
and the returned result will be JSON:
```json
{
  "_links" : {
    "profile" : {
      "href" : "http://localhost:8080/profile"
    }
  }
}
```