
FROM ghcr.io/graalvm/jdk-community:25i1
WORKDIR /workspace
COPY build/libs/e-commerce-api-0.0.1-SNAPSHOT.jar /workspace/api.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/workspace/api.jar"]
