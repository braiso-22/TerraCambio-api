# TerraCambio — Development Guidelines

This document collects project-specific knowledge for TerraCambio (Spring Boot, Kotlin, Gradle). It is intended for advanced developers contributing to this codebase.

## 1) Build and Configuration

- Build tool: Gradle (Kotlin DSL). Primary entry point:
  - Spring Boot application main: `com.braiso_22.terracambio_api.TerracambioApiApplication` (function `main`)
- Plugins and versions are managed via `gradle/libs.versions.toml`:
  - Kotlin: 2.1.10
  - Spring Boot: 3.5.5
  - Spring Dependency Management: 1.1.7
  - springdoc-openapi: 2.8.13
- Core dependencies (see `build.gradle.kts`):
  - `org.springframework.boot:spring-boot-starter-web`
  - `com.fasterxml.jackson.module:jackson-module-kotlin`
  - `org.jetbrains.kotlin:kotlin-reflect`
  - `org.springdoc:springdoc-openapi-starter-webmvc-ui`
  - Tests use `spring-boot-starter-test` and `kotlin-test-junit`.

Build commands (run from project root):
- Windows PowerShell/CMD:
  - Run tests: `gradlew.bat test`
  - Build: `gradlew.bat build`
  - Run dev server: `gradlew.bat bootRun`
- Unix-like environments:
  - Run tests: `./gradlew test`
  - Build: `./gradlew build`
  - Run dev server: `./gradlew bootRun`

Server configuration:
- `src/main/resources/application.properties` controls app settings (server port, etc.). Default port is 8080 unless overridden.
- Component scanning: `@ComponentScan("listing")` is set in `TerracambioApiApplication` to pick up Listing features.
- Logging: Spring Boot’s default logging (Logback) is used; customize with `logback-spring.xml` if needed.
- Static resources (if any) are served from `src/main/resources/static` under `/` by Spring Boot static resource mappings.
- OpenAPI/Swagger UI via springdoc: accessible at `/swagger-ui/index.html` once the server is running.

Notes:
- JDK 17 is configured via Gradle toolchain and is recommended for Spring Boot 3.x.
- The project description and version are set in `build.gradle.kts`.

## 2) Testing

Frameworks:
- Spring Boot Test (`org.springframework.boot:spring-boot-starter-test`) with JUnit Platform (JUnit 5).
- Kotlin Test (JUnit 4 compatibility artifacts may also be present via `kotlin-test-junit`). Tests run on the JUnit Platform per Gradle config.

Canonical test patterns:
- Application context smoke test: see `src/test/kotlin/com/braiso_22/terracambio_api/TerracambioApiApplicationTests.kt`.
- Domain/unit tests: see `src/test/kotlin/com/braiso_22/listing/domain/ListingDomainValidationTest.kt`.

Running tests:
- All tests: `gradlew.bat test` (Windows) or `./gradlew test` (Unix)
- One test class by FQN: e.g., `./gradlew test --tests com.braiso_22.terracambio_api.TerracambioApiApplicationTests`
- One test method: `./gradlew test --tests "com.braiso_22.terracambio_api.TerracambioApiApplicationTests.contextLoads"`

Add a new test:
- Create a file under `src/test/kotlin` in the appropriate package (e.g., `com.braiso_22`).
- Example Spring Boot test snippet:

  ```kotlin
  @org.springframework.boot.test.context.SpringBootTest
  class ExampleTest {
      @org.junit.jupiter.api.Test
      fun contextLoads() {
          // add assertions for your beans/services
      }
  }
  ```

Troubleshooting:
- If the context fails to start, verify `@ComponentScan("listing")` finds your beans and that constructor dependencies are satisfied.
- For HTTP endpoint tests, you can use `@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)` and `TestRestTemplate`, or slice tests with `@WebMvcTest` for controllers.
- If JSON serialization fails, ensure your DTOs are Kotlin-friendly (default values, nullable where appropriate) and that `jackson-module-kotlin` is on the classpath (it is).

## 3) Additional Development Notes

Project layout and conventions:
- Package root for the Spring Boot app: `com.braiso_22.terracambio_api`.
- Listing feature lives under `listing` with a hexagonal/ports-and-adapters structure (application ports, domain, and adapters).
- HTTP API: `listing.intraestructure.adapters.in.springboot_api.ListingController` exposes endpoints, e.g., `GET /api/v1/listing` (returns a list of `ListingDto`).

REST and DTOs:
- Controllers are annotated with `@RestController` and map routes with `@RequestMapping`/`@GetMapping`/`@PostMapping`.
- Keep controllers thin; delegate to application services (ports) when they are introduced.
- DTOs live alongside controllers; use Jackson annotations if needed.

Configuration-first:
- Changes to ports, hosts, or environment should go through `application.properties` whenever possible.

Logging:
- Use slf4j (`LoggerFactory.getLogger(...)`). Avoid logging sensitive data.

Local run tips:
- Start server: `gradlew.bat bootRun` (Windows) or `./gradlew bootRun` (Unix).
- After startup, try `GET http://localhost:8080/api/v1/listing` to receive a sample response from `ListingController`.
- Swagger UI: `http://localhost:8080/swagger-ui/index.html` (if springdoc starter is on the classpath).

Code style:
- Kotlin conventions apply. Keep domain models immutable; prefer data/value classes.
- For Spring, favor constructor injection and final/val properties.

Release/packaging:
- Use Spring Boot’s packaging (`bootJar`) by default. Additional tasks like building an image can be configured later if needed.

Security considerations:
- Validate and sanitize input at controller boundaries.
- Externalize secrets via environment variables or external config; keep `application.properties` free of secrets.

Maintenance checklist when adding endpoints:
- Add a new controller or method under `listing.intraestructure.adapters.in.springboot_api` (or a new feature package) and corresponding tests under `src/test/kotlin`.
- If the endpoint serializes models, ensure DTOs are Jackson-compatible.
- Update OpenAPI automatically via springdoc annotations/config, or add manual documentation if needed.
- Add minimal logging for observability and error paths.
