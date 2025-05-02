FROM openjdk:21-jdk-slim AS builder
WORKDIR /app

COPY gradlew .
COPY gradle/wrapper gradle/wrapper
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x gradlew && \
    ./gradlew build -x test

FROM openjdk:21-slim
WORKDIR /app

COPY --from=builder /app/build/libs/s2v-solar-app-1.0-SNAPSHOT.jar ./app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]