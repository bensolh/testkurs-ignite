# Kurs for nyansatte: Tester som tåler endring

**Varighet:** 90 minutter
**Format:** Fysisk kurs med teori og praktiske oppgaver (løses av deltakerne eller i plenum)
**Rød tråd:** Test oppførsel, ikke implementasjon.
**Språk:** Norsk, men kode på engelsk

## Forslag til tittel på oversiktsslide

1. **Tester som tåler endring** – Fakes, Object Mother og tester som sjekker *hva* som skjer, ikke *hvordan*.
2. **Slutt å mocke alt** – Hvordan skrive tester som er lette å lese, og som ikke knekker ved refaktorering.
3. **Gode tester i praksis** – Hands-on med testfakes, Object Mother og verifisering via tilstand.
4. **Fra skjøre til solide tester** – Vi skriver om ekte tester og ser forskjellen selv.
5. **Test oppførsel, ikke implementasjon** – Praktisk workshop om fakes, testdata og robuste tester.

Favoritter: nummer 1 eller 4.

## Agenda

| Tid | Del | Innhold |
|---|---|---|
| 0–5 | Intro | Hvem jeg er, målet med kurset og hvordan det er lagt opp |
| 5–15 | Hvorfor teste, og hva | Testpyramiden, og hva som kjennetegner en god test |
| 15–35 | Konsepter | Test doubles, Object Mother, verifisering via tilstand, deterministiske tester |
| 35–70 | Praktiske oppgaver | Skrive om dårlige tester til gode |
| 70–80 | Testbar kode (valgfritt) | SRP, avhengigheter inn utenfra |
| 80–90 | Oppsummering | Huskeregler, spørsmål, veien videre |

## 1. Intro (5 min)

- Hvem jeg er.
- Målet med kurset: å skrive tester som er lette å lese og som tåler refaktorering.
- Opplegget: teori → oppgaver → oppsummering.
- Oppgavene kan løses selv, eller følges i plenum.

## 2. Hvorfor teste, og hva (10 min)

- Tester som sikkerhetsnett, dokumentasjon og noe som gjør det trygt å refaktorere.
- Testpyramiden: unit / integrasjon / e2e (for eksempel Playwright) / ytelse.
    - Kurset er backend-basert, men prinsippene gjelder på alle nivåer.
- Kjernebudskap: **en god test brekker bare når oppførselen endres.**
- Mulig åpning: vis en mock-tung test og spør «Hva skjer med denne hvis vi refaktorerer?»

## 3. Konsepter (20 min)

- **Struktur:** Arrange–Act–Assert / Given–When–Then. Navngi testen etter oppførselen.
- **Test doubles:** dummy, stub, fake, mock og spy, og når hver av dem passer.
- **Object Mother / Test Data Builder:** vis bare det som er relevant for testen.
- **Verifisering via tilstand i stedet for interaksjon:** `verify(times(1), ...)` mot tilstand i fake (for eksempel `fake.antallKall` eller `fake.sendteEposter`).
- **Deterministiske tester:** tid (`Clock`), tilfeldighet og ID-er som avhengigheter.
- **Kontraktstester for fakes:** samme testsuite kjøres mot fake og ekte implementasjon, slik at faken aldri lyver.
- **Når mocks er riktig:** ved eksterne grenser vi ikke eier, eller når interaksjonen *er* selve oppførselen. Ikke vær dogmatisk.

## 4. Praktiske oppgaver (35 min)

Før/etter-par der deltakerne skriver om en dårlig test til en god. Oppgavene skal kunne gjøres uavhengig av hverandre.

| # | Før | Etter |
|---|---|---|
| 1 | Test med mye konstruksjon av testdata | Object Mother / Test Data Builder |
| 2 | Mockito-stubbing av repository | Fake-repository (in-memory) |
| 3 | `verify(times(1), someMethod)` | Assert på bieffekt i fake |
| 4 | Demo: refaktorer implementasjonen | Mock-testen brekker, fake-testen står |

### TODO

