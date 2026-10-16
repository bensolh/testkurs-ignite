# Oppgave 4 – Demo: refaktorering

- **Tester:** `LoyaltyServiceTest.kt` (mock) og `solution/LoyaltyServiceTest.kt` (fake)
- **Produksjonskode:** `src/main/kotlin/com/example/testkursignite/exercise4/LoyaltyService.kt`

`LoyaltyService.pointsFor()` regner ut bonuspoeng for en kunde. Det finnes én test med mock og én med fake.

1. Kjør begge testene. Begge er grønne.
2. Refaktorer `LoyaltyService` slik at den bruker `repository.findByCustomerId(customerId)` i stedet for å hente alle ordre og filtrere selv.
3. Kjør testene igjen. Hvilken test brekker, og hvorfor? Har oppførselen endret seg?

**Forrige:** [Oppgave 3](../exercise3/README.md) · **Neste:** [Bonus 1](../exercise5_bonus/README.md)

