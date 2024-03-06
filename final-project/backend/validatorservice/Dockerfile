FROM openjdk:17-jdk-alpine

WORKDIR /usr/src/app

COPY target/validatorservice-0.0.1-SNAPSHOT.jar validatorservice-0.0.1-SNAPSHOT.jar

EXPOSE 8081

CMD ["java", "-jar", "validatorservice-0.0.1-SNAPSHOT.jar"]

