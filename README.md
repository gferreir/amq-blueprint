# amq-blueprint

Spring Boot AMQ Broker client. Examples of consumer (Sync and Async) and producer.

## Getting started

Compile and package the application:

```
java clean package
```

Run the application:

```
java -jar target/blueprint-0.0.1-SNAPSHOT.jar
```

Testing with Vscode Rest Client plugin
---

```json
POST http://localhost:8080/transaction/send
Accept: application/json
Content-Type: application/json

{
  "name": "Fulano da Silva",
  "age": 20,
  "country": "Brazil",
  "hobby": "Video Game"
}

###

POST http://localhost:8080/transaction/send/anotherQueue
Accept: application/json
Content-Type: application/json

{
  "name": "Fulano da Silva",
  "age": 20,
  "country": "Brazil",
  "hobby": "Video Game"
}

###

POST http://localhost:8080/transaction/send/many/7
Accept: application/json
Content-Type: application/json

###

GET http://localhost:8080/transaction/receive/anotherQueue
Accept: application/json
Content-Type: application/**json**
```