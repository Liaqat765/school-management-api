# 🎓 School Management System - Spring Boot REST API

A simple backend system to manage students, courses, enrollments, and grades for a private school.

## 🛠 Tech Stack
- Java 21
- Spring Boot 3.x
- PostgreSQL
- Spring Data JPA
- Hibernate
- Maven
- Swagger / SpringDoc OpenAPI
- ModelMapper 
- Lombok
- 

## 🚀 Setup Instructions


1. Clone the repo:
   
   git clone https://github.com/Liaqat765/school-management-api.git
   
   
2. Build the project:
   mvn clean install

3. Run the application:
    mvn spring-boot:run
4. Access Swagger UI:
   http://localhost:8383/swagger-ui.html

📘 API Summary

🧑 Student Endpoints (/api/v1/students)

POST /create – Create a new student

GET /all – Get a list of all students

GET /{id} – Get a student by ID

PUT /update/{id} – Update an existing student

DELETE /delete/{id} – Delete a student

📚 Course Endpoints (/api/v1/courses)

POST /create – Create a new course

GET /all – Get a list of all courses

GET /{id} – Get a course by ID

PUT /update/{id} – Update an existing course

DELETE /delete/{id} – Delete a course

🔗 Enrollment & Grade Endpoints (/api/v1/enrollments)

POST /create – Enroll a student in one or more courses

GET /student/{studentId} – Get all enrollments for a specific student

GET /{studentId}/course/{courseId} – Get a student's grade in a specific course

PUT /update/{enrollmentId} – Update an enrollment or grade

DELETE /delete/{enrollmentId} – Delete an enrollment