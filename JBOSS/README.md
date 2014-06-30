
Instalacja JBoss AS7
--------------------

Pobierz z http://jbossas.jboss.org/downloads
(np.JBoss AS 7.1.1.Final)

Uruchomienie JBoss AS
---------------------

jboss-as-7.1.1.Final/bin$ sudo sh ./standalone.sh

http://localhost:8080/

Polaczenie z serwerem przy uzyciu wiersza polecen (CLI)
-------------------------------------------------------

jboss-as-7.1.1.Final/bin$ sudo ./jboss-cli.sh
...
[disconnected /] connect
[standalone@localhost:9999 /] 


- zatrzymanie serwera:

[standalone@localhost:9999 /] :shutdown
{"outcome" => "success"}

Instalacja narzedzi JBoss w Eclipse
------------------------------------

Help->Install New Software

- instalacja JBoss Tools

File->New->Server

- wskazac folder z instalaja serwera JBoss AS
- wystartowac serwer z Eclipse

Dodanie uzytkownika
--------------------

u: jboss
p: jboss1

jboss-as-7.1.1.Final/bin$ sudo sh ./add-user.sh

What type of user do you wish to add? 
 a) Management User (mgmt-users.properties) 
 b) Application User (application-users.properties)
(a): a

Enter the details of the new user to add.
Realm (ManagementRealm) : 
Username : jboss
Password : 
Re-enter Password : 

About to add user 'jboss' for realm 'ManagementRealm'
Is this correct yes/no? yes
Added user 'jboss' to file '/home/tomek/Desktop/jboss-as-7.1.1.Final/standalone/configuration/mgmt-users.properties'
Added user 'jboss' to file '/home/tomek/Desktop/jboss-as-7.1.1.Final/domain/configuration/mgmt-users.properties'

Uruchomienie konsoli webowej
--------------------------------------

http://localhost:9990/error/index.html


Tworzenie pierwszego projektu w Eclipse
---------------------------------------

Eclipse-> File-> New-> Dynamic Web Project

target runtime: JBoss 7.1 Runtime

Dodajemy servlet do projektu: File->New->Servlet

Stworzyc plik WEB-INF/jboss-web.xml:
<jboss-web>
	<context-root>/hello</context-root>
</jboss-web>
Dodac aplikacje na serwerze JBoss (Add and Remove w zakladce Servers)

Sprawdzenie na URL: http://localhost:8080/hello/test

Wdrozenie aplikacji z poziomu konsoli
--------------------------------------

Uwaga: W eclipse ustawiono 'Deploy project as compressed archives' dla obslugi skompresowanych archiwow przez CLI.

jboss-as-7.1.1.Final/bin$ sudo sh ./jboss-cli.sh 
[sudo] password for tomek: 
You are disconnected at the moment. Type 'connect' to connect to the server or 'help' for the list of supported commands.
[disconnected /] connect
[standalone@localhost:9999 /] deploy
HelloWorld.war
[standalone@localhost:9999 /] undeploy HelloWorld.war
[standalone@localhost:9999 /] deploy
[standalone@localhost:9999 /] deploy ../standalone/deployments/HelloWorld.war
[standalone@localhost:9999 /] deploy
HelloWorld.war

Tworzenie pierwszego projektu Maven w Eclipse
-----------------------------------------------

- Add Archetype Catalog

    Go to menu Window--->Preferences.

    At the Preferences window, select Maven---->Archetypes,

    On the Right Panel, click Add Remote Catalog... button.

    At the Remote Archetype Catalog windows enter the following: -
        Catalog file: http://repo.maven.apache.org/maven2
        Description Maven Central

    Add more remote catalog.
        Catalog file: http://download.java.net/maven/2
        Description Java.Net

    Click OK to apply change.

- Stworz nowy projekt Maven

File->New->Maven Project

Filter: ejb-javaee6

- Dodac odpowiednie zaleznosci w pom.xml (patrz plik w projekcie *ticket-agency-ejb*)

- Stworzyc w Eclipse nowa konfiguracje do wdrozenia projektu na JBoss

Run as->Run Configuration->Maven Build->New:
Base directory: ${workspace_loc:/ticket-agency-ejb}
Goals: jboss-as:deploy

Tworzenie zdalnego klienta EJB
-------------------------------

- jboss-remote-naming (JNDI): ejb:<app_name>/<module_name>/.../<classname-of-remote-interface>
[logi z Jboss - przydatne do poierania JNDI dla 'ejb:']
	java:global/ticket-agency-ejb/TheatreBox!eu.tbelina.jboss.ejb.TheatreBox
        java:app/ticket-agency-ejb/TheatreBox!eu.tbelina.jboss.ejb.TheatreBox
        java:module/TheatreBox!eu.tbelina.jboss.ejb.TheatreBox
        java:global/ticket-agency-ejb/TheatreBox
        java:app/ticket-agency-ejb/TheatreBox
        java:module/TheatreBox


- stworzyc nowy projekt Maven
New->Maven Project

wybrac archetyp: maven-archetype-quickstart


- Dodac odpowiednie zaleznosci w pom.xml (patrz plik w projekcie *ticket-agency-ejb-client*)

- context.lookup() do wyszukiwania komponenetów EJB

- uruchomiene aplikacji klienckiej

Dodano w pom.xml modul *exec-maven-plugin* umozliwiajacy wykonanie programow Javy przy uzyciu celu exec.

- Stworzyc w Eclipse nowa konfiguracje do uruchominia klienta

