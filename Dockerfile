   # Build stage
    FROM openjdk:17-jdk-slim AS build
    WORKDIR /app
    COPY . .
    RUN gradle clean build -x test

    # Package stage
    FROM openjdk:17-jdk-slim
    VOLUME /tmp
    COPY --from=build /app/build/libs/*.jar app.jar
    EXPOSE 8080
    ENTRYPOINT ["java","-jar","/app.jar"]