FROM eclipse-temurin:21-jdk-alpine as builder

WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN sed -i 's/\r$//' gradlew

RUN ./gradlew clean build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]