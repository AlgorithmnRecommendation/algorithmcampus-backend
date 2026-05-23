# API Design Principles Reference

Use this reference when reviewing or changing API design. Apply only the sections relevant to the code in front of you.

## Design Goal

An API design is healthy when responsibilities are easy to name, behavior lives near the data and invariants it protects, dependencies point inward toward stable domain concepts, and HTTP/database/framework details do not leak across the whole system.

## Responsibility Boundaries

### Controller

Controllers should:

- Translate HTTP requests into application input.
- Delegate use cases to services or application handlers.
- Return response DTOs and status codes.
- Keep business decisions out of request handlers.

Avoid:

- Business rule branching in controller methods.
- Repository calls from controllers.
- Entity mutation logic in controllers.
- Repeated validation logic that belongs in DTO validation or domain rules.

### Service or Application Layer

Services should:

- Coordinate one use case.
- Load required domain objects.
- Call domain behavior or policies.
- Manage transaction boundaries when the framework expects it.
- Convert domain outcomes into application-level responses or errors.

Avoid:

- God services that own unrelated workflows.
- Services that duplicate domain invariants.
- Methods with many primitive parameters instead of request objects or domain value objects.
- Direct HTTP framework types in service signatures unless the project explicitly uses that style.

### Domain Model

Domain objects should:

- Protect invariants.
- Expose behavior that changes their own state.
- Use meaningful names for domain operations.
- Keep persistence annotations acceptable if the project uses JPA, but avoid making persistence concerns the only design driver.

Avoid:

- Public setters that allow invalid state.
- Anemic objects where all behavior lives in services.
- Bidirectional relationships or inheritance used without a clear domain reason.
- Domain classes depending on controllers, DTOs, or HTTP types.

### Repository

Repositories should:

- Encapsulate persistence queries.
- Return domain entities, projections, or persistence-specific DTOs according to project convention.
- Hide query details from services.

Avoid:

- Business rule decisions inside repositories.
- Repositories returning transport DTOs unless the project intentionally uses read-model projections.
- Query method names that encode too much workflow behavior.

### DTO and API Contract

DTOs should:

- Represent request and response contracts explicitly.
- Keep entity internals from leaking to clients.
- Own syntactic validation such as required fields, size limits, format, and simple range checks.

Avoid:

- Returning JPA entities directly from controllers.
- Reusing request DTOs as response DTOs when fields or semantics differ.
- DTOs that expose server-managed fields as client input.
- API responses that require clients to understand internal enum or persistence names unless they are intentionally public contract values.

## SOLID Checklist

### Single Responsibility Principle

Ask:

- Can this class be described with one responsibility?
- Would two unrelated feature changes modify the same class?
- Does the method combine validation, persistence, mapping, authorization, and business rules?

Improve by:

- Splitting unrelated use cases into separate services or methods.
- Moving mapping to a boundary helper only when mapping is repeated or complex.
- Moving invariant checks into domain methods or policies.

### Open/Closed Principle

Ask:

- Will a new rule or type require editing a long conditional chain?
- Is there a stable abstraction already present in the project?

Improve by:

- Using polymorphism, strategy, or enum behavior when variation is real and repeated.
- Avoiding premature abstractions for one-off branches.

### Liskov Substitution Principle

Ask:

- Does a subclass reject behavior promised by the parent?
- Is inheritance used only for code reuse?

Improve by:

- Replacing fragile inheritance with composition.
- Keeping interfaces small and behaviorally consistent.

### Interface Segregation Principle

Ask:

- Are callers forced to depend on methods they never use?
- Is an interface mirroring a class without a real boundary?

Improve by:

- Splitting interfaces by client needs.
- Avoiding interfaces when there is one implementation and no meaningful dependency boundary.

### Dependency Inversion Principle

Ask:

- Do high-level policies depend on low-level framework or infrastructure details?
- Can the use case be tested without web or database plumbing?

Improve by:

- Depending on abstractions at true volatility points.
- Keeping framework types near adapters and boundaries.
- Using constructor injection consistently.

## API Design Checks

### Contract Clarity

- Use endpoint names and HTTP methods that match resource semantics.
- Make request and response shapes explicit.
- Keep status codes consistent with project conventions.
- Avoid changing public field names or meanings during internal refactors.

### Validation Ownership

- Put syntax and shape validation on request DTOs.
- Put business invariants in domain objects or domain policies.
- Put cross-aggregate or use-case validation in services.
- Do not silently ignore invalid input.

### Exception Design

- Throw domain or application exceptions that communicate the failing rule.
- Map exceptions to API responses in a centralized handler when the project has one.
- Include failure-capture messages useful enough for debugging.
- Avoid catching and ignoring exceptions.
- Avoid leaking stack traces, SQL details, or internal class names in public responses.

### Mapping

- Keep mapping explicit and readable.
- Avoid letting response DTOs pull data through lazy entity graphs unexpectedly.
- Avoid large mapping frameworks unless already used or mapping volume justifies them.
- Keep entity-to-response decisions at the API/application boundary.

## Common Smells

- God controller: endpoint methods contain business rules, persistence calls, and mapping logic.
- God service: one service owns many unrelated workflows or domain concepts.
- Feature envy: a method mostly reads another object's data to decide behavior that belongs there.
- Data clumps: repeated parameter groups should become request DTOs or value objects.
- Primitive obsession: strings and numbers represent domain concepts with validation rules.
- Shotgun surgery: one behavior change requires edits across many classes.
- Inappropriate intimacy: layers know each other's internal details.
- Refused bequest: subclasses inherit methods or state they cannot validly support.
- DTO/entity leakage: persistence entities become public API contracts.

## Refactoring Heuristics

- Prefer extracting a named method before extracting a new class.
- Extract a class when a responsibility has its own data, rules, lifecycle, or tests.
- Introduce a value object when a primitive carries domain meaning and validation.
- Introduce a policy or strategy when a rule varies by type, tenant, role, or state and is likely to grow.
- Keep transaction boundaries around a complete use case.
- Preserve naming conventions already used in the codebase.

## Verification

Before changes:

- Compile if feasible.
- Check tests around the touched API.
- Identify whether the change affects public contract, persistence, or only internal design.

After changes:

- Run focused tests for touched behavior.
- Run full verification when the blast radius is broad.
- Manually inspect generated Swagger/OpenAPI output if the change affects API documentation.
- Report any command that could not run and why.
