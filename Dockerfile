# build stage
FROM paketobuildpacks/graalvm:9.1.4 as builder

WORKDIR /app

COPY . .

# RUN ./mvnw dependency:go-offline

RUN ./mvnw spring-boot:build-image -DskipTests -Pnative

# run stage
FROM alpine:3.22.1 as runner

WORKDIR /app

# COPY --from=builder /app/target/app.jar app.jar

# ENTRYPOINT ["java", "-jar", "app.jar"]

COPY --from=builder /app/target/native/native-app .

RUN chmod +x native-app

ENTRYPOINT ["./native-app"]
