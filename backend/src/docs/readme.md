# Backend Architektur #

## Layer-Architektur ##

![Layer-Architektur](./Backend_Architektur.png)

![Layer-Architektur2](./BackendArchitketur2.png) 

---

## Erklärung ##

Die Anwendung folgt einer klassischen Mehrschichten- bzw. Layer-Architektur, 
um Verantwortlichkeiten klar zu trennen, den Code wartbar zu halten und eine 
saubere Struktur für eine Multi-User-Applikation sicherzustellen. Die wichtigsten Schichten sind:

### Controller Layer ###
Der Controller Layer stellt die Schnittstelle zwischen Frontend und Backend dar.

Hauptaufgaben:

- 	Entgegennahme von HTTP-Anfragen (GET, POST, PUT, DELETE)
-	Aufruf der passenden Service-Methoden
-   Rückgabe von HTTP-Responses an das Frontend
-	Validierung grundlegender Request-Daten
-	Verwaltung von Rollen- und Zugriffsrechten via Spring Security

**Wichtig:**
Der Controller Layer enthält keine Geschäftslogik, sondern dient ausschließlich zur 
Steuerung des Datenflusses zwischen Frontend, Service Layer und DTOs.

---

## DTO Layer ##
Der DTO Layer dient dazu, kontrollierte Datenobjekte zwischen Frontend und Backend 
auszutauschen.

Aufgaben:
-	Schutz sensibler Daten (z. B. Passwort wird nie übertragen)
-	Reduzierung der übertragenen Datenmenge
-	Bereitstellung klar definierter Datenstrukturen für Requests/Responses
-	Entkopplung des Entity-Modells vom API-Modell

**Wichtig:**
DTOs sind nicht in der Datenbank gespeichert sie sind reine Transportobjekte.

---

## Mapper Layer ##
Der Mapper Layer übersetzt Daten zwischen Entities und DTOs.

Aufgaben:

-	Umwandlung von DTOs → Entities (z. B. beim Erstellen/Bearbeiten)
-	Umwandlung von Entities → DTOs (z. B. fürs Frontend)
-	Sicherstellen, dass nur erlaubte Daten nach außen gegeben werden
-	Reduktion von Boilerplate im Controller und Service Layer

---

## Service Layer ##
Der Service Layer bildet den Kern der Geschäftslogik der Anwendung.
Aufgaben:
•	Umsetzung aller fachlichen Regeln (z. B. Lok-Anlage, Bestandstransfers, Lagerplatzzuweisung)
•	Multi-User-Logik (z. B. Zugriffskontrollen, Validierung, Rollenverhalten)
•	Kommunikation mit dem Repository Layer
•	Konsistenz und Korrektheit der Daten sicherstellen
•	Aufbereitung von Daten für Mapper/DTOs

**Wichtig:**
Der Service Layer ist unabhängig vom Frontend und bildet die zentrale Steuereinheit der Applikation.

---

## Repository Layer ##
Der Repository Layer ist für die Datenbankzugriffe verantwortlich.

Aufgaben:
-	Verwenden von JPA/Hibernate zur Datenpersistenz
-	CRUD-Operationen für alle Entitäten
-	Ausführen von Abfragen (z. B. findBy… Methoden)
-	Verwaltung von Relationen (OneToMany, ManyToOne, usw.)
-	Rückgabe reiner Datenobjekte an den Service Layer

**Wichtig:**
Der Repository Layer führt keine Logik aus — nur Datenzugriff.

---

## Entity Layer ##
Der Entity Layer beschreibt die Datenstruktur des Systems.

Bestandteile:

-	Lok-Entität
-	Hersteller
-	Lagerplatz
-	Bestand
-	Benutzer
-	Rolle

**Eigenschaften:**
-	Mit JPA annotiert (@Entity, @Id, @ManyToOne, usw.)
-	Repräsentiert die reale Domäne der Anwendung
-	Entspricht 1:1 den Tabellen in der Datenbank

**Wichtig:**
Entities werden nicht direkt ans Frontend gesendet, um Sicherheit und Kapselung zu gewährleisten.

---

## Transactionals ##

Wieso Transactionals? ```@Transactional``` sorgt dafür, dass mehrere zusammengehörige 
Datenbankoperationen als eine einzige, unteilbare Einheit ausgeführt werden. Das 
bedeutet:

Wenn ein Fehler passiert, werden alle Änderungen automatisch zurückgerollt, sodass keine halbfertigen oder inkonsistenten Daten entstehen.

1. Im [BestandService.java](../main/java/com/railStock/rail_stock/service/BestandService.java) in der Methode ```transferBestand``` ist die erste Transactional zu finden.

Die Methode transferBestand führt einen vollständigen Bestandswechsel zwischen zwei Lagerplätzen durch. Sie validiert Quell- und Ziel-Lagerplatz, prüft die verfügbare Bestandsmenge und nimmt alle notwendigen Änderungen an den entsprechenden Bestandsdatensätzen vor.
Durch @Transactional wird sichergestellt, dass beide Änderungen (Abzug am Quellplatz und Addition am Zielplatz) atomar erfolgen und bei einem Fehler vollständig zurückgerollt werden, um Inkonsistenzen zu vermeiden.


2. Im [LokService.java](../main/java/com/railStock/rail_stock/service/LokService.java) in den Methoden 
```createLok``` und ```duplicateLokWithChanges``` sind weitere Transactionals zu finden.

Die Methoden createLok und duplicateLokWithChanges führen jeweils mehrere abhängige Schritte aus: Laden des Herstellers, Validierungen und das Speichern neuer Lok-Einträge.
Durch ```@Transactional``` wird sichergestellt, dass diese zusammengehörigen Operationen atomar ausgeführt werden. Falls z. B. ein Hersteller nicht existiert oder eine Validierung fehlschlägt, wird der gesamte Vorgang zurückgerollt.
So wird verhindert, dass unvollständige oder fehlerhafte Lok-Einträge in der Datenbank landen, und die Datenkonsistenz bleibt jederzeit gewährleistet.

3. Im [AppUserService.java](../main/java/com/railStock/rail_stock/service/AppUserService.java) die ganze Klasse ```AppUserService.java``` eine Transactional.

Der ```AppUserService``` ist als Ganzes mit ```@Transactional``` annotiert. Dadurch werden alle Methoden innerhalb dieses Services automatisch in einer Transaktion ausgeführt.
Beim Anlegen oder Ändern von Benutzerdaten (z. B. Speichern neuer User, Hashen von Passwörtern, Validierungen) wird so sichergestellt, dass alle Schritte atomar ablaufen.
Falls während der Verarbeitung ein Fehler auftritt, wird der gesamte Vorgang zurückgerollt und verhindert, dass unvollständige Benutzerkonten oder fehlerhafte Daten gespeichert werden.

Dies schützt besonders sicherheitsrelevante Prozesse wie die Registrierung und Passwortverarbeitung vor Inkonsistenzen.

---

## Datenbank Diagramm ##

![DatenbankDiagramm](ERD.png)

## Erklärung des Datenbankdiagramms ##
Das Datenbankdiagramm von RailStock bildet die Struktur der Anwendung und die Beziehungen zwischen den wichtigsten Entitäten ab. Es zeigt, wie die verschiedenen Komponenten der Applikation miteinander verbunden sind.

---
## Entitäten ##
## Lok ##
-	Repräsentiert ein einzelnes Modelleisenbahn-Lokobjekt.
-	Wichtige Attribute: id, art_number, betriebsrat, bezeichung, epoche, modell, spur, stromart, typ , hersteller-ID (FK).
-	Jede Lok ist einem Hersteller zugeordnet (ManyToOne).
-	Jede Lok kann einen Bestand haben(1:0..1).
-	Loks können über den Bestand einem Lagerplatz zugewiesen werden.

---

