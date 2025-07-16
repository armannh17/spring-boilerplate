# build stage
FROM eclipse-temurin:24-jdk-alpine AS builder

WORKDIR /app

RUN gu install native-image

COPY . .

RUN ./mvnw -Pnative spring-boot:build-image -DskipTests

# run stage
FROM alpine:3.22.1 AS runner

WORKDIR /app

COPY --from=builder /app/target/app .

RUN chmod +x app

EXPOSE 3000

ENTRYPOINT ["./app"]
