# ==========================================
# Stage 1: Build the Application
# ==========================================
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copy the pom.xml and download dependencies first (caches them for faster builds)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the actual source code and build the executable jar
COPY src ./src
RUN mvn clean package -DskipTests

# ==========================================
# Stage 2: Run the Application
# ==========================================
# Note: Change '17' to '21' if you are using Java 21
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built jar from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Railway automatically injects a $PORT environment variable.
# We tell Docker to listen on it.
EXPOSE ${PORT:-8080}

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]