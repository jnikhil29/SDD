# Data Model: Client CRUD API

## Entity: Client

| Field | Type | Constraints | Notes |
|---|---|---|---|
| id | Long | Primary key, generated | Internal identifier |
| coreClientId | String | Not null, unique | Used as the stable lookup key for updates and deletes |
| clientName | String | Not null, unique | Must be unique across all clients |
| onboardingDate | LocalDate | Not null | Represents the client onboarding date |
| createdAt | Instant | Not null | Audit timestamp |
| updatedAt | Instant | Not null | Audit timestamp |

## Validation Rules

- coreClientId is required and must be unique.
- clientName is required and must be unique.
- onboardingDate is required and must be a valid date.
- Updates and deletes are resolved using coreClientId, and coreClientId is not modified by update requests.

## Relationships

- No relationships to other entities are required for this feature.
