---
name: unit-test
description: Use this skill when writing or reviewing unit tests.
---

# Instructions

- AssertJ > junit assertions
- Nest test classes per method
- DTO mapping tests: use recursive AssertJ comparison (catch unmapped fields)
- Prefer fakes over Mockito
- Avoid integration tests where not needed
- Use ObjectMother to create test instances

## uag-applikasjon
- API test: `@WebMvcTest` + extend `AbstractApiTest`
- Integration test: `@UagRepositoryTest`

## truls-kvern
- Integration test: `@TrulsSpringBootTest`