# Stage 1: Build the application using Maven
FROM maven:3.8.4-openjdk-11-slim AS build

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src/

RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim

WORKDIR /app

COPY --from=build /app/target/liveCode-0.0.1-SNAPSHOT.jar /app/livecode-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/livecode-app.jar"]

