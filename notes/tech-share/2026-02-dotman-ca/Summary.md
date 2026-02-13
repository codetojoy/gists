# Dotmanca - Project Summary

## Overview

**The Official Dotman Website** (v4.0.0) is a Django-based web application for managing and displaying Dotman Comics content. It provides pages for comic arcs/issues, image galleries, characters, places, and news updates. The project is authored by Evan Porter and licensed under MIT.

## Tech Stack

| Layer          | Technology                                   |
|----------------|----------------------------------------------|
| Language       | Python 3.14                                  |
| Framework      | Django 5.2                                   |
| Database       | PostgreSQL 16                                |
| Object Storage | AWS S3 (Minio for local dev)                 |
| WSGI Server    | Gunicorn                                     |
| Containerization | Docker (multi-stage build)                 |
| Package Manager | uv (Astral)                                 |
| Linter         | Ruff                                         |
| Test Framework | pytest + pytest-django                       |
| Password Hashing | Argon2                                     |

## Project Structure

```
src/
  config/           Django settings (base, local, production, test) and URL routing
  dotmanca/         Core app: custom User model, storage backends, base templates
  comics/           Comic arcs and issues with page navigation
  gallery/          Image galleries with signal handlers
  characters/       Character roster
  places/           Story world locations
  news/             News and updates
  main/             Homepage
```

## Django Apps

- **comics** - Arc and Issue models, page-by-page reading view with next/previous navigation
- **gallery** - Gallery and GalleryImage models, linked to comic issues
- **characters** - Character profiles with images
- **places** - Location directory
- **news** - News articles with markdown support
- **main** - Homepage landing view
- **dotmanca.users** - Custom User model extending Django's AbstractUser

## Infrastructure

- **Dockerfile** - Multi-stage build: builder stage compiles dependencies (including argon2 native libs), runtime stage runs as non-root user (UID 999) on port 8080
- **docker-compose.yml** - Four services: webapp, postgres, s3 (Minio), and a createbucket init container
- **docker-entrypoint.sh** - Waits for PostgreSQL, runs migrations, collects static files, starts Gunicorn with 2 workers

## CI/CD (GitHub Actions)

### Workflows

1. **CI CD** (`ci-cd.yml`) - Runs on every pull request
   - **pytest** job: installs project with uv, runs the test suite
   - **ruff** job: runs `ruff check --diff .` for linting

2. **CodeQL** (`codeql.yml`) - Runs on push/PR to main + weekly schedule
   - Static security analysis for Python code
   - Uses harden-runner with strict egress blocking

3. **Dependency Review** (`dependency-review.yml`) - Runs on pull requests
   - Scans for known-vulnerable dependencies in changed manifests
   - Uses harden-runner in audit mode (not yet blocking)

4. **Scorecards** (`scorecards.yml`) - Runs on push to main + weekly schedule
   - OpenSSF supply-chain security scoring
   - Uploads SARIF results to GitHub code scanning dashboard
   - Uses harden-runner with strict egress blocking

### Dependabot

- **uv packages**: weekly
- **GitHub Actions**: daily
- **Docker images**: daily

## Security Posture

- Harden-runner on security workflows with egress blocking
- SHA-pinned actions in security workflows (CodeQL, Scorecards, Dependency Review)
- Non-root Docker user
- Locked dependencies (`uv.lock`)
- Argon2 password hashing
- Bleach HTML sanitization
- `persist-credentials: false` on Scorecards checkout

---

## Suggestions for Improvement

### GitHub Actions - High Priority

1. **Pin action versions by SHA in `ci-cd.yml`**
   The main CI workflow uses tag-based references (`actions/checkout@v6.0.1`, `actions/setup-python@v6`, `astral-sh/setup-uv@v7`) while the security workflows correctly use SHA-pinned versions. Tags are mutable and can be overwritten, which is a supply-chain risk. All workflows should use SHA-pinned actions for consistency and security.

2. **Finish the dependency-review egress policy TODO**
   `dependency-review.yml` line 20 has `egress-policy: audit` with a TODO comment to change it to `block`. After sufficient runs to identify required endpoints, this should be switched to `block` with an explicit `allowed-endpoints` list, matching the other security workflows.

3. **Add coverage reporting to CI**
   The project has `coverage` and `django-coverage-plugin` as test dependencies, plus a `[tool.coverage.run]` config, but the CI pipeline just runs `uv run pytest` without coverage. Consider running `uv run coverage run -m pytest && uv run coverage report --fail-under=80` (or similar threshold) to enforce coverage standards, and optionally upload coverage reports as artifacts or to a service like Codecov.

4. **Add `ruff format --check` to the lint job**
   The CI only runs `ruff check` (linting rules) but not `ruff format --check` (formatting). Since ruff replaces both flake8 and black, adding format checking ensures consistent code style. This also makes the leftover `[tool.black]` and `[tool.isort]` configs in `pyproject.toml` unnecessary (see below).

5. **Add concurrency controls to workflows**
   When multiple commits are pushed to a PR in quick succession, redundant workflow runs pile up. Adding a concurrency group cancels superseded runs:
   ```yaml
   concurrency:
     group: ${{ github.workflow }}-${{ github.ref }}
     cancel-in-progress: true
   ```

6. **Add a PostgreSQL service container to the test job**
   The test job runs without a database service, meaning tests likely use SQLite (via the test settings). Running tests against PostgreSQL in CI would catch database-specific issues (e.g., JSONField behavior, migration problems). Add a service container:
   ```yaml
   services:
     postgres:
       image: postgres:16
       env:
         POSTGRES_PASSWORD: test
         POSTGRES_USER: test
         POSTGRES_DB: test
       ports:
         - 5432:5432
       options: >-
         --health-cmd pg_isready
         --health-interval 10s
         --health-timeout 5s
         --health-retries 5
   ```

### GitHub Actions - Medium Priority

7. **Add a build/push workflow for Docker images**
   There is no CD pipeline. Consider adding a workflow that builds and pushes the Docker image to a container registry (GHCR, Docker Hub, or ECR) on push to main or on tagged releases. This would enable automated deployments.

8. **Add `harden-runner` to the CI CD workflow**
   The security workflows all use `step-security/harden-runner`, but the main CI workflow does not. For consistency and protection against exfiltration during test/lint runs, add it there too.

9. **Add a workflow for running migrations check**
   Django has `python manage.py makemigrations --check --dry-run` which fails if there are model changes without corresponding migrations. Adding this as a CI step catches forgotten migrations before merge.

10. **Set explicit `permissions` on the CI CD workflow**
    The security workflows declare minimal permissions, but `ci-cd.yml` uses the default (which may be overly broad depending on repo settings). Add `permissions: contents: read` at the workflow level.

### Configuration Cleanup

11. **Remove stale `[tool.black]` and `[tool.isort]` configs from `pyproject.toml`**
    The project uses ruff for linting but still has `[tool.black]` (targeting Python 3.11) and `[tool.isort]` config blocks. These are unused and the Python version target is out of date. Remove them.

12. **Upgrade `docker-compose.yml` to modern format**
    The file uses `version: "3.7"` which is deprecated in modern Docker Compose. The `version` key can simply be removed - Docker Compose V2 ignores it.

### General

13. **Add `ruff format` to the README's linting instructions**
    The README documents `uv run ruff check --diff .` but doesn't mention formatting. If format checking is added to CI, the README should include `uv run ruff format --check .` as well.

14. **Consider adding a `Makefile` or `justfile`**
    Common commands (run server, run tests, lint, format, migrate) are documented in the README but not scripted. A Makefile or justfile would provide a consistent, discoverable interface for contributors.
