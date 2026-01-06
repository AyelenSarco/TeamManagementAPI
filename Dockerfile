FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/teamManagement-0.0.1.jar
COPY ${JAR_FILE} app_teamManagement.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app_teamManagement.jar"]