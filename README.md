# Spring Boot Microservices

[![build-all-workflow](https://github.com/vromanyu/spring-boot-microservices/actions/workflows/build-all-workflow.yml/badge.svg?branch=main)](https://github.com/vromanyu/spring-boot-microservices/actions/workflows/build-all-workflow.yml)

This repository is a learning playground for building **Spring Boot–based microservices**. It demonstrates a small but realistic microservice ecosystem including centralized configuration, service discovery, API gateway routing, and a full observability stack. The project also showcases CI with GitHub Actions and automated Docker image builds and pushes via a Maven plugin. A `docker-compose` file is included to run the services locally.

## Architecture Overview

The system is composed of:
- Multiple Spring Boot microservices
- Spring Cloud components for configuration, routing, and service discovery
- A complete observability stack for metrics, logs, and traces
- Containerized deployment using Docker and Docker Compose

## Features

### Microservices & Spring Cloud
- Small collection of Spring Boot microservices (for learning and experimentation)
- **Spring Cloud Config Server** for centralized external configuration
- **Eureka Server** for service discovery and registry
- **Spring Cloud Gateway** as the API gateway and single entry point
- **Keycloak** for centralized authentication and authorization on the Edge server

### Observability
- **OpenTelemetry** for distributed tracing
- **Micrometer** for application metrics
- **Prometheus** for metrics scraping and storage
- **Loki** for centralized log aggregation
- **Tempo** for distributed trace storage
- **Grafana** for metrics, logs, and trace visualization
- **Grafana Alloy** for telemetry collection and forwarding

### DevOps & Tooling
- Continuous Integration pipelines using **GitHub Actions**
- Docker images built & pushed using a **Maven plugin**
- `docker-compose` setup to run the entire microservice and observability stack locally

## Prerequisites
- Java (CI uses 21, 25)
- Maven 3.6+
- Docker
- Docker Compose

## Quickstart

1. Clone the repository
   - git clone https://github.com/vromanyu/spring-boot-microservices.git
   - cd spring-boot-microservices

2. Run the microservice cluster locally
   - docker-compose up --build
   - To run in the background: docker-compose up -d
   - To stop and remove containers: docker-compose down

## Contributing
Feel free to open issues or pull requests if you want to suggest improvements or add examples.

## Contact
Maintainer: @vromanyu
