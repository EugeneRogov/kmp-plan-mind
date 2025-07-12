# Plan Mind

This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop, and Server.

## Docker Setup

### Prerequisites

- Docker Desktop installed on macOS

### Running with Docker

1. **Build and start all services:**

```bash
docker-compose up --build
```

2. **Stop all services:**

```bash
docker-compose down
```

3. **View logs:**

```bash
docker-compose logs -f server
docker-compose logs -f postgres
```

4. **Access services:**

- Server: http://localhost:8080
- PostgreSQL: localhost:5432
    - Database: planmind
    - User: planmind
    - Password: planmind123

### Development

To rebuild only the server after code changes:

```bash
docker-compose up --build server
```

## Original Setup

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that's common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple's CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you're sharing code with other platforms,
  you still need some amount of platform-specific code, for example to set up the UI.

* `/server` contains the backend code powered by Ktor.

* `/shared` contains the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [YouTrack](https://youtrack.jetbrains.com/newIssue?project=CMP).

You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.