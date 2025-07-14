# build stage
FROM eclipse-temurin:24-jdk-alpine as builder

WORKDIR /app

COPY . .

RUN ./mvnw dependency:go-offline

RUN ./mvnw package -DskipTests

# run stage
FROM eclipse-temurin:24-jre-alpine as runner

WORKDIR /app

COPY --from=builder /app/target/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
