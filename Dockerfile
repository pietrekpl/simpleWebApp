FROM openjdk:17-ea-11-jdk-slim-buster
MAINTAINER p.ludynia
COPY target/simplewebapp-0.0.1-SNAPSHOT.jar .
EXPOSE 8082
CMD java -jar simplewebapp-0.0.1-SNAPSHOT.jar

