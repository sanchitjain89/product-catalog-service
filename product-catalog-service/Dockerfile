# FROM maven:3.8.5-openjdk-17 # for Java 17
# Use an official Maven image as the base image
FROM maven:3.9.9-eclipse-temurin-17

# Set the working directory inside the container
WORKDIR /app

# Copy the project's pom.xml and dependency-related files to the container
COPY pom.xml ./

# Pre-download dependencies (caching optimization step)
RUN mvn dependency:resolve

# Copy the rest of the application code
COPY src ./src

# Package the application (compile and package into a JAR)
RUN mvn package

# Command to run the Spring Boot application
CMD ["mvn", "spring-boot:run"]
