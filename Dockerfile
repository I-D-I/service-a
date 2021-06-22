### STAGE 1: Setup ### para KANIKO
#FROM maven:3.8.1-jdk-11 as builder
#
#RUN ls -la
#
#WORKDIR /workspace/source
#
#RUN mvn package -DskipTests
#
#RUN ls -la

### STAGE 2: Setup ###
FROM openjdk:11-jre-slim

WORKDIR /workspace/source

USER root

RUN apt update && apt install -y curl

#COPY --from=builder /workspace/source/target/*.jar /app.jar

COPY target/*.jar /app.jar

EXPOSE 8080

HEALTHCHECK CMD curl --fail http://localhost:8080/service-a/actuator/health || exit 1

CMD ["java", "-jar", "/app.jar"]
