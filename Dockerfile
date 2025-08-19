FROM openjdk:17-jdk-alpine

# Set the working directory inside the container.
WORKDIR /app

# Copy the built JAR file from your build output to the container.
# For Maven:  COPY target/<your_app_name>-<version>.jar app.jar
# For Gradle: COPY build/libs/<your_app_name>-<version>.jar app.jar
# Make sure to replace <your_app_name>-<version>.jar with your actual JAR filename
COPY build/libs/*.jar app.jar

# Expose the port that your Spring Boot application listens on (default is 8080).
EXPOSE 8080

# Define the command to run your application when the container starts.
ENTRYPOINT ["java", "-jar", "app.jar"]