# ATS Resume Analyzer

A production-oriented Spring Boot backend application that allows users to upload resumes, extract content from PDF files, perform ATS-style resume analysis, and securely manage resume data using JWT authentication.

The project demonstrates modern backend development practices including authentication, file handling, caching, database persistence, API documentation, and containerization.

---

## Features

### Authentication & Security

* JWT-based Authentication
* User Registration and Login
* Password Encryption using BCrypt
* Role-Based Security
* Protected REST APIs

### Resume Management

* Upload Resume PDFs
* Retrieve Uploaded Resumes
* Delete Resumes
* User-specific Resume Access

### PDF Processing

* PDF Text Extraction using Apache PDFBox
* Resume Content Parsing
* Automatic Text Storage

### ATS Resume Analysis

* Resume Skill Analysis
* ATS-style Score Generation
* Matched Skills Detection
* Missing Skills Identification
* Analysis History Tracking

### Performance Optimization

* Redis Caching
* Cached ATS Analysis Responses
* Reduced Processing Time

### API Documentation

* Swagger/OpenAPI Integration
* Interactive API Testing

### Deployment

* Dockerized Spring Boot Application
* Environment Variable Configuration
* Production-Ready Configuration Structure

---

## Tech Stack

### Backend

* Java 21
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Hibernate

### Database

* MySQL

### Caching

* Redis

### Authentication

* JWT (JSON Web Tokens)
* BCrypt Password Encoder

### File Processing

* Apache PDFBox

### Documentation

* Swagger / OpenAPI

### DevOps

* Docker

---

## Project Architecture

Controller
↓
Service
↓
Repository
↓
MySQL Database

Additional Components:

JWT Authentication Filter

Redis Cache Layer

PDF Processing Service

ATS Analysis Engine

---

## API Modules

### Authentication APIs

POST /api/auth/register

POST /api/auth/login

### Resume APIs

POST /api/resumes/upload

GET /api/resumes

GET /api/resumes/{id}

DELETE /api/resumes/{id}

### Analysis APIs

GET /api/resumes/{id}/analyze

GET /api/resumes/{id}/analysis-history

---

## Security Features

* Stateless Authentication
* JWT Token Validation
* Password Hashing with BCrypt
* Protected Endpoints
* User-specific Data Isolation

---

## Redis Caching

ATS analysis results are cached using Redis to reduce repeated computation and improve response time.

Cache Strategy:

* First Request → Analyze Resume + Store in Redis
* Subsequent Requests → Return Cached Result

---

## Docker Support

The application can be containerized using Docker and configured using environment variables.

Example Environment Variables:

DB_URL

DB_USERNAME

DB_PASSWORD

JWT_SECRET

REDIS_HOST

REDIS_PORT

---

## Learning Outcomes

This project demonstrates practical experience with:

* REST API Development
* Spring Security
* JWT Authentication
* File Upload Handling
* PDF Parsing
* Database Design
* Caching with Redis
* Docker Containerization
* Clean Architecture
* Production-Level Backend Development

---

## Future Enhancements

* OpenAI Resume Feedback
* Resume Improvement Suggestions
* Job Description Matching
* Resume Versioning
* Email Notifications
* Docker Compose Setup
* CI/CD Pipeline
* Cloud Deployment (AWS)

---

## Author

Shubham Kumar

Aspiring Backend Engineer focused on Java, Spring Boot, System Design, and Scalable Backend Development.
