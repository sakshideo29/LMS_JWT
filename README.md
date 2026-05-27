# Learning Management System (LMS) - JWT Authentication

A backend-based Learning Management System developed using Spring Boot and JWT Authentication.  
This project provides secure REST APIs for user authentication, course management, and enrollment functionality.

## Features

- User Registration & Login
- JWT-based Authentication & Authorization
- Role-based access handling
- Course Management APIs
- Student Enrollment APIs
- Secure RESTful APIs
- Layered Architecture Implementation
- Exception Handling
- Database Integration
- API Testing using Postman

---

## Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- H2 Database
- Maven
- Postman

---

## Project Architecture

The project follows layered architecture:

- Controller Layer
- Service Layer
- Repository Layer
- Security Layer

This improves maintainability, scalability, and separation of concerns.

---

## API Endpoints

### Authentication APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/register` | Register new user |
| POST | `/auth/login` | User login |

### Course APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/courses` | Get all courses |
| POST | `/courses` | Add new course |

### Enrollment APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/enroll` | Enroll student in course |

---

## Security

- Implemented JWT Authentication
- Password encryption using Spring Security
- Protected APIs with authorization

---

## Database

- H2 In-Memory Database
- Easy testing and development setup

---

## How to Run the Project

### Prerequisites

- Java 17 (or your version)
- Maven
- IDE (IntelliJ IDEA recommended)

### Steps

1. Clone the repository

```bash
git clone https://github.com/sakshideo29/LMS_JWT.git
```

2. Navigate to project folder

```bash
cd LMS_JWT
```

3. Run the application

```bash
mvn spring-boot:run
```

4. Access H2 Console

```bash
http://localhost:8080/h2-console
```

---

## Future Improvements

- Docker Integration
- PostgreSQL Migration
- AWS Deployment
- Swagger API Documentation
- Unit & Integration Testing

---

## Author

Sakshi Deo
