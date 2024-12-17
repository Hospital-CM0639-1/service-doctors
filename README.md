Spring Boot Docker Service Doctor
==========================
This service is a simple Spring Boot application that provides a RESTful API to manage doctors.
It provides the following features:
- Get all medical procedures made by doctor in a specific period of time
- Get all patient's medical procedures in a specific period of time
- Get all medical procedures paged
- Create a new medical procedure
- Update a medical procedure
- Delete a medical procedure

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

API Documentation
========
- Get all medical procedures made by doctor in a specific period of time.<br/>
`GET /medical-procedures/doctor/{doctorId}?startDate={yyyyy-MM-dd HH:mm}&endDate={yyyyy-MM-dd HH:mm}
`
- Get all patient's medical procedures in a specific period of time.<br/>
`GET /medical-procedures/patient/{patientId}?startDate={yyyyy-MM-dd HH:mm}&endDate={yyyyy-MM-dd HH:mm}
`
- Get all medical procedures paged.<br/>
`GET /medical-procedures?page={page}&size={size}&sort={sort}
`
- Create a new medical procedure.<br/>
`POST /medical-procedures
`
- Update a medical procedure.<br/>
`PUT /medical-procedures/{id}
`
- Delete a medical procedure.<br/>
```DELETE /medical-procedures/{id}```