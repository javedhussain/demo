# Spring Boot Demo Application

This is a Spring Boot application that provides a secure User Management API with health monitoring capabilities.

## Features

- User Management API with CRUD operations
- JWT-based Authentication
- Health Check Endpoints
- PostgreSQL Database Integration
- Spring Security Implementation

## Prerequisites

- Java 17 or higher
- PostgreSQL Database
- Gradle

## Getting Started

### Building the Application

```bash
./gradlew build
```

### Running the Application

```bash
./gradlew bootRun
```

## API Documentation

### Health Check Endpoints

The application includes Spring Boot Actuator for health monitoring:

- `GET /actuator/health` - Returns the overall health status of the application
- `GET /actuator/health/liveness` - Returns the liveness status
- `GET /actuator/health/readiness` - Returns the readiness status

### User Management API (v1)

All user management endpoints are secured with JWT authentication except for registration and login.

#### Authentication Endpoints

1. **Register User**
   - Endpoint: `POST /api/v1/users/register`
   - Access: Public
   - Request Body:

     ```json
     {
       "username": "string",
       "password": "string",
       "email": "string"
     }
     ```

2. **Login**
   - Endpoint: `POST /api/v1/users/login`
   - Access: Public
   - Request Body:

     ```json
     {
       "username": "string",
       "password": "string"
     }
     ```

   - Response: JWT Token

#### Protected Endpoints

The following endpoints require a valid JWT token in the Authorization header:
`Authorization: Bearer <your_jwt_token>`

1. **Get All Users**
   - Endpoint: `GET /api/v1/users`
   - Access: Authenticated users

2. **Get User by ID**
   - Endpoint: `GET /api/v1/users/{id}`
   - Access: Authenticated users

3. **Update User**
   - Endpoint: `PUT /api/v1/users/{id}`
   - Access: Authenticated users
   - Request Body:

     ```json
     {
       "username": "string",
       "email": "string"
     }
     ```

4. **Delete User**
   - Endpoint: `DELETE /api/v1/users/{id}`
   - Access: Authenticated users

## Security

- JWT-based authentication
- Password encryption using BCrypt
- Protected endpoints requiring valid JWT tokens
- Token expiration and validation

## Configuration

Key application properties can be configured in `application.properties`:

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

# JWT Configuration
jwt.secret=your_jwt_secret
jwt.expiration=3600000  # 1 hour in milliseconds
```

## Build and Dependencies

The project uses Gradle for dependency management. Key dependencies include:

- Spring Boot 3.1.4
- Spring Security
- Spring Data JPA
- PostgreSQL Driver
- Spring Boot Actuator
- JWT (JSON Web Token)

## Contributing

Please follow these steps for contributing:

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.
