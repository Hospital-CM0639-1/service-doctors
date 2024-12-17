Spring Boot Docker Service Doctor
==========================
This service is a simple Spring Boot application that provides a RESTful API to manage doctors.
It provides the following features:

_Medical procedures_
- Get all medical procedures made by doctor in a specific period of time
- Get all patient's medical procedures in a specific period of time
- Get all medical procedures paged
- Create a new medical procedure
- Update a medical procedure
- Delete a medical procedure

_Patient vitals_
- Get all vitals of patient in a specific period of time
- Get all patient vitals visited from doctor in a specific period of time
- Get all patient vitals paged
- Create a new patient vitals
- Update a patient vitals
- Delete a patient vitals

Docker
========
Before to create the image and run the docker container, change the datasource of _application.properties_:
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
MEDICAL PROCEDURE
- Get all medical procedures made by doctor in a specific period of time.<br/>
`GET /medical-procedures/doctor/{doctorId}?startDate={yyyyy-MM-dd HH:mm}&endDate={yyyyy-MM-dd HH:mm}`
- Get all patient's medical procedures in a specific period of time.<br/>
`GET /medical-procedures/patient/{patientId}?startDate={yyyyy-MM-dd HH:mm}&endDate={yyyyy-MM-dd HH:mm}`
- Get all medical procedures paged.<br/>
`GET /medical-procedures?page={page}&size={size}&sort={sort}`
- Create a new medical procedure.<br/>
`POST /medical-procedures`
- Update a medical procedure.<br/>
`PUT /medical-procedures/{id}`
- Delete a medical procedure.<br/>
`DELETE /medical-procedures/{id}`

  _Example of object:_
    ```json
    {
      "id": 1,
      "procedureName": "ECG",
      "procedureTimestamp": "2024-12-01T09:45:00",
      "description": "Electrocardiogram performed to assess chest pain",
      "procedureCost": 200.0,
      "emergencyVisit": {
        "id": 1
      },
      "staff": {
        "id": 1
      }
    }
    ```

PATIENT VITALS
- Get all vitals of patient in a specific period of time.<br/>
`GET /patient-vitals/patient/{patientId}?startDate={yyyyy-MM-dd HH:mm}&endDate={yyyyy-MM-dd HH:mm}`
- Get all patient vitals visited from doctor in a specific period of time.<br/>
`GET /patient-vitals/doctor/{doctorId}?startDate={yyyyy-MM-dd HH:mm}&endDate={yyyyy-MM-dd HH:mm}`
- Get all patient vitals paged.<br/>
`GET /patient-vitals?page={page}&size={size}&sort={sort}`
- Create a new patient vitals.<br/>
`POST /patient-vitals`
- Update a patient vitals.<br/>
`PUT /patient-vitals/{id}`
- Delete a patient vitals.<br/>
`DELETE /patient-vitals/{id}`

  _Example of object:_
  ```json
  {
    "id": 1,
    "recordedAt": "2024-12-04T09:30:00",
    "bodyTemperature": 38.2,
    "bloodPressureSystolic": 130,
    "bloodPressureDiastolic": 85,
    "heartRate": 90,
    "respiratoryRate": 20,
    "oxygenSaturation": 96.5,
    "additionalObservations": "Post-surgery monitoring, vitals stable",
    "staff": {
      "id": 1
    },
    "emergencyVisit": {
      "id": 1
    }
  }
  ```