# Tasks: Client CRUD API

**Input**: Design documents from `/specs/001-client-crud/`

**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story.

## Format: `[ID] [P?] [Story] Description`

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Initialize the Spring Boot project and shared tooling.

- [ ] T001 Create the project structure per the implementation plan under src/main/java/com/example/clientservice and src/test/java/com/example/clientservice
- [ ] T002 Initialize a Java 17 Spring Boot 3 project with Spring Web, Spring Data JPA, Spring Validation, Spring Security, and PostgreSQL dependencies
- [ ] T003 [P] Configure build and test tooling such as Maven/Gradle, JUnit 5, and basic code quality settings

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Establish the persistence, security, and error handling foundation used by all user stories.

- [ ] T004 Create the Client JPA entity and repository under src/main/java/com/example/clientservice/model and src/main/java/com/example/clientservice/repository
- [ ] T005 Configure PostgreSQL connection properties and application configuration for the service
- [ ] T006 Implement HTTP Basic Authentication security configuration for the API
- [ ] T007 Add shared validation and exception handling for field errors and business-rule violations
- [ ] T008 Create DTOs for create, update, and response payloads under src/main/java/com/example/clientservice/dto

**Checkpoint**: Foundation ready - user story implementation can now begin in parallel

---

## Phase 3: User Story 1 - Create a client (Priority: P1) 🎯 MVP

**Goal**: Allow clients to be created with validation and uniqueness enforcement.

**Independent Test**: A client can be created through the API and the stored record can be retrieved or verified.

### Tests for User Story 1

- [ ] T009 [P] [US1] Add controller integration test for successful client creation in src/test/java/com/example/clientservice/controller/ClientControllerTest.java
- [ ] T010 [P] [US1] Add validation test for missing required fields in src/test/java/com/example/clientservice/controller/ClientControllerTest.java
- [ ] T011 [P] [US1] Add uniqueness test for duplicate coreClientId or clientName in src/test/java/com/example/clientservice/controller/ClientControllerTest.java

### Implementation for User Story 1

- [ ] T012 [US1] Implement create client endpoint in src/main/java/com/example/clientservice/controller/ClientController.java
- [ ] T013 [US1] Implement create client business logic in src/main/java/com/example/clientservice/service/ClientService.java
- [ ] T014 [US1] Add validation for required fields and duplicate client data in src/main/java/com/example/clientservice/service/ClientService.java
- [ ] T015 [US1] Add persistence support for create operations in src/main/java/com/example/clientservice/repository/ClientRepository.java

**Checkpoint**: At this point, User Story 1 should be fully functional and testable independently

---

## Phase 4: User Story 2 - Update an existing client (Priority: P1)

**Goal**: Allow existing clients to be updated by coreClientId with validation and duplicate checks.

**Independent Test**: A client can be updated by coreClientId and the updated values are persisted.

### Tests for User Story 2

- [ ] T016 [P] [US2] Add controller integration test for successful client update in src/test/java/com/example/clientservice/controller/ClientControllerTest.java
- [ ] T017 [P] [US2] Add validation test for non-existent coreClientId in src/test/java/com/example/clientservice/controller/ClientControllerTest.java
- [ ] T018 [P] [US2] Add duplicate-name validation test for updates in src/test/java/com/example/clientservice/controller/ClientControllerTest.java

### Implementation for User Story 2

- [ ] T019 [US2] Implement update client endpoint in src/main/java/com/example/clientservice/controller/ClientController.java
- [ ] T020 [US2] Implement update business logic in src/main/java/com/example/clientservice/service/ClientService.java
- [ ] T021 [US2] Add update validation and duplicate checks in src/main/java/com/example/clientservice/service/ClientService.java

**Checkpoint**: At this point, User Story 2 should be independently functional

---

## Phase 5: User Story 3 - Delete a client (Priority: P1)

**Goal**: Allow clients to be deleted by coreClientId with clear validation responses.

**Independent Test**: A client can be deleted by coreClientId and no longer exists afterwards.

### Tests for User Story 3

- [ ] T022 [P] [US3] Add controller integration test for successful client deletion in src/test/java/com/example/clientservice/controller/ClientControllerTest.java
- [ ] T023 [P] [US3] Add validation test for deleting a non-existent client in src/test/java/com/example/clientservice/controller/ClientControllerTest.java

### Implementation for User Story 3

- [ ] T024 [US3] Implement delete client endpoint in src/main/java/com/example/clientservice/controller/ClientController.java
- [ ] T025 [US3] Implement delete business logic in src/main/java/com/example/clientservice/service/ClientService.java
- [ ] T026 [US3] Add delete validation for missing clients in src/main/java/com/example/clientservice/service/ClientService.java

**Checkpoint**: All user stories should now be independently functional

---

## Phase 6: Polish & Cross-Cutting Concerns

**Purpose**: Final cleanup, documentation, and validation of the complete feature.

- [ ] T027 [P] Add or update API documentation for the endpoints and error responses
- [ ] T028 [P] Review and refine error messages to match the validation behavior from the specification
- [ ] T029 Run the quickstart validation scenarios against the running application
