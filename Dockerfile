FROM amazoncorretto:21-alpine3.21 as builder

WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN sed -i 's/\r$//' gradlew


RUN ./gradlew clean build -x test --no-daemon

FROM amazoncorretto:21.0.7-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]