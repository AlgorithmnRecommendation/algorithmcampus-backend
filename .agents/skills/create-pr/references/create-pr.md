# Create PR Reference

Use this reference for the detailed PR creation and update workflow.

## Prerequisites

- Work inside a Git repository.
- Use an authenticated `gh` CLI.
- Use `dev` as the base branch unless the user explicitly requests another base.

## Phase 0: Smart Commits

1. Run `git status --short`.
2. If no uncommitted changes exist, skip to branch handling.
3. If uncommitted changes exist:
   - Run `git diff`.
   - Run `git diff --cached`.
   - Classify changes by logical unit.
   - Show the proposed split before committing.

Example:

```text
커밋을 다음과 같이 분리합니다:
1. feat(user): 로그인 폼 컴포넌트 추가 - src/features/login/...
2. style(user): 로그인 페이지 스타일 조정 - src/pages/login/...
3. test(user): 로그인 폼 테스트 추가 - src/features/login/__tests__/...
```

After user confirmation:

- Stage only the files for each logical unit.
- Commit each unit separately.
- Use Conventional Commit format: `type(scope): description`.
- Keep commit messages concise and behavior-focused.

## Phase 1: Branch Handling

1. Run `git branch --show-current`.
2. If the current branch is `dev`:
   - Analyze changed files and commits.
   - Propose a branch name such as `feat/add-login-form`, `fix/modal-zindex`, `docs/update-api-docs`, or `chore/update-ci`.
   - Ask the user to confirm or edit the branch name.
   - Run `git checkout -b <branch-name>`.
3. If the current branch is not `dev`, continue on the current branch.

## Phase 2: Existing PR Detection

Run:

```bash
gh pr list --head <current-branch> --json number,title,body,url
```

If a PR exists, follow Existing PR Update. If not, follow New PR Creation.

## New PR Creation

1. Analyze changes:

```bash
git log dev..HEAD --oneline
git diff dev...HEAD
```

2. Read `.github/pull_request_template.md` if present.

3. Choose label:

- `bug`: bug fix
- `enhancement`: new feature
- `documentation`: documentation
- `dependencies`: dependency update
- `github_actions`: CI/CD changes

4. Build a Korean PR title.

- Preserve the conventional type when useful.
- Keep code identifiers, endpoint names, and library names in English.
- Prefer concrete behavior over vague wording.

Examples:

- `feat(user): 사용자 프로필 조회 API 추가`
- `fix(auth): 토큰 만료 처리 오류 수정`
- `docs(api): Swagger 문서 갱신`

5. Select reviewers.

- Read `.agents/skills/shared/config/discord-members.json` if it exists. Keys are GitHub usernames.
- Run `gh api user --jq '.login'` to identify the current user.
- Exclude the current user from reviewer candidates.
- Show the candidate list and ask the user to choose a reviewer.
- If no reviewer can be determined, ask the user.

6. Push:

```bash
git push -u origin <current-branch>
```

7. Create PR:

```bash
gh pr create --base dev --title "<title>" --body "<body>"
```

8. Edit metadata:

```bash
gh pr edit <number> --add-label <label> --add-assignee <username> --add-reviewer <reviewer>
```

## Existing PR Update

1. Push new commits:

```bash
git push
```

2. Inspect existing PR:

```bash
gh pr view --json title,body,number,url
```

3. Determine newly added commits when possible.

- If the last PR commit is known, inspect `git log <last-pr-commit>..HEAD --oneline`.
- Otherwise summarize the full branch diff against `dev`.

4. Update PR title/body in Korean using the same rules as new PR creation:

```bash
gh pr edit <number> --title "<updated-title>" --body "<updated-body>"
```

5. If images are needed, only use GitHub-hosted attachment URLs.

## PR Body Guidelines

Prefer a concise body that matches the repository template. If there is no template, use:

```markdown
## 변경 사항
- 

## 확인 방법
- 

## 참고 사항
- 
```

Mention tests or verification commands actually run. Do not claim unrun checks.

## Image Rules

- Do not commit image files solely for PR display.
- Do not use local file paths.
- Do not use repository blob/raw URLs for screenshots.
- Upload images through the GitHub PR body editor or comment box.
- Use only the resulting GitHub-hosted attachment URL.
- Verify that the image renders in the PR UI.

## Error Handling

| Situation | Handling |
| --- | --- |
| SAML SSO error | Show the authorization URL and ask the user to authenticate. |
| No commits ahead of base | Explain that there is no PR content to create. |
| Branch already has PR | Follow Existing PR Update. |
| Push rejected | Fetch status, explain divergence, and ask before rebasing or force-with-lease. |
| Reviewer unknown | Ask the user to choose or provide reviewer usernames. |
