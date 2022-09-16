FROM maven:3.8.1-openjdk-17-slim as builder
COPY ./pom.xml pom.xml
COPY ./src src/
RUN mvn clean package

FROM openjdk:17-alpine
COPY --from=builder target/simplewebapp-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar app.jar