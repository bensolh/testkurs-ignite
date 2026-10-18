# Oppgave 5 – Rik domenemodell

- **Test:** `ReturnServiceTest.kt` (og `OrderMother.kt`)
- **Produksjonskode:** `src/main/kotlin/com/example/testkursignite/exercise5/ReturnService.kt`
- **Løsningsforslag:** `solution/` (ikke kikk før du har prøvd selv)

En kunde kan returnere en ordre innen 30 dager etter levering. Testene går gjennom `ReturnService` og bruker Mockito.

1. Les `Order`. Hvor bor returregelen, og hvilke metoder finnes for å endre tilstanden til en ordre?
2. Skriv om testene slik at de tester regelen der den bor.
3. Skriv om `OrderMother` slik at den lager ordre med domenemetodene (`ship()`, `deliver()`, `cancel()`) i stedet for å ta inn status og datoer.
4. Ser du noen ordre i «før»-testene som aldri kunne ha oppstått i virkeligheten?

## Bonus

Hva med `ReturnService`? Trenger den egne tester nå, og i så fall hvilke?

**Forrige:** [Oppgave 4](../exercise4/README.md) · **Neste:** [Bonus 1](../exercise6_bonus/README.md)

