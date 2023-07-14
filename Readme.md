#Coworking Space
----------------------
##Dies ist eine kleine Backend Applikation für ein Coworking space Buchung.
es können...
...Mitglieder erstellt werden
...Buchungen vorgenommen werden
...Mitglieder verwalten
...Buchungen verwalten

---------------------------------
##Aufbau
1. Stellen Sie ein Docker Conatainer für Mysql auf. 
2. Forken Sie das Projekt und stellen sie eine Datenbankverbindung her.
3. Passen sie ihre Application.properties ab:
      spring.datasource.url=jdbc:mysql://localhost:3306/cospace
      spring.datasource.username=root
      spring.datasource.password=root
      spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
      spring.jpa.hibernate.ddl-auto=update
      spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration

##Starten
Nun können sie die Applikation starten indem sie die CoworkSpaceApplication laufen lassen