## Hersteller ##
-	Enthält Informationen zu Herstellern von Modellen.
-	Attribute: ID, Name
-	Beziehung zu Loks: Ein Hersteller kann mehrere Loks besitzen (OneToMany).
-	Beliebig viele Hersteller kann keinen oder einen Bestand haben (Many: 0..1)

---

## Lagerplatz ##
-	Repräsentiert einen physischen oder virtuellen Ort, an dem Loks gelagert werden.
-	Attribute: ID, regal, tablar
-	Beziehung zu Bestand: Ein Lagerplatz kann mehrere Loks enthalten (über die Bestand-Tabelle).

---

## Bestand ##
-	Die Bestandstabelle verbindet Loks mit Lagerplätzen und gibt die Menge an.
-	Attribute: ID, menge, Lok-ID (FK), Lagerplatz-ID (FK), hersteller-ID(FK)
-	Beziehung: ManyToOne zu Lok und Lagerplatz.
-	Diese Tabelle ermöglicht, den aktuellen Lagerbestand pro Lok pro Lagerplatz zu erfassen.

---

## Benutzer ##
-	Repräsentiert die User der Multi-User-Applikation.
-	Attribute: ID, email, password, rôle, username, version
-	Jeder Benutzer hat eine Rolle, die Rechte und Zugriffsmöglichkeiten definiert.

## Rolle ##
-	Enthält die möglichen Rollen (z. B. Admin, User).
-	Attribute: ID, Name, Beschreibung.
-	Beziehung: Ein Benutzer kann genau eine Rolle besitzen (OneToMany von Rolle zu Benutzer).
-	Rollen bestimmen, welche Aktionen ein Benutzer durchführen darf, z. B. Loks anlegen oder Benutzer verwalten.

---

## JWT-Authentifizierungs-Flow ##

![JWT1](JWT1.png)

## Registrierung ##

### Schritt 1 ###
**Der Benutzer sendet seine Daten an das Frontend**

Das Frontend schickt eine JSON-Payload ans Backend. Diese sieht so aus:

```JSON
{
  "username": "max",
  "email": "max@example.com",
  "password": "meinPasswort123"
}
```
---

### Schritt 2 ###

**Das Backend empfängt den Request**

Das Backend hat dafür einen Endpoint:

```arduino
POST /api/auth/register
```

Der Controller übergibt die Daten an AppUserService, besser gesagt an **registerUser()**,
dieser Service/Methode ist für die Registrierung verantwortlich.


---

### Schritt 3 ###

**Passwort wird gehasht**

Da das Passwort niemals im Klartext gespeichert werden sollte, nutzen wir einen 
Passwort-Encoder(ByCrypt).

Zum Verständniss macht diese Methode:

1. Erhält Passwort im Klartext.
2. Hasht das Passwort mithilfe von ByCrypt.
```ini
hashedPassword = ByCrypt.encode(password)
```
Das Ergebnis sieht dann etwa so aus:
```javascript
$2a$10$8273JH....etc
```
3. Und speichert User und gehashtes Passwort

---

### Schritt 4 ###

**Ein neuer Benutzer wird in der Datenbank gespeichert**

In die Tabelle app_users wird folgendes eingetragen

| id | username | email | password_hash | role |
| -- | -------- | ----- | ------------- | ---- |
| 1  | max      | max@… | $2a$10$…      | USER |

Zum Schluss schickt das Backend eine Bestätigung:

```JSON
{ "message": "User registered successfully" }
```
---

## Login ##

![JWT2](JWT2.png)

### Schritt 1 ###

**Der Benutzer sendet seine Login-Daten an:**

```arduino
POST /api/auth/login
```
Dies beinhaltet:

```JSON
{
  "username": "max",
  "password": "meinPasswort123"
}
```

---

### Schritt 2 ###

**Backend prüft, ob der User existiert**

Backend lädt den User aus app_users und vergleicht Passwörter:

Dies sieht so aus:

```scss
passwordEncoder.matches(rawPassword, hashedPassword)
```

