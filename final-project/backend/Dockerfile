#FROM openjdk:17

#WORKDIR /usr/src/app

#COPY target/backend-0.0.1-SNAPSHOT.jar .

#EXPOSE 8080

#CMD ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]

FROM mariadb:10.6.16

ENV MARIADB_ROOT_PASSWORD=qwerty
ENV MARIADB_USER=sanndag
ENV MARIADB_PASSWORD=admin
ENV MARIADB_DATABASE=persons

VOLUME /var/lib/mysql


