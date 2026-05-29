# Code Review Reference - DDD / JPA / SOLID Focus

Use this reference when reviewing Spring Boot and JPA backend code. Apply only the checks relevant to the code under review.

## Review Priorities

1. Responsibility separation and SOLID principles
2. Rich domain model direction and anemic domain model risks
3. Service layer bloat
4. FK-centric design and ID-driven flow
5. Repository and aggregate boundary design
6. Appropriate JPA usage
7. Tests that verify domain rules
8. Naming, readability, and duplication

## 1. SOLID Principles

Check whether:

- One class has too many reasons to change.
- A service handles orchestration plus all business decisions.
- Domain logic can move to the object that owns the state or invariant.
- Interface and implementation separation is meaningful.
- The structure can support extension without repeatedly editing long conditional blocks.
- Dependency direction flows from application policy toward details through clear boundaries.

Flag especially:

- One service owns creation, participation, withdrawal, validation, save ordering, and response assembly.
- A service becomes the object that makes every decision.
- State-change rules exist only as service-level `if/else` branches.

## 2. Anemic Domain Model

Check whether:

- Entities are only field containers with getters and setters.
- Core rules and state changes could live inside entities or domain objects but remain in services.
- The entity can explain who may change its state and under what conditions.

Problem signals:

- Entity behaves like a data container.
- All validation happens in the service layer.
- Domain rules are scattered across multiple services.
- Values are injected through setters instead of named state-change methods.

Prefer:

- Named behavior methods on entities for meaningful state transitions.
- Value objects for concepts with validation or domain meaning.
- Domain services only for rules that do not naturally belong to one entity.

## 3. Service Layer Bloat

Application services should coordinate use cases. Treat the following as bloat signals:

- One method handles lookup, validation, state change, persistence, and response assembly.
- Service methods are long and branch-heavy.
- A service directly judges rules for several entities.
- A service explains an entity's internal rules from the outside.

When suggesting improvements, identify which responsibility can move:

- Entity: state transition owned by one aggregate/entity.
- Value object: primitive value with domain meaning and validation.
- Domain service: domain rule spanning multiple objects.
- Application service: use-case orchestration and transaction boundary.
- Infrastructure layer: external API, lock, cache, message broker, retry, persistence detail.

## 4. FK-Centric Design

Check whether:

- Services and repositories manually combine IDs such as `groupId`, `groupMemberId`, or `groupChallengeId`.
- ID passing is more central than object collaboration.
- Entity relationships are treated only as numeric foreign keys.

Flag especially:

- A service calls several repositories to manually assemble foreign-key flow.
- Entities cannot collaborate and are connected externally by IDs.
- Table structure dominates the code more than object behavior.

Prefer:

- Object references and aggregate boundaries where they clarify behavior.
- Repository methods expressed in domain/use-case language.
- IDs at API boundaries, not throughout all domain behavior.

## 5. Repository Design

Check whether:

- Repositories are only `one table = one repository` accessors.
- Repository methods represent domain lookup/save needs.
- Aggregate boundaries are considered.
- A service coordinates several repositories to maintain one aggregate's consistency.

Prefer:

- Repositories per aggregate root where the project structure supports it.
- Query methods that reflect domain intent.
- Read models or projections for query-heavy response assembly when appropriate.

## 6. JPA Usage

Check whether:

- JPA is used only as ID-based CRUD.
- Object graphs are used where they improve clarity.
- Associations and dirty checking are used appropriately.
- Services unnecessarily manage setters and save order.
- Persistence context benefits are ignored.

Flag especially:

- Entities are treated like DB rows rather than objects.
- Code passes IDs even when an associated object is already available.
- Every field update is manually pushed through setters.
- The design relies heavily on save-order control instead of aggregate behavior.

Do not overcorrect:

- Avoid eager relationships without a performance reason.
- Avoid loading large object graphs just to look more object-oriented.
- Respect existing fetch strategy and transaction conventions.

## 7. Domain and Infrastructure Boundaries

Do not suggest moving infrastructure concerns into domain objects.

Keep these out of domain entities:

- `ResponseStatusException`
- `DataIntegrityViolationException`
- HTTP status codes
- Persistence retry logic
- External API calls
- Message broker, lock, cache, and transaction implementation details

Prefer:

- Domain exceptions or domain result types for business-rule failures.
- Web exception mapping at controller advice or API boundary.
- Infrastructure exceptions handled in application or infrastructure layers.

## 8. Test Quality

Check whether:

- Tests only verify call order or mock interactions.
- Domain rules are directly tested.
- Tests express "who performs what action in which state, and what changes".
- Service tests mock so much that real rule verification is weak.

Prefer:

- Entity/domain object behavior tests for core rules.
- Use-case/service tests for orchestration and transaction behavior.
- Infrastructure tests only where persistence mapping or query behavior matters.

## Anti-Patterns To Flag

- Entity as getter/setter-only data container.
- Service handles creation, participation, withdrawal, validation, persistence, and response assembly.
- State-change rules exist only outside the entity.
- Use case flows through manual FK value combination.
- Repository is only a table accessor.
- JPA is used only as a SQL execution replacement.
- Domain object throws web or infrastructure exceptions.
- Tests focus only on mock call counts.

## Review Wording

Use practical phrasing:

- "이 구조는 규칙이 늘어날수록 서비스가 계속 비대해질 가능성이 큽니다."
- "이 검증은 엔티티가 자기 상태 전이를 보호하는 메서드로 옮길 수 있습니다."
- "다만 현재 기능 규모에서는 별도 도메인 서비스까지 분리하는 것은 과할 수 있습니다."
- "ID는 컨트롤러/요청 경계에서는 자연스럽지만, 도메인 내부 흐름까지 ID 중심이 되면 객체 협력이 약해집니다."