- Wenn alles korrekt ist -> Success, weiter.
- Wenn es einen Fehler gibt -> Fehler.

--- 

### Schritt 3 ###

**JWT wird erstellt**

Das Backend erzeugt einen JWT(JSON Web Token), der etwa so aussieht:

```ini
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9…
```

---

### Schritt 4 ###

**Token wird an das Frontend zurück geschickt**

Der Token wird im localStorage gespeichert, da wir keine Cookies benutzen.

Das Frontend empfängt ein JSON das etwa so aussieht:

```JSON
{
	"token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJzdWIiOiJhZG1pbiIsImlhdCI6MTc2NTE5MDk0MywiZXhwIjoxNzY1Mjc3MzQzfQ.dxQteot5ghxaAZtKuCd9-iPJ51xikmeAyjIOGshNs0E",
	"userId": 5,
	"username": "admin",
	"email": "admin@quiz.com",
	"role": "ADMIN",
	"expiresIn": 86400000,
	"tokenType": "Bearer"
}
```

Mit diesem Token kann der User dann auf protected-routes zugreifen,
also Endpoints für die man eine Role mit Berechtigungen hat.

---

### Schritt 5 ###

**Der JwtAutenticationFilter extrahiert den Header des Token**

1. Sprich der Header mit Bearer <JWT> wird extrahiert.
2. Der Token wird validiert.
3. Wenn das klappt werden UserDetails aus der DB geladen.
4. Der SecurityContext wird gesetzt 
5. Der gültige Token wird an das Frontend geschickt

---

## Tech Stack ##

| **Technologie**       | **Version**                                 | **Verwendung**                                                              |
| --------------------- | ------------------------------------------- | --------------------------------------------------------------------------- |
| **Java**              | 21                                          | Programmiersprache des Backends                                             |
| **Spring Boot**       | 3.5.6                                       | Backend-Framework, startet die Applikation, bietet Web, JPA, Security, etc. |
| **Spring Security**   | 6.1.0 (kommt mit Spring Boot 3.5.6)         | Authentifizierung & Rollenverwaltung                                        |
| **JWT (JJWT)**        | 0.11.5                                      | Token-basierte Authentifizierung                                            |
| **JPA / Hibernate**   | Hibernate 6.x (kommt mit Spring Boot 3.5.6) | ORM für Datenbankzugriffe                                                   |
| **Spring Validation** | 3.5.6 (über Spring Boot Starter)            | DTO- und Request-Validierung                                                |
| **PostgreSQL**        | 16                                          | Relationale Datenbank                                                       |
| **BCrypt**            | enthalten in Spring Security                | Passwort-Hashing                                                            |
| **Maven**             | 3.x                                         | Build-Tool & Dependency-Management                                          |
| **SpringDoc OpenAPI** | 2.6.0                                       | API-Dokumentation (Swagger UI)                                              |
| **HikariCP**          | intern (Spring Boot)                        | Connection-Pooling für DB                                                   |

---

## Beschreibung ##

Das Backend von RailStock basiert auf Spring Boot und stellt alle Funktionen der 
Anwendung über eine REST-API bereit. Um die Multi-User-Anforderungen zu erfüllen, 
wird Spring Security eingesetzt. Darüber werden Logins, Benutzerrollen und der Zugriff auf Admin- oder Benutzer-Endpunkte gesteuert.
Sobald sich ein Benutzer anmeldet, erzeugt der JwtService ein JWT-Token, das im 
Frontend gespeichert wird. Dieses Token wird bei jedem Request automatisch im 
Authorization-Header mitgeschickt. Der JwtAuthenticationFilter prüft anschließend:

- ob das Token gültig ist

- ob es nicht abgelaufen ist

- welcher Benutzer darin enthalten ist

- welche Rolle der Benutzer besitzt

Erst danach wird der Request an die eigentliche Controller-Methode weitergeleitet.
Alle Datenbankzugriffe laufen über JPA-Repositories, sodass keine eigenen 
SQL-Statements geschrieben werden müssen. Hibernate übersetzt die Entitäten 
automatisch in Tabellen und kümmert sich um Abfragen, Relationen und Updates. 
Für die Sicherheit der Benutzerkonten werden alle Passwörter beim Registrieren 
mit BCrypt gehasht gespeichert, damit nie Klartext-Passwörter in der Datenbank liegen.
PostgreSQL dient als zentrale Datenbank für Lok-Bestände, Lagerplätze, 
Benutzerkonten und Rollen. Mit diesem Setup entsteht eine klare, sichere und 
erweiterbare Serverarchitektur, die perfekt zu einer 
Multi-User-Lagerverwaltungsapplikation passt.

---

## Test Protokoll ##

| Test-ID | Testname                   | Testtyp   | Ziel / Zweck                                                         | Vorgehensweise                                                                                                                                                             | Erwartetes Ergebnis                                                                                       | Ergebnis                              |
| ------- | -------------------------- | --------- | -------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | ------------------------------------- |
| **T1**  | Lok anlegen                | Unit-Test | Prüft, ob `LokService` eine Lok korrekt erstellt                     | 1. Erstelle `LokDTO` mit Name & Hersteller<br>2. Rufe `LokService.createLok(dto)` auf<br>3. Mock `lokRepository.save`                                                      | `Lok` wird zurückgegeben, Name & Hersteller stimmen, Repository.save wird genau einmal aufgerufen         | Erfolgreich / alle Assertions erfüllt |
| **T2**  | User Registrierung & Login | Unit-Test | Prüft, ob `AppUserService` Registrierung & Auth korrekt funktioniert | 1. Erstelle `AppUser` mit username, email, password, role<br>2. Mock PasswordEncoder (`encode`) und Repository (`save`)<br>3. Rufe `registerUser` & `authenticateUser` auf | Neuer `AppUser` wird zurückgegeben, Passwort gehashed, Login erfolgreich, Repository.save wird aufgerufen | Erfolgreich / alle Assertions erfüllt |

---

## Test Resultate ##

**Test 1**

![Test1](Test1.png)

---

**Test 2**

![Test2](Test2..png)

---

## Set-up Guide/ Installation ##

### 1. Projekt herunterladen ###

- Repository klonen oder ZIP herunterladen:

```bash
git clone https://github.com/ImeschNico/RailStock.git
cd RailStock
```

---

### 2. Voraussetzungen ###

Docker (Version ≥ 20.x)

Docker Compose (Version ≥ 1.29.x)

Optional: Datenbank-Client wie DBeaver, falls direkter DB-Zugriff gewünscht ist

---

### 3. Docker Setup ###

Docker Compose starten

```bash
cd src/main/java/com/railStock/rail_stock/docker/docker-compose.yml
docker-compose up -d
```

---

### 4. Datenbank ###

- Der DataLoader im Backend sorgt automatisch dafür, dass Testdaten (Loks, Benutzer) bei jedem Start eingespielt werden.

- Kein manueller SQL-Import nötig.

--- 

### 5. Enviroment Variables erstellen ###

1. **IntelliJ IDEA**

- Run/Debug Configuration öffnen

- Menü: Run → Edit Configurations…

- Wähle deine Spring Boot Application aus.

2. **Environment Variables eintragen**

- Feld: Environment variables

- Werte setzen:

- - DB_USERNAME=railstock_user
- - DB_PASSWORD=railstock_password
- - JWT_SECRET=3cbed2a54586f9ea7002d8e2b79226ae23c55d0fb8c16d835f72301037da253c

3. **Speichern und starten**

Run/Debug starten → Spring Boot liest die Variablen automatisch über 
```bash
${DB_USERNAME}, ${DB_PASSWORD} und ${JWT_SECRET}
 ```
aus der application.properties.

--- 

4. **Backend starten**
- Backend ist jetzt unter 
```ini
http://localhost:8080
```
erreichbar.





