Run as->Run Configuration->Maven Build->New:
base direcory: ${workspace_loc:/ticket-agency-ejb-client}
goals: install exec:exec


Dodanie uslugi czasomierza EJB
------------------------------

TODO: Nie dziala czasomierz pojedynczy w klasie TheatreBox

TODO: Nie dziala obsluga obiektow Future - odbior wynikow

Realizacja czasomierza czasowego w eu.tbelina.jboss.timer.AutomaticSellerBean.
Wywolanie rowniez czasomierza jednokrotnego z metody automaticCustomer()-> createTimer()

Projekt webowy w maven dla obslugi CDI
--------------------------------------

New->Maven Project
archetyp: webapp-javaee6
artifact id (nazwa projektu): *ticket-agency-cdi*


- uzycie adnotacji:
	- @Named
	- @SessionScoped, @RequestScoped
	- @Model <=> @Named+@ReguestScoped
	- @Produces - producenci, mozliwosc odwolania bezposrednio do obiektow z poziomu JSF

- dodac obserwatra - oczekuja na zmiany danych

- uzycie po stronie klienta AJAX'oweg odswiezania strony z uzyciem RchFaces	


Projekt JPA + CDI
------------------

- Instalacja sterownika JDBC w JBoss AS7

Instalowany jako modul: JBOSS_HOME/modules
	- dodac plik .jar
 	- dodac plik module.ml zawierajacy deklaracje modulu i jego zaleznosci

	jboss-as-7.1.1.Final/modules/com$ mkdir mysql
	jboss-as-7.1.1.Final/modules/com$ cd mysql/
	jboss-as-7.1.1.Final/modules/com/mysql$ mkdir main
	jboss-as-7.1.1.Final/modules/com/mysql$ cd main/
	jboss-as-7.1.1.Final/modules/com/mysql/main$ cp /home/tomek/Desktop/mysql-connector-java-3.0.17-ga-bin.jar .
	jboss-as-7.1.1.Final/modules/com/mysql/main$ ls
	mysql-connector-java-3.0.17-ga-bin.jar

	- module.xml ( o nazwie *com.mysql* dla Data Source) :

WAZNE: w paczce .jar powinien byc tylko 1 driver (w paczkach z Maven sa 2 i rzuca bledem)
	
<module xmlns="urn:jboss:module:1.0" name="com.mysql">
  <resources>
    <resource-root path="mysql-connector-java-5.1.27-bin.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>


- konfiguracja zrodla danych (Data Source)

	- edycja pliku standalone.xml


	<subsystem xmlns="urn:jboss:domain:datasources:1.0">
            <datasources>
                <datasource jndi-name="java:jboss/datasources/MySqlDS" pool-name="MySqlDS" enabled="true" >
                    <connection-url>jdbc:mysql://localhost:3306/ticketsystem</connection-url>
                    <driver-class>com.mysql.jdbc.Driver</driver-class>
                    <driver>mysql</driver>
                    <security>
                        <user-name>jboss</user-name>
                        <password>jboss</password>
                    </security>
                </datasource>
                <drivers>
                    <driver name="mysql" module="com.mysql" />
                </drivers>
            </datasources>
        </subsystem>

- utworzenie projektu web w Maven

JMS Provider
-------------------------------------

- uruchomic serwer z uzyciem pliku konfiguracyjnego *standalone/configuration/standalone-full.xml* (dla obslugi JMS)

$ sudo sh bin/standalone.sh -c standalone-full.xml

	- dodanie w pliku standalone-full.xml fabryki polaczen JMS wykorzysujaca pule placzen

		   <pooled-connection-factory name="hornetq-ra">
                        <transaction mode="xa"/>
                        <connectors>
                            <connector-ref connector-name="in-vm"/>
                        </connectors>
                        <entries>
                            <entry name="java:/JmsXA"/>
                        </entries>
                    </pooled-connection-factory>

	- dodanie Data Source:
 	    
  	  <datasources>
                <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true">
                    <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1</connection-url>
                    <driver>h2</driver>
                    <security>
                        <user-name>sa</user-name>
                        <password>sa</password>
                    </security>
                </datasource>
                <datasource jta="false" jndi-name="java:jboss/datasources/MySqlDS" pool-name="MySqlDS" enabled="true">
                    <connection-url>jdbc:mysql://localhost:3306/ticketsystem</connection-url>
                    <driver-class>com.mysql.jdbc.Driver</driver-class>
                    <driver>mysql</driver>
                    <security>
                        <user-name>jboss</user-name>
                        <password>jboss</password>
                    </security>
                </datasource>
                <drivers>
                    <driver name="mysql" module="com.mysql"/>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                </drivers>
            </datasources>


- jboss admin Console:

	- dodawanie kolejki/tematu:
	
	Messaging-> Messaging Provider -> View -> JMS Destination -> Add
	name: TicketQueue
	JNDI name: java:jboss/jms/queue/ticketQueue

- dodanie JMS do projektu

	- tworzenie MDB(Ziarno sterowane komunikatami) - przechwytywanie/odczyt danych




Pobieranie przykladow z ksiazki
-------------------------------------
$ ftp ftp.helion.pl
Connected to virtual115.helion.pl.
220 ProFTPD 1.3.5rc3 Server ready.
Name (ftp.helion.pl:tomek): anonymous
331 Anonymous login ok, send your complete email address as your password
Password:
230-                          
 			    Serwer ftp wydawnictwa helion.
 			Przyklady do ksiazek prosze pobierac klikajac link na stronie !
 
230 Anonymous access granted, restrictions apply
Remote system type is UNIX.
Using binary mode to transfer files.

