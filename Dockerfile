FROM openjdk:17-alpine
COPY target/simplewebapp-0.0.1-SNAPSHOT.jar .
CMD java -jar simplewebapp-0.0.1-SNAPSHOT.jar

