# TaskForge

A task and sprint tracker backend built from scratch with Java and Spring Boot, to practise real-world backend development step by step.

## Tech stack

- Java 21
- Spring Boot (Spring Web, Validation)
- Maven
- JUnit 5
- Git and GitHub
- Planned: MySQL, Spring Data JPA, Docker, GitHub Actions

## Features so far

- Object-oriented domain model: abstract `Task` with `Bug`, `Story` and `Epic`, a `Status` enum and a `User` class
- Custom exceptions for business rules (invalid status transitions, task not found)
- Collections, streams and comparators used for filtering and sorting
- REST API with layered structure: controller, repository and DTOs
- Request validation and a global exception handler that returns clean 400 and 404 responses
- JUnit tests for the core domain

## Project structure

```
taskforge
├── core            Plain Java module: domain model, collections, tests
└── taskforge-api   Spring Boot REST API
```

## Getting started

Requirements: JDK 21 and Git.

```
git clone https://github.com/suhasinirgota/taskforge.git
cd taskforge/taskforge-api
.\mvnw.cmd spring-boot:run
```

The API starts on `http://localhost:8080`.

## API endpoints

| Method | Path | Description | Success |
| --- | --- | --- | --- |
| GET | `/api/hello` | Health check | 200 |
| GET | `/api/tasks` | List all tasks | 200 |
| GET | `/api/tasks/{id}` | Get one task | 200, or 404 if missing |
| POST | `/api/tasks` | Create a task | 201, or 400 if invalid |

Example request:

```
POST /api/tasks
Content-Type: application/json

{"title": "Add search feature", "type": "STORY"}
```

Example response (201 Created):

```
{"id":4,"title":"Add search feature","status":"TODO","points":5,"type":"Story"}
```

## Roadmap

- [ ] Persist data with MySQL and Spring Data JPA
- [ ] Integration tests and CI with GitHub Actions
- [ ] Assign tasks to users and manage sprints
- [ ] Docker support

## What I learned

Short notes on OOP design, HashMap vs list lookups, checked vs unchecked exceptions, dependency injection and why DTOs are separate from the domain model.