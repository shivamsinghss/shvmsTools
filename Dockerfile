FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENV TZ=Asia/Kolkata
ENTRYPOINT ["java", "-jar", "/app.jar"]
