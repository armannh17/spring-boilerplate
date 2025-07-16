# build stage
FROM paketobuildpacks/graalvm:9.1.4 AS builder

WORKDIR /app

COPY . .

RUN ./mvnw -Pnative spring-boot:build-image -DskipTests

# run stage
FROM alpine:3.22.1 AS runner

WORKDIR /app

COPY --from=builder /app/target/app .

RUN chmod +x app

EXPOSE 3000

ENTRYPOINT ["./app"]
