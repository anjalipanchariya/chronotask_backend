# Stage 1: Build the app
FROM gradle:7.6-jdk17 AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

# Stage 2: Run the app
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
