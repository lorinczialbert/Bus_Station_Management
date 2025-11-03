Projektaufgabe 1: Bus Station Management System

Eine Spring Boot REST-API zur Verwaltung eines Busbahnhofs (Busse, Routen, Passagiere etc.) auf Basis der Projektaufgabe 1.

Ziel

Das Ziel ist es, eine Spring Boot Anwendung mit einer klaren 4-Schichten-Architektur (MVC) aufzubauen. Die Anwendung verwendet keine echte Datenbank, sondern speichert alle Daten "In-Memory" (im Arbeitsspeicher).

Technologie

Java 17

Spring Boot

Maven

Spring Web (für REST-APIs)

Git / GitHub

Architektur (Schichtentrennung)

Die Anwendung ist in logische Schichten aufgeteilt, um das "Single Responsibility"-Prinzip zu befolgen:

Model (/model)

Einfache Java-Klassen (POJOs), die unsere Daten beschreiben (z.B. Bus, Passenger, Route). Sie basieren auf dem UML-Diagramm.

Repository (/repository)

Verwaltet die Daten. Jedes Repository (z.B. BusRepository) speichert seine Objekte in einer ArrayList.

Stellt Methoden bereit wie save(), findAll(), findById().

Service (/service)

Enthält die "Business-Logik". Der Service koordiniert die Aktionen.

Beispiel: BusService ruft das BusRepository auf, um Daten zu holen oder zu speichern.

Controller (/controller)

Stellt die HTTP-Schnittstelle (REST-API) nach außen bereit.

Verwendet @RestController, um Anfragen (z.B. vom Browser) entgegenzunehmen und JSON-Daten zurückzugeben.

Der Controller ruft nur den Service auf (niemals das Repository direkt).

Daten-Initialisierung

DataInitializer.java: Diese Klasse wird beim Start der Anwendung automatisch ausgeführt (CommandLineRunner). Sie füllt die In-Memory-Repositories mit Testdaten (Busse, Stationen, Routen etc.), damit die API beim Start sofort Daten zum Anzeigen hat.

API Endpunkte (Beispiele)

Nach dem Start der Anwendung können die Daten über folgende Endpunkte im Browser abgerufen werden:

http://localhost:8080/api/buses

http://localhost:8080/api/passengers

http://localhost:8080/api/routes

http://localhost:8080/api/busstations

... und so weiter für alle anderen Modelle.