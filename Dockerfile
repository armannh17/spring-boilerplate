# build stage
FROM eclipse-temurin:24-jdk-alpine as builder

WORKDIR /app

COPY . .

RUN ./mvnw dependency:go-offline

RUN ./mvnw -Pnative native:compile

# run stage
FROM eclipse-temurin:24-jre-alpine as runner

WORKDIR /app

COPY --from=builder /app/target/app .

RUN chmod +x app

ENTRYPOINT ["./app"]
