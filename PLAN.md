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

- [ ] Lag konkrete cases i domenet (velg domene som passer til kurset).
- [ ] Lag oppgavene på egnet sted.
- [ ] Lag løsningsforslag på egnet sted. Kan ligge nært oppgavene, men ikke i samme fil.
- [ ] Sjekk oppsettet på forhånd (bygg, IDE, kjøring av tester).
- [ ] Ha bonusoppgaver klare til de som blir raskt ferdige.

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