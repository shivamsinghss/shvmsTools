FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=build/libs/shvmsTools-0.0.1-SNAPSHOT-plain.jar
COPY ${JAR_FILE} app.jar
ENV TZ=Asia/Kolkata
ENTRYPOINT ["java", "-jar", "/app.jar"]
