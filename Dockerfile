# Stage 1: build the JAR
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: run it
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/target/devops-tasks-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]