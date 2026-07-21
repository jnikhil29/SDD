# Quickstart: Client CRUD API

## Prerequisites

- Java 17+
- Maven or Gradle
- PostgreSQL instance running locally

## Setup

1. Create a PostgreSQL database for the service.
2. Configure database connection properties in the application configuration.
3. Start the Spring Boot application.

## Validation scenarios

1. Create a client with a valid payload and basic auth credentials.
2. Attempt to create a client with missing fields and confirm that a validation error is returned.
3. Update an existing client by coreClientId and verify the change is persisted.
4. Attempt to update or delete a client with a non-existent coreClientId and confirm the not-found validation message.
5. Attempt to reuse an existing client name during update and confirm the duplicate-name validation message.
