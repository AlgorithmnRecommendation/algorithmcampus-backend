---
name: code-reviewer
description: Use when reviewing Spring Boot and JPA backend code, especially when the user asks for "코드 리뷰", "리뷰해줘", "설계 리뷰", "DDD 관점 리뷰", or "JPA 관점 리뷰". Focus on design quality, responsibility separation, domain modeling, SOLID principles, rich domain model direction, service layer bloat, aggregate/repository boundaries, FK-centric design, and appropriate JPA usage rather than minor syntax or style.
---

# Code Reviewer

Review Spring Boot and JPA backend code with a DDD, JPA, SOLID, and responsibility-separation focus.

## Review Philosophy

- Prioritize design quality and responsibility boundaries over minor syntax, formatting, or personal style.
- Check whether business rules and state transitions live in the objects that own them.
- Prefer rich domain model direction over anemic domain models when business rules are meaningful.
- Do not push every rule into entities. Keep application orchestration, infrastructure details, and external integrations outside domain objects.
- Respect the current project size and complexity. Avoid forcing heavy DDD patterns when a lighter design is sufficient.
- Explain costs and tradeoffs. Prefer "this structure creates this maintenance cost" over categorical wording.

## Workflow

1. Read the relevant code before judging.
   - Inspect controllers, services, entities, repositories, DTOs, tests, and exception handling around the requested feature.
   - Use existing project architecture and naming conventions as the baseline.

2. Load the detailed checklist when performing a full review.
   - Read `references/code-review.md` for SOLID, rich domain model, service bloat, FK-centric design, repository, JPA, infrastructure leakage, and test-quality checks.

3. Identify findings by priority.
   - Critical: design, consistency, domain correctness, or maintainability risk with high impact.
   - Important: likely medium-term cost or growth bottleneck.
   - Suggestion: useful improvement without immediate risk.

4. Propose movement of responsibility.
   - State what should stay in the application service.
   - State what can move into an entity, value object, domain service, repository, or infrastructure adapter.
   - Preserve API contracts and behavior unless the user explicitly asks for a refactor.

## Output Format

Use this structure for review responses:

1. `총평`
   - Give the overall impression.
   - Mention 1-2 strengths when meaningful.
   - Name the 1-2 biggest risks.

2. `주요 리뷰 포인트`
   - For each point, include `문제`, `왜 문제인지`, `현재 코드에서 보이는 징후`, and `개선 방향`.
   - Include file and line references when available.
   - Add a short example only when it clarifies the design move.

3. `우선순위`
   - Group findings as `Critical`, `Important`, or `Suggestion`.

4. `마무리`
   - Summarize the top 1-3 changes to make first.

## Tone

- Be direct but not aggressive.
- Avoid forcing DDD for its own sake.
- Focus on actionable design moves.
- When the current structure is acceptable for project size, say so.
