# Use an official openjdk image as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the application JAR file into the Docker image
COPY target/UserService-0.0.1-SNAPSHOT.jar /app/UserService.jar
# Database connection settings for MySQL
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql-userservice:3306/userdb
ENV SPRING_DATASOURCE_USERNAME=user
ENV SPRING_DATASOURCE_PASSWORD=root
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver

# JPA and Hibernate settings
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_SHOW_SQL=true
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQLDialect

# Application settings
ENV SPRING_APPLICATION_NAME=user-service
ENV SERVER_PORT=3030

# Flyway settings
ENV SPRING_FLYWAY_ENABLED=false

# Service Discovery settings
ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka:8761/eureka/
ENV EUREKA_CLIENT_REGISTER_WITH_EUREKA=true
ENV EUREKA_CLIENT_FETCH_REGISTRY=true
EXPOSE 3030

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app/UserService.jar"]