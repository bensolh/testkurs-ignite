# Oppgave 3 – Sjekk tilstand, ikke interaksjon

- **Test:** `ShippingServiceTest.kt`
- **Produksjonskode:** `src/main/kotlin/com/example/testkursignite/exercise3/ShippingService.kt`
- **Løsningsforslag:** `solution/` (ikke kikk før du har prøvd selv)

`ShippingService.ship()` sender en ordre og varsler kunden på e-post. Testene bruker `verify(...)`.

1. Lag en `FakeEmailSender` som tar vare på e-postene som er sendt (for eksempel i `sentEmails`).
2. Skriv om testene til å sjekke tilstanden i faken i stedet for å verifisere kall.
3. Dekker testene all oppførselen til `ship()`?

## Bonus

Se på «før»-testene. Hvilke endringer kan du gjøre i produksjonskoden uten at noen av dem brekker?

**Forrige:** [Oppgave 2](../exercise2/README.md) · **Neste:** [Oppgave 4](../exercise4/README.md)

