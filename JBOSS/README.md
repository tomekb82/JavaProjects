
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

