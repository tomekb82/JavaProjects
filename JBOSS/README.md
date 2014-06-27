
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

Eclipse-> File-> New-> Dynami Web Project

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
