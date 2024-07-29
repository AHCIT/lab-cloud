# Microservices Architecture Laboratory

Welcome to the "Microservices Architecture Laboratory" project, a Spring Cloud-based microservices example application
designed to help developers who are new to microservices development get started quickly.

## Overview

The project consists of the following services:

1. **Gateway Service**: Routes requests to appropriate microservices.
2. **Auth Service**: Handles user authentication and authorization.
3. **User Service**: Manages user information and operations.
4. **Config Service**: Centrally manages configuration properties.
5. **Product Service**: Manages product information and operations.
6. **Order Service**: Manages orders and related operations.
7. **Payment Service**: Handles payment transactions.
8. **Hardware Data Collection Service**: Collects and processes data from hardware devices.
9. **Report Service**: Generates and manages reports.
10. **User Behavior Data Service**: Collects and analyzes user behavior data to provide insights and analytics.

## Architecture

The project architecture includes the following components:

- **Spring Boot**: The base framework for building microservices.
- **Spring Cloud**: Provides tools for service discovery, configuration, routing, and more.
- **Nacos**: Service registry and configuration center for service discovery and configuration management.
- **Spring Cloud Gateway**: API Gateway for routing requests.
- **Spring Security & OAuth2**: For security and authentication.
- **Feign**: Simplifies HTTP client code for inter-service communication.
- **MySQL/PostgreSQL**: Relational database for storing business data.
- **Redis**: Caching and distributed locking.
- **Docker**: Containerization of services.
- **Kubernetes**: Orchestration and management of containerized services.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven or Gradle
- Docker
- Kubernetes (optional, for deployment)

1. Clone the repository:
   ```sh
   git clone https://github.com/AHCIT/lab-cloud.git
2. Build and run the services,use command like
   ```shell
   docker buildx build --platform linux/amd64 -t lab-action-server:0.0.1-SNAPSHOT .    
   docker run -d -p 8919:8919 --name lab-action-server --memory=8g --cpus=4 lab-action-server:0.0.1-SNAPSHOT
   ```
   