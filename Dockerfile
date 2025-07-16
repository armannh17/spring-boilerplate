# build stage
FROM ghcr.io/graalvm/graalvm-ce:22.3.1 AS builder

WORKDIR /app

COPY . .

RUN ./mvnw -Pnative native:build -DskipTests

# run stage
FROM alpine:3.22.1 AS runner

WORKDIR /app

COPY --from=builder /app/target/app .

RUN chmod +x app

EXPOSE 3000

ENTRYPOINT ["./app"]
