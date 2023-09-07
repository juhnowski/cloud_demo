# Demo
Демо построения распределенного приложения

# Этап 1 - Разработка сервиса

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

Этап 2 - Создание Docker образа 
Строим образ:
```
docker build -t myorg/myapp .
```
Проверяем его работоспособность:
```
docker run -p 8080:8080 -e "JAVA_OPTS=-Ddebug -Xmx128m" myorg/myapp
```

Этап 3 - Паттерн Sidecar

