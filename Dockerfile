# Docker file for Shuttlestats microservice API

# Build Stage
FROM maven:3.9.1-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml /app/
RUN mvn package -DskipTests
COPY ./src ./src

# Production Stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app /app
ENTRYPOINT ["java", "-jar", "/app/target/my-app.jar"]