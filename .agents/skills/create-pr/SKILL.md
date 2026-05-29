---
name: create-pr
description: Use when creating or updating a GitHub Pull Request from the current repository, especially when the user says "PR 올려줘", "PR 생성", "create PR", "/create-pr", or "/pr". Handles smart commits from uncommitted changes, branch creation when currently on dev, existing PR updates, Korean PR title/body writing, labels, assignees, reviewers, and GitHub CLI workflows.
---

# Create PR

Create or update a GitHub Pull Request while organizing commits, branches, labels, assignees, reviewers, and PR text.

## Language Policy

- Write PR titles and bodies in Korean by default.
- Keep established English technical terms, library names, API paths, code identifiers, labels, and domain terms.
- Rewrite English commit messages into natural Korean PR title/body text.
- Apply the same language policy when updating an existing PR.

## Operating Rules

- Use `git` and `gh` for repository and GitHub operations.
- Ask before committing when grouping uncommitted changes into logical commits.
- Ask before creating a new branch name when the current branch is `dev`.
- Ask for reviewers when reviewer selection is unclear.
- Never commit image files just to show them in a PR body.
- Do not use local file paths, repository blob URLs, or raw repository URLs for PR body images.
- If images are required, use GitHub-hosted attachment URLs and verify they render in the PR.

## Workflow

1. Inspect repository state.
   - Run `git status`.
   - If there are uncommitted changes, inspect `git diff` and `git diff --cached`.
   - Group changes into logical commit units such as feature, fix, test, docs, refactor, style, config, or CI.
   - Show the proposed commit split to the user and get confirmation before staging and committing.
   - Use Conventional Commit format: `type(scope): description`.

2. Check the current branch.
   - Run `git branch --show-current`.
   - If the branch is `dev`, propose a branch name based on the changes, ask for confirmation, then create it.
   - If already on a feature/fix/docs branch, continue on that branch.

3. Check for an existing PR.
   - Run `gh pr list --head <current-branch> --json number,title,body,url`.
   - If a PR exists, update it after pushing new commits.
   - If no PR exists, create a new PR.

4. Prepare PR content.
   - Compare against `dev` using `git log dev..HEAD --oneline` and `git diff dev...HEAD`.
   - Read `.github/pull_request_template.md` if present.
   - Use `references/create-pr.md` for labels, reviewer selection, title/body construction, update behavior, and error handling.

5. Push and create or update.
   - Push the current branch to origin.
   - Create a new PR with base `dev`, or update the existing PR title/body.
   - Add label, assignee, and reviewer when available.

6. Report the result.
   - Include PR URL, title, label, assignee, reviewer, and whether it was created or updated.

## Output Format

Use this final report shape:

```text
PR 생성 완료

PR: <url>
제목: <title>
라벨: <label>
담당자: <assignee>
리뷰어: <reviewer>
```

For existing PRs, use `PR 업데이트 완료`.