- [x] Lag konkrete cases i domenet (velg domene som passer til kurset). → Nettbutikk.
- [x] Lag oppgavene på egnet sted. → `exerciseN`-pakker, beskrevet i `OPPGAVER.md`.
- [x] Lag løsningsforslag på egnet sted. → `exerciseN/solution`-pakker under `src/test`.
- [x] Sjekk oppsettet på forhånd (bygg, IDE, kjøring av tester).
- [x] Ha bonusoppgaver klare til de som blir raskt ferdige. → `exercise5_bonus`-pakken, pluss en liten bonus nederst i README for oppgave 1–3.

### Notater til kursholder

- **Oppgave 2:** «Før»-testene sjekker bare returverdien. Fjern `repository.save(cancelled)` i `OrderService`: mock-testene er fortsatt grønne, mens fake-testen brekker.
- **Oppgave 3:** «Før»-testene sjekker aldri at ordren faktisk får status `SHIPPED`, og `verifyNoMoreInteractions` låser testen til implementasjonen.
- **Oppgave 4:** Bytt ut `findAll().filter { ... }` med `findByCustomerId(customerId)` i `LoyaltyService`. Mock-testen feiler (`expected: 100 but was: 0`) fordi Mockito returnerer tom liste for metoder som ikke er stubbet. Fake-testen står. Dette er sjekket.
- **Bonus 1 (`exercise5_bonus`):** «Før»-testen bruker `LocalDate.now()` i testen og systemklokken i produksjonskoden. Den kan feile rundt midnatt.

### Svar på bonusspørsmålene

- **Oppgave 1 – grensetilfeller for gratis frakt:** Løsningsforslaget dekker allerede 499 (frakt), 500 (gratis) og medlem (gratis). Andre gode tilfeller:
    - Flere linjer som til sammen når grensen, for eksempel `line(price = 250)` + `line(price = 250)`.
    - Antall som løfter ordren over grensen, for eksempel `line(price = 100, quantity = 5)`.
    - Medlem med tom ordre / lav sum gir fortsatt 0 i frakt.
    - Poenget: hver test blir én–to linjer med Arrange, fordi `OrderMother` fyller inn resten.
- **Oppgave 2 – endringer «før»-testene ikke fanger** (sjekket ved å kjøre testene):
    - Fjerne `repository.save(cancelled)`. Ordren blir aldri lagret som kansellert.
    - Lagre den opprinnelige ordren, `repository.save(order)`, i stedet for den kansellerte.
    - Begge fanges av løsningsforslagets `stores the order as cancelled`, fordi den sjekker tilstanden i faken.
- **Oppgave 3 – endringer «før»-testene ikke fanger** (sjekket ved å kjøre testene):
    - Returnere `order` i stedet for `shipped`. Ordren får aldri status `SHIPPED`, men e-posten sendes som før.
    - Fanges av løsningsforslagets `marks the order as shipped`.
    - Motsatt vei: `verifyNoMoreInteractions` gjør at «før»-testen brekker ved ufarlige endringer, for eksempel hvis `EmailSender` får en metode som `isAvailable()` og tjenesten begynner å kalle den før sending. Kunden mottar akkurat det samme, men testen er rød.

## 5. Testbar kode – valgfritt (10 min)

- «Vanskelig å teste er et signal om dårlig strukturert kode.»
- Gud-klasse (henter data, validerer, beregner og sender e-post) som deles opp etter SRP.
- Avhengigheter sendes inn utenfra: konstruktør-injeksjon, porter/interfaces.
- Kort pekepinn til hexagonal / clean architecture, uten å gå i dybden.

## 6. Oppsummering (10 min)

Huskeregler:

- Test oppførsel, ikke implementasjon.
- Bruk Object Mother, slik at bare det relevante synes i testen.
- Foretrekk fakes fremfor mocks, og sjekk tilstand fremfor interaksjon.
- Hvis noe er vanskelig å teste, er det et designproblem.
- Mocks er greit ved eksterne grenser.

Forslag til avslutning: *«Tester er kode dere skal leve med lenge – skriv dem slik at fremtidige kolleger (og dere selv) blir glade når de leser dem.»*

Åpne for spørsmål.

## Øvrige tema hvis det er tid

- Én oppførsel per test.
- Ikke test private metoder.
- Unngå logikk (if/løkker) i tester.
- Lesbare assertions (AssertJ).
- Ustabile («flaky») tester.
- Nøstede testklasser for lesbarhet og enklere vedlikehold av tester
- Rik domenemodell som tar ansvar for egen tilstandsendring