# TerraCambio — Development Guidelines

This document collects project-specific knowledge for TerraCambio (Ktor, Kotlin, Gradle). It is intended for advanced developers contributing to this codebase.

## 1) Build and Configuration

- Build tool: Gradle (Kotlin DSL). Primary entry points:
  - Application main: `io.ktor.server.netty.EngineMain`
  - Ktor module: `com.braiso_22.Application.module`
- Plugins and versions are managed via `gradle/libs.versions.toml`:
  - Kotlin: 2.1.10
  - Ktor: 3.2.3
  - Logback: 1.4.14
- Core dependencies (see `build.gradle.kts`): ktor-server-core, content-negotiation, kotlinx-json, swagger, netty, config-yaml, and logback. Tests use `ktor-server-test-host` and `kotlin-test-junit`.

Build commands (run from project root):
- Windows PowerShell/CMD:
  - Run tests: `gradlew.bat test`
  - Build: `gradlew.bat build`
  - Run dev server: `gradlew.bat run`
- Unix-like environments:
  - Run tests: `./gradlew test`
  - Build: `./gradlew build`
  - Run dev server: `./gradlew run`

Server configuration:
- `src/main/resources/application.yaml` is loaded via `ktor-server-config-yaml`. Defaults expected by Ktor Generator apply (host/port, dev profile, etc.). Netty EngineMain uses this file by default; the `Application.module` function wires Ktor features: serialization, HTTP, and routing.
- Logging: `src/main/resources/logback.xml`.
- Static resources are served from `src/main/resources/static` under `/static`.
- Swagger: Plugin is available; consult `Routing.kt` and `documentation.yaml` under `resources/openapi` if you add Swagger routes.

Notes:
- The `settings.gradle.kts` and `gradle.properties` follow standard Ktor template conventions; nothing custom to change for local dev beyond typical JDK setup (JDK 17+ recommended for Ktor 3.x).

## 2) Testing

Frameworks:
- Ktor Test Host (`io.ktor:ktor-server-test-host`) for in-memory application testing.
- Kotlin Test (JUnit 4 adapter) via `kotlin-test-junit`.

Canonical test pattern:
- Use `testApplication { ... }` to spin up an embedded test server.
- Call `application { module() }` to install our module configuration.
- Use the provided HTTP client to perform requests.

Example (existing): `src/test/kotlin/ApplicationTest.kt`
- Verifies `GET /` returns 200.

Running tests:
- All tests: `gradlew.bat test` (Windows) or `./gradlew test` (Unix)
- One test class by FQN: e.g., `./gradlew test --tests com.braiso_22.ApplicationTest`
- One test method: `./gradlew test --tests "com.braiso_22.ApplicationTest.testRoot"`

Add a new test:
- Create a file under `src/test/kotlin` in the appropriate package (e.g., `com.braiso_22`).
- Template snippet:
  
  ```kotlin
  class ExampleTest {
      @Test
      fun `smoke root`() = testApplication {
          application { module() }
          val res = client.get("/")
          assertEquals(HttpStatusCode.OK, res.status)
      }
  }
  ```

Demonstrated test run (validated locally during preparation of this document):
- We temporarily added a `GuidelinesSmokeTest` using the above pattern and ran it with:
  - `./gradlew test --tests com.braiso_22.GuidelinesSmokeTest` (Unix) or `gradlew.bat test --tests com.braiso_22.GuidelinesSmokeTest` (Windows)
- The test passed and was then removed to keep the repository unchanged except for this guidelines file.

Troubleshooting:
- Unresolved reference `get` in tests: ensure `import io.ktor.client.request.*` is present when calling `client.get("…")`.
- If binding to the HTTP port fails in integration scenarios, prefer Ktor Test Host (as above) rather than starting a real Netty server in tests.
- If serialization fails, check `configureSerialization()` in `Serialization.kt` and ensure the content negotiation plugin is installed.

## 3) Additional Development Notes

Project layout and conventions:
- Package root: `com.braiso_22` for the app; domain models may live under feature-specific packages, e.g., `com.braiso_22.listing.domain`.
- Domain example provided: `Listing`, `Type`, and `Money` value class (Kotlin inline class). Keep domain models immutable; prefer Kotlin data/value classes.
- Routing is centralized in `Routing.kt` via `fun Application.configureRouting()`. Add new routes inside `routing { … }` and keep handlers thin. Use separate files per feature if route surface grows.
- Serialization: kotlinx.serialization with JSON. Annotate models with `@Serializable` if you plan to serialize them via Ktor (current domain types are not annotated; add as needed at the boundary).
- Configuration-first: changes to ports, hosts, or environment should go through `application.yaml` whenever possible.
- Logging: use slf4j facade via Logback; prefer structured logs and avoid logging sensitive data.

Local run tips:
- Start server: `gradlew.bat run` (Windows) or `./gradlew run` (Unix). After startup, `GET http://localhost:8080/` should respond with "Hello World!" as implemented in `Routing.kt`.
- Static content is under `/static` (e.g., `http://localhost:8080/static/index.html`).

Code style:
- Kotlin conventions apply. Keep functions small and pure where possible. For Ktor, group feature installation in separate `configureXxx()` functions, as already used (Serialization, HTTP, Routing). Prefer top-level functions for Ktor configuration blocks.
- Tests: prefer `testApplication` over legacy `withTestApplication` for Ktor 3.x.

Release/packaging:
- The Ktor Gradle plugin provides additional tasks such as `buildFatJar`, `buildImage`, and Docker-related tasks from the template. Use these when preparing runnable artifacts; no extra configuration is currently required.

Security considerations:
- Validate and sanitize input at route boundaries; configure content negotiation to fail fast on unknown properties if desired.
- Keep `application.yaml` and `logback.xml` free of secrets. Use environment variables or externalized config when necessary.

Maintenance checklist when adding endpoints:
- Add route in `Routing.kt` (or a new file) and unit tests in `src/test/kotlin` using Test Host.
- If the route serializes models, annotate them with `@Serializable`.
- Update OpenAPI under `resources/openapi/documentation.yaml` if API surface changes.
- Add minimal logging for observability and error paths.
