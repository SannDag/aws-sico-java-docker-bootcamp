# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the application JAR file into the container
COPY target/validatorservice-0.0.1-SNAPSHOT.jar .

# Make port 8080 available to the world outside this container
EXPOSE 8081

# Define the command to run your app
CMD ["java", "-jar", "validatorservice-0.0.1-SNAPSHOT.jar"]

#Construir y ejecutar contenedor
#docker build -t validatorservice-0.0.1-SNAPSHOT.jar .
#docker run -p 8081:8081 validatorservice-0.0.1-SNAPSHOT.jar