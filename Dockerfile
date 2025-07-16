# build stage
FROM springci/graalvm-ce:java17-0.12.x AS builder

WORKDIR /app

COPY . .

RUN ./mvnw -Pnative native:compile -DskipTests

# run stage
FROM alpine:3.22.1 AS runner

WORKDIR /app

COPY --from=builder /app/target/app .

RUN chmod +x app

EXPOSE 3000

ENTRYPOINT ["./app"]
