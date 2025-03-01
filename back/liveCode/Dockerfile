FROM maven:3.8.4-openjdk-11-slim AS build

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src/

RUN rm -rf /root/.m2/repository && mvn clean package -DskipTests -X

FROM openjdk:11-jdk-slim

WORKDIR /app

COPY --from=build /app/target/liveCode-0.0.1-SNAPSHOT.jar /app/my-springboot-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/my-springboot-app.jar"]


