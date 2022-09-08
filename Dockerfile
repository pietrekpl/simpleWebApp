FROM openjdk:11
MAINTAINER p.ludynia
COPY target/simplewebapp-0.0.1-SNAPSHOT.jar .
CMD java -jar simplewebapp-0.0.1-SNAPSHOT.jar
