# ~~TerraCambio API~~

## ⚠ Changed to monorepo

I changed to a mono repo structure so now the server code is at [TerraCambio github](https://github.com/braiso-22/TerraCambio)


## Old readme

A Kotlin + Spring Boot API for TerraCambio. This repository contains a minimal, clean architecture-style setup (ports and adapters/hexagonal) for a Listing feature, plus Springdoc OpenAPI for interactive API docs.

## Tech Stack
- Kotlin 2.1.x
- Spring Boot 3.5.x
- Gradle (Kotlin DSL)
- springdoc-openapi (Swagger UI)

Main application class: `com.braiso_22.terracambio_api.TerracambioApiApplication`

## Prerequisites
- JDK 17 (required)
  - Verify with: `java -version` (should report version 17)
- Internet access for Gradle to download dependencies on first run

The project uses the Gradle Wrapper, so you do not need a local Gradle installation.

## How to Run
From the project root (this folder):

- Windows (PowerShell/CMD):
  - Run tests: `gradlew.bat test`
  - Start the dev server: `gradlew.bat bootRun`
- macOS/Linux:
  - Run tests: `./gradlew test`
  - Start the dev server: `./gradlew bootRun`

By default the server starts on port 8080 (configurable in `src/main/resources/application.properties`).

## API Documentation (Swagger UI)
Once the server is running, open:
- http://localhost:8080/swagger-ui/index.html

This is provided by springdoc-openapi and reflects the controllers available in the application.

## Project Layout (high level)
- `src/main/kotlin/com/braiso_22/terracambio_api/TerracambioApiApplication.kt` — Spring Boot entry point.
- `listing` package — Listing feature organized with ports (application/domain) and adapters (in/out). The HTTP controller is in `listing.intraestructure.adapters.in.springboot_api`.
- `src/main/resources/application.properties` — Spring Boot configuration (port, etc.).

## Common Tasks
- Build a production jar: use `gradlew.bat build` (Windows) or `./gradlew build` (Unix). The boot jar will be under `build/libs`.
- Run a single test class (example): `./gradlew test --tests com.braiso_22.terracambio_api.TerracambioApiApplicationTests`

## Troubleshooting
- If the app fails to start, ensure you are using JDK 17 and that no other process is using port 8080.
- If Swagger UI doesn’t load, confirm the application is running and that `springdoc-openapi` is on the classpath (it is configured via Gradle).

## License
This project is provided as-is for development/testing purposes.
