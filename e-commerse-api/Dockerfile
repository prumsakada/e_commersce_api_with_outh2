# Runtime stage only - assumes JAR is already built
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY build/libs/*.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
