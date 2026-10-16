# Oppgaver: Tester som tåler endring

Domenet er en enkel nettbutikk med ordre, kunder, frakt og bonuspoeng.

Hver oppgave ligger i sin egen pakke, har sin egen `README.md` og kan løses uavhengig av de andre:

- Produksjonskode: `src/main/kotlin/com/example/testkursignite/<oppgave>/`
- Oppgavetekst og testen du skal skrive om: `src/test/kotlin/com/example/testkursignite/<oppgave>/`
- Løsningsforslag: `src/test/kotlin/com/example/testkursignite/<oppgave>/solution/` (ikke kikk før du har prøvd selv)

Alle testene er grønne før du starter, og de skal fortsatt være grønne når du er ferdig.

Kjør testene med `./mvnw test` eller fra IDE-en.

| Oppgave | Tema |
|---|---|
| [Oppgave 1](src/test/kotlin/com/example/testkursignite/exercise1/README.md) | Testdata (Object Mother) |
| [Oppgave 2](src/test/kotlin/com/example/testkursignite/exercise2/README.md) | Fake i stedet for stubbing |
| [Oppgave 3](src/test/kotlin/com/example/testkursignite/exercise3/README.md) | Sjekk tilstand, ikke interaksjon |
| [Oppgave 4](src/test/kotlin/com/example/testkursignite/exercise4/README.md) | Demo: refaktorering |
| [Bonus 1](src/test/kotlin/com/example/testkursignite/exercise5_bonus/README.md) | Deterministiske tester (Clock) |

Oppgave 1–3 har også en liten bonusoppgave nederst i sin README.

