FROM openjdk:11-jdk-slim AS build

WORKDIR /app

COPY gradlew .
COPY build.gradle .
COPY src /src

RUN ./gradlew build -x test

FROM openjdk:11-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8011

ENTRYPOINT ["java", "-jar", "app.jar"]
