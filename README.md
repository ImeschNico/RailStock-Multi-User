# RailStock Multi-User #

## Einführung ##

RailStock ist eine Anwendung, die sich an Modelleisenbahn-Sammler sowie Privatverkäufer richtet und das Bewirtschaften
des eigenen Lagers erheblich erleichtert. Die Applikation bietet einen schnellen und übersichtlichen Einblick in den
vorhandenen Lokbestand und unterstützt dabei, jederzeit zu wissen, welche Modelle verfügbar sind und wo sie sich befinden.

Das Ziel von RailStock ist es, Sammlern wie auch Händlern ein Werkzeug zur Verfügung zu stellen, mit dem Bestände strukturiert,
vollständig und nachvollziehbar verwaltet werden können. Die Anwendung bietet Funktionen zum Suchen und Bearbeiten von Lokdaten, 
zum Erstellen und Verwalten von Lagerplätzen sowie zum Anpassen und Transferieren von Beständen. Zusätzlich besteht die Möglichkeit, 
Benutzerkonten anzulegen und RailStock gemeinsam mit mehreren Anwendern zu nutzen.

---

## Motivation ##

Die Motivation für dieses Projekt entstand durch meinen Stiefvater, der selbst ein leidenschaftlicher Sammler ist und zudem privat 
Modelleisenbahnen verkauft. Über die Jahre hat er eine beträchtliche Anzahl an Lokmodellen angesammelt. Sein Dachgeschoss dient als 
Lager und ist inzwischen vollständig gefüllt, jedoch ohne klaren Überblick darüber, welche Loks sich wo befinden.

Beim Anblick dieser chaotischen Situation entstand die Idee, eine Applikation zu entwickeln, die ihm echten Mehrwert bietet und im Alltag 
tatsächlich genutzt werden kann. Ich habe die Gelegenheit genutzt und meinen Stiefvater als „Kunden“ gewonnen, um ein möglichst praxisnahes 
und realitätsgetreues Szenario für die Entwicklung zu schaffen.

---

## Ziel der Multi-User Unterstüzung ##

Im Rahmen der Abgabe für das Modul 223 – Multi-User-Applikationen objektorientiert realisieren bestand die Aufgabe darin, eine Multi-User-fähige 
Anwendung umzusetzen. Als Ausgangspunkt diente ein bereits lauffähiges Frontend (React + Vite) sowie ein Backend (Spring Boot). Ich entschied mich bewusst dafür,
mein eigenes Projekt weiterzuentwickeln und als Basis zu verwenden, um eine praxisnahe und erweiterbare Multi-User-Applikation zu realisieren.

---

## Funktionale Anforderungen ##

•	Benutzer registrieren und anmelden
•	Loks anlegen, bearbeiten und löschen
•	Lagerplätze erstellen und verwalten
•	Loks einem Lagerplatz zuordnen
•	Filter- und Suchfunktionen
•	Bestände übertragen / transferieren
•	Multi-User-Funktionalität inkl. Rollen- und Rechteverwaltung

---

## User Stories ##

1.	Als Admin will ich eine Seite haben, auf der ich neue Loks anlegen und bestehende Loks bearbeiten kann,
damit nur Administratoren Umbauten und Änderungen an Lokdaten vornehmen können und diese klar vom normalen
Benutzerbetrieb getrennt bleiben.

3.	Als Benutzer möchte ich mich registrieren und anmelden können,
damit ich Zugriff auf die Funktionen der Anwendung habe und diese nicht öffentlich einsehbar sind.

4.	Als gleichzeitiger Benutzer möchte ich unabhängig von anderen Nutzern arbeiten können,
damit mehrere Personen das System parallel verwenden können, ohne sich gegenseitig zu beeinflussen.

Benutzerrollen:
User -> darf Loks verwalten, Bestand anpassen/transferieren und Lagerplätze erstellen
Admin -> darf Loks bearbeiten oder erstellen  

---

## Backend Dokumentation ##

Zur Ausführlichen Dokumentation der Backend Architektur gehts hier: [Backend Architketur Dokumentation](backend/src/docs/readme.md)

---

## Frontend Dokumentation ##

Zur Ausführlichen Dokumentation des Frontend gehts hier: [Frontend Dokumentation](frontend/src/readme.md)

---

## Hilfestellungen ##

Das Erstellen einer Multi-User-Applikation war für mich eine neue Herausforderung, da ich bisher noch keine solche Anwendung entwickelt habe.  
Aus diesem Grund habe ich verschiedene Hilfestellungen in Anspruch genommen:

- Das Tutorial-Projekt unseres Dozenten Graziano Laveder. An dieses Tutorial konnte ich viele Konzepte, insbesondere in Bezug auf Security, auf mein eigenes Projekt übertragen.
- Unterstützung durch Mitschüler, mit denen ein reger Austausch stattfand, sodass wir uns gegenseitig helfen konnten.
- Hilfe des Dozenten Graziano Laveder, der mich bei fachlichen Fragen kompetent unterstützt hat.
- ChatGPT, das ich unter anderem für das Styling des Frontends, das Erstellen der Tests und die Fehlerfindung genutzt habe.

--- 

## Learnings / Erkenntnisse ##

Durch das Projekt konnte ich einige wichtige Learnings mitnehmen, darunter:

- Die Erstellung und der Umgang mit JWT-Token zur Authentifizierung und Autorisierung.
- Multi-User-Struktur und Rollenmanagement (Unterscheidung zwischen User und Admin, Rechteverwaltung).
- Integration von Frontend und Backend in einer Multi-User-Applikation.
- Aufbau und Nutzung von React Context zur globalen State-Verwaltung.
- Nutzung von Vite als modernes Frontend-Build-Tool.
- Umgang mit Asynchronität (API-Calls, Ladezustände, Error-Handling).
- Strukturierung einer komplexen Anwendung (Komponenten, Services, Contexts, Pages).
- Praxisnahe Fehlerbehebung und Debugging im Zusammenspiel von Frontend und Backend.

---

## Fazit ##

Dieses Projekt war für mich persönlich herausfordernd, nicht nur wegen der neuen Technologien, die ich verwendet habe, 
sondern auch aufgrund des engen Zeitrahmens von nur einer Woche. Trotz der Herausforderungen bin ich stolz auf das Ergebnis, 
auch wenn bereits weitere Features geplant sind, wie eine mobile Anwendung oder eine erweiterte User-Verwaltung.

Da RailStock eine Anwendung ist, die echten Mehrwert bieten soll, hatte ich immer ein klares Ziel vor Augen: eine Applikation zu 
entwickeln, mit der mein Stiefvater und meine Mutter ihren Lagerbestand effizient managen und einen besseren Überblick behalten können.

Zusammenfassend kann man mit RailStock zum aktuellen Zeitpunkt:

- Loks suchen, bearbeiten und erstellen  
- Bestände anpassen und transferieren  
- Lagerplätze erstellen  
- Loks filtern  
- Registrieren und Einloggen  

Auch wenn RailStock aktuell alle Kernfeatures enthält, um zu funktionieren, ist sie noch lange nicht fertig. Ausblick auf weitere Features:

- User-Verwaltung implementieren  
- Mobile Applikation entwickeln und einbinden  
- Mobile Applikation inkl. Strichcode- oder QR-Scanner, um Bestände einfacher kontrollieren zu können










