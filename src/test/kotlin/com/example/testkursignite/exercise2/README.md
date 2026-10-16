# Oppgave 2 – Fake i stedet for stubbing

- **Test:** `OrderServiceTest.kt`
- **Produksjonskode:** `src/main/kotlin/com/example/testkursignite/exercise2/OrderService.kt`
- **Løsningsforslag:** `solution/` (ikke kikk før du har prøvd selv)

`OrderService.cancel()` kansellerer en ordre. Testene bruker Mockito til å stubbe repositoryet.

1. Lag et `FakeOrderRepository` som lagrer ordre i et `Map`.
2. Skriv om testene til å bruke faken.
3. Hva bør testene sjekke for å vite at kanselleringen faktisk «holder»?

## Bonus

Se på «før»-testene. Hvilke endringer kan du gjøre i produksjonskoden uten at noen av dem brekker?

**Forrige:** [Oppgave 1](../exercise1/README.md) · **Neste:** [Oppgave 3](../exercise3/README.md)

