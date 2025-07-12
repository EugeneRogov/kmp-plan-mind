FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy Gradle files
COPY gradle/ gradle/
COPY gradlew .
COPY gradlew.bat .
COPY gradle.properties .
COPY settings.gradle.kts .
COPY build.gradle.kts .

# Copy shared module
COPY shared/ shared/

# Copy server module
COPY server/ server/

# Make gradlew executable
RUN chmod +x gradlew

# Build the application with shadowJar
RUN ./gradlew server:build -x test

# Copy the built jar
RUN cp server/build/libs/server-all.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]