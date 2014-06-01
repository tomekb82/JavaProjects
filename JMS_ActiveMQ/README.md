start:

steps.

    I ended up getting activemq to work by creating a configuration file via running the command "./bin/activemq setup newConfig" (exclude the quotes)
    I then replaced the current config file "activemq" which was located at etc/default/. (I made a backup of the original activemq file before overwriting it with newConfig).
    Run "./bin/activemq start" which will create a PID file.
    After the file is created re-run "./bin/activemq start" to finally start up the broker.

You can then test the install by navigating to "http://localhost:8161/admin/" or by doing a "netstat -an | grep 61616" if you kept the default ports etc.

-----------------------------------------------------


projekt:

- kompilacja:
$ javac -cp javassist.jar:j2ee.jar:activemq-all-5.9.0.jar Producer.java
$ java -classpath .:javassist.jar:j2ee.jar:activemq-all-5.9.0.jar Producer

$ javac -cp javassist.jar:j2ee.jar:activemq-all-5.9.0.jar Consumer.java
$ java -classpath .:javassist.jar:j2ee.jar:activemq-all-5.9.0.jar Consumer
log4j:WARN No appenders could be found for logger (org.apache.activemq.thread.TaskRunnerFactory).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
Text Message is Helloworld

- debug (firefox):
http://localhost:8161/admin/queueConsumers.jsp?JMSDestination=testQ

uwaga: można wydłużyć czas w Customer coby dłuzej czekać, wtedy widać go na localhoscie(admin)
