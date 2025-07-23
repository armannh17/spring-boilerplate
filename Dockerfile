# build stage
FROM eclipse-temurin:24-jdk-alpine AS builder

WORKDIR /build

COPY . .

RUN ./mvnw package -DskipTests

# run stage
FROM eclipse-temurin:24-jre-alpine AS runner

WORKDIR /app

COPY --from=builder /build/target/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
