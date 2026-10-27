# project-practicum

> A Spring Boot backend for a smart-home scenario — automating window blinds and battery settings from weather data, temperature and light. Built for the T-Bank project practicum.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=flat-square&logo=postgresql&logoColor=white)
![OpenAPI](https://img.shields.io/badge/OpenAPI-6BA539?style=flat-square&logo=openapiinitiative&logoColor=white)

## About

The service decides how a home's window blinds and battery should behave based on live **weather** (fetched from an external weather API), **ambient / battery temperature** and **light** conditions. Scheduled jobs poll the data, apply the configured rules, act on the blinds, and keep a log of every action and settings change.

## Features

- 🪟 Window-blind automation (open / close) from weather + light + temperature rules
- 🔋 Battery settings & battery-temperature tracking
- 🌦️ External weather integration via a reactive `WebClient` (Spring WebFlux)
- ⏰ Scheduled tasks with **ShedLock** (safe scheduling across instances)
- 👤 Users and bot users, notifications, and full action/settings logging
- ✅ Request validation and a global exception handler
- 📚 OpenAPI / Swagger UI documentation

## Tech stack

**Java 17 · Spring Boot 3.4 · Spring Web + WebFlux · Spring Data JPA · PostgreSQL · ShedLock · Lombok · springdoc-openapi · Testcontainers · Gradle · Docker**

## Architecture

```
ru.tbank.practicum
├── config         # app configuration (AppConfig)
├── entity         # JPA entities (Users, BotUser, WindowBlind*, Battery*, AmbientTemp, Notifications, logs)
├── repository     # Spring Data JPA repositories
├── service        # business logic — WeatherService, SettingService, UserService
│   ├── dto        # weather API DTOs (Weather, Wind, Clouds, Rain, Coord, …)
│   └── enums      # blind status / curtain action enums
├── mapper         # entity ↔ DTO mapping
├── job            # ScheduledTasks
└── exception      # GlobalExceptionHandler
```

## Getting started

### Prerequisites
- JDK 17+
- PostgreSQL (or use the provided `docker-compose.yaml`)

### Configuration
Set your database and weather-API credentials in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/dbPracticum
    username: admin
    password: dbPracticum
app:
  cred:
    token: "your-weather-api-token"
```

### Run

```bash
# start PostgreSQL
docker compose up -d

# run the app
./gradlew bootRun
```

API docs: http://localhost:8080/swagger-ui.html

## Tests

```bash
./gradlew test
```

Integration tests use **Testcontainers** to spin up a disposable PostgreSQL instance.

## CI

GitHub Actions workflow in `.github/workflows/ci.yml` builds and tests the project on each push.
