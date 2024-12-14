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
The service provides the following endpoints:
CRUD operations for doctors:
- GET /staff/doctors
- GET /staff/doctors/{id}
- POST /staff/doctors
- PUT /staff/doctors/{id}
- DELETE /doctors/{id}

An example of a doctor object:
```
{
	"firstName": "firstName",
	"lastName": "lastName",
	"email": "firstName.lastName@hospital.com",
	"phoneNumber": "(555)123-4567",
	"role": "DOCTOR",
	"department": "Emergency Medicine",
	"specialization": "Trauma",
	"hireDate": "2024-12-13 00:00:00",
	"active": true
}