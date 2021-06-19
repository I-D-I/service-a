### STAGE 1: Setup ###
#FROM maven as builder
#
#WORKDIR /app
#COPY src ./src
#COPY pom.xml .
#
#RUN mvn package -DskipTests

### STAGE 2: Setup ###
FROM openjdk:11-jre-slim

USER root

RUN apt update && apt install -y curl
  
#COPY --from=builder /app/target/*.jar /app.jar

COPY target/*.jar /app.jar

EXPOSE 8080

HEALTHCHECK CMD curl --fail http://localhost:8080/service-a/actuator/health || exit 1

CMD ["java", "-jar", "/app.jar"]
