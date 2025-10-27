Projektaufgabe 1: Bus Station Management System

Dies ist das erste Projekt fuer Fortgeschrittene Programmierung (Thema 1).

Das Ziel ist es, die Grundstruktur einer Spring Boot Anwendung zu bauen. Wir benutzen die MVC-Architektur und eine klare Schichtentrennung.

Technologie Java 17 Spring Boot Maven Spring Web Git / GitHub

Architektur und Design-Entscheidungen

Wir haben die vier Schichten (Model, Repository, Service, Controller) wie im PDF gefordert.

1. Model (model/) Hier sind alle Klassen aus dem UML-Diagramm (z.B. Bus, Route, Staff). Alle Attribute sind private und haben getter/setter und einen leeren Konstruktor.

2. Repository (repository/) Diese Schicht verwaltet die Daten. Es gibt keine Datenbank. Alle Daten sind nur im Speicher. Entscheidung: Wir benutzen eine List (ArrayList) in jedem Repository, um die Objekte zu speichern, so wie es das PDF erlaubt. Jedes Repository hat die Methoden save, findAll, findById und delete.

3. Controller (controller/) Diese Schicht nimmt die Anfragen vom Benutzer (Browser) entgegen. Wir haben einen HelloController, um zu testen, ob die Anwendung laeuft. Entscheidung: Wir haben einen AbstractBaseController. Alle anderen Controller (z.B. BusController) erben davon. Das hilft uns, das DRY-Prinzip (Don't Repeat Yourself) einzuhalten.