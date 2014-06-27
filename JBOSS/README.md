
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

