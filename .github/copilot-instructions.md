## Language
- Answer in the same language as the prompt was written
- All code should be in English

## Purpose of the repo
- This repo is to be used for a course on writing good unit tests

## Scope and complexity
- Code is to be read by graduates - complexity must be tailored accordingly
- Code must compile
- The program will not be used; this is only for testing purposes. Any 3rd party integrations etc. can therefore be dummies
- Keep the code small enough to fit on a PPT slide where possible
- Keep the production code as simple as possible. Skip logging, edge-cases, custom exceptions, and layers of abstraction unless the exercise is about them (or you're told otherwise)
- Each exercise should be fairly short and to the point. I fit would take longer than 5-10 minutes, cut it down.
- Stick to basic Kotlin. Avoid coroutines, `inline`/`reified`, DSLs, lots of extension functions, chained scope functions and sealed hierarchies unless they're needed. Graduates may know Java better than Kotlin.

## Architecture
- Prefer "rich domain model" (Helper functions on domain objects where applicable and relevant). Not directly scope for the course, but nice to be able to point to at least one such example and how it makes test easier to make, for instance.

## The "bad" tests
- "Before" tests must compile and pass. They're badly written, not broken
- They should be bad in a realistic way. Write them in a way real teams might do
- Don't fix any "before" tests unless asked; They exist to serve as an example
- Avoid comments or TODOs that give away the answer/improvement.
- Exercise 4 needs a seam for refactoring. Refactoring the implementation must actually break the mock test and leave the fake test passing. Check that this really happens.

## Tooling
- Mockito
- JUnit 5, but prefer AssertJ
- Unit tests don't use Spring. No @SpringBootTest or @MockBean. Use plain constructor injection
- Keep each exercise in its own package with no shared helpers between exercises, so they stay independent

## Fakes and determinism
- Keep fakes simple. An in-memory Map or List, with state exposed publicly (e.g. sentEmails). No fake frameworks
- No hidden non-determinism. Never use LocalDateTime.now(), Random or UUID.randomUUID() directly unless the exercise is about that

## Meta
- Ask before adding new files or modules outside what was requested