################################################################################
# BUILDER — compile to a GraalVM native executable
################################################################################
FROM eclipse-temurin:24-jdk-alpine AS builder

WORKDIR /workspace

# copy sources & Maven wrapper
COPY pom.xml mvnw ./
COPY .mvn .mvn

# go offline (speed up rebuilds)
RUN ./mvnw -B dependency:go-offline

# copy application code
COPY src src

# package as native executable
RUN ./mvnw -B -Pnative clean package -DskipTests

################################################################################
# RUNTIME — tiny image with only your native binary
################################################################################
FROM alpine:3.18 AS runner
WORKDIR /app

# copy the native app from builder
COPY --from=builder /workspace/target/app .

# make sure it’s executable
RUN chmod +x app

# expose and run
EXPOSE 3000
ENTRYPOINT ["./app"]
