# Payment-Service-Provider

Partnerprojekte:
* Thorsten Bauer: Clubshop\
Beim Abschicken einer Zahlng vom Clubshop wird geprüft ob der Nutzer mit der Email registriert ist.
Falls ja, wird die Zahlung durhgeführt und ein Status "true" zurück gesendet. Ist der User nicht registriert wird "false" zurück gesendet.
* Maimilian Weinzierl: ManagementSystem\
Von diesem Projekt werden die Nutzer beim starten einer neuen Verlosung abgefragt.
Falls der Gewinner registriert ist wird eine Zahlung ausgeführt.
Ist der Gewinner nicht registriert wird ein Email an den Gewinner versendet.
  (Emailversand ist nicht implementiert)
* Florian Samaga: Getränkeshop \
(Es ist noch nicht sicher ob dieses Partnerprojekt teilnimmt)
---
Es gibt einen Administrator-Account der nicht gelöscht werden kann.
* Email: admin@othr.de
* Passwort: password\
(Über den Admin-Account kann eine Verlosung gestartet werden)

Standardnutzer können ihren Account löschen.
* Email: standard@gmail.com
* Passwort: password\
(Alle Nutzer die sich registrieren sind Standardnutzer)
---
Eventuell muss die *.jar nochmal ausgeführt werden.\
Folgende Fehlerursache die zum Abstürzen des Programms über Nacht geführt hat konnte ich nicht mehr beheben:\
2022-01-21 13:25:50.341  INFO 685007 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'\
2022-01-21 13:25:50.344  INFO 685007 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...\
2022-01-21 13:25:50.348  INFO 685007 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.


