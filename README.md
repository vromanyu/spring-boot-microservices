# Spring Boot Microservices

[![springboot-restful-webservices](https://github.com/vromanyu/spring-boot-microservices/actions/workflows/springboot-restful-webservices.yml/badge.svg)](https://github.com/vromanyu/spring-boot-microservices/actions/workflows/springboot-restful-webservices.yml)


This repository is a learning playground for building microservices with Spring Boot. It demonstrates a small microservice cluster, CI with GitHub Actions, and automated Docker image builds and pushes via a Maven plugin. A docker-compose file is included to run the services locally as a cluster.

## Features
- Small collection of Spring Boot microservices (for learning and experimentation)
- Continuous Integration pipelines using GitHub Actions
- Docker images built & pushed using a Maven plugin configured in the project
- docker-compose file to run the microservice cluster locally

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

## Continuous Integration
CI pipelines are provided as GitHub Actions workflows (see `.github/workflows/`). The workflows build the projects and produce Docker images according to the repository configuration.

## Contributing
Feel free to open issues or pull requests if you want to suggest improvements or add examples.

## Contact
Maintainer: @vromanyu
