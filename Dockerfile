FROM openjdk:17-ea-11-jdk-slim-buster
COPY target/simplewebapp-0.0.1-SNAPSHOT.jar .
CMD java -jar simplewebapp-0.0.1-SNAPSHOT.jar

