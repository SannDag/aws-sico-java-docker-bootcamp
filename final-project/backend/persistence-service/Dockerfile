#FROM openjdk:17-jdk-alpine
#
#WORKDIR /usr/src/app
#
#COPY target/persistence-service-0.0.1-SNAPSHOT.jar backend-0.0.1-SNAPSHOT.jar
#
#EXPOSE 8080
#
#CMD ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]

FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/persistence-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_persistence.jar
ENTRYPOINT ["java", "-jar", "app_persistence.jar"]