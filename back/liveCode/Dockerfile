FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/liveCode-0.0.1-SNAPSHOT.jar /app/livecode-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/livecode-app.jar"]
