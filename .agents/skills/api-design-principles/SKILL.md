---
name: api-design-principles
description: Use when reviewing, designing, improving, or refactoring API design for clear object-oriented boundaries, SOLID principles, responsibility separation, domain/service/controller layering, DTO and entity boundaries, request/response contracts, validation ownership, exception design, dependency direction, and maintainable Java or backend code structure.
---

# API Design Principles

Review and improve API design with emphasis on object-oriented design quality, SOLID principles, and clear responsibility separation.

## Core Rules

- Preserve behavior unless the user explicitly asks for a redesign.
- Prefer the project's existing architecture, naming, framework conventions, and package boundaries.
- Keep API contracts stable unless the requested task includes contract changes.
- Separate transport concerns from business rules: controllers adapt HTTP, services coordinate use cases, domain objects protect invariants, repositories handle persistence.
- Avoid broad refactors. Apply the smallest design change that improves clarity, responsibility, or maintainability.

## Workflow

1. Establish the current behavior and API contract.
   - Identify endpoints, request/response DTOs, validation rules, services, domain objects, repositories, and exception flows.
   - For Java projects, compile before design changes when feasible: use `./mvnw compile`, `mvn compile`, `./gradlew compileJava`, or the repository's existing build command.

2. Read the detailed reference when doing a design review or refactor.
   - Use `references/api-design-principles.md` for SOLID, responsibility boundaries, layering, DTO/entity separation, validation, exception, and code-smell checks.

3. Assess responsibilities before editing.
   - Ask what each class owns, what it knows too much about, and whether changes would concentrate or reduce coupling.
   - Flag design smells before changing code: God service, anemic domain, controller business logic, DTO leakage, feature envy, duplicated validation, persistence logic outside repositories.

4. Apply focused improvements.
   - Move behavior toward the object that owns the data or invariant.
   - Introduce interfaces only when they isolate real volatility or match existing project patterns.
   - Prefer composition and dependency injection over inheritance or static coupling.
   - Keep DTO mapping explicit and close to the application boundary unless the project has an established mapper pattern.

5. Verify.
   - Run the narrowest useful tests first, then the repository's normal verification command when available.
   - If verification cannot run, report the exact blocker and the remaining design risk.

## Review Output

When reviewing without editing, lead with actionable findings ordered by severity. Include file and line references when available, explain the design risk, and propose a concrete improvement. If no issues are found, say so and mention any verification gaps.

When editing, summarize the responsibility changes, contract impact, and verification result.
