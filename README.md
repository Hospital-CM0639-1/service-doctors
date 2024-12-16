Spring Boot Docker Service Doctor
==========================
This service is a simple Spring Boot application that provides a RESTful API to manage doctors. It uses a postgres database to store the data.

Docker
========
Before to create the image and run the docker container, change the datasource of application.properties:
```
spring.datasource.url=jdbc:postgresql://hospital-database:5432/hospital
```
Assume that the name of the postgres container is *hospital-database* and the server database is running on port *5432*.
Then run the following command:
```
docker compose up --build -t doctor-service -d
```

Documentation
========
- Get all medical procedures of a doctor in a specific period of time.<br/>
GET medical-procedures/doctor/{doctorId}?startDate={yyyyy-MM-dd HH:mm}&endDate={yyyyy-MM-dd HH:mm}
