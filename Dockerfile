# Stage 1: Build the application
FROM maven:3.9.11-eclipse-temurin-17 AS builder


WORKDIR /app
# Copy the pom.xml file
COPY pom.xml .

# Download dependencies (this layer will be cached if pom.xml doesn't change)
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-jdk-jammy

# Copy the jar from the builder stage
COPY --from=builder /app/target/teamManagementAPI-0.0.1-SNAPSHOT.jar app_team_management.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app_team_management.jar"]
