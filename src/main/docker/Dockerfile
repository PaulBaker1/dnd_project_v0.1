FROM ubuntu:latest
LABEL authors="Paul"

# Use the official Java runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into the container
COPY target/dnd-session-manager.jar /app

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/dnd-session-manager.jar"]

# Use the official PostgreSQL image from Docker Hub
FROM postgres:13-alpine

# Set environment variables for PostgreSQL setup
ENV POSTGRES_DB=dnd
ENV POSTGRES_USER=dmadmin
ENV POSTGRES_PASSWORD=securepassword

# Expose the default PostgreSQL port
EXPOSE 5432
