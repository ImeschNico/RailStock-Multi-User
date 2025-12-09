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
