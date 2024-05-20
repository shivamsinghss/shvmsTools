FROM openjdk:17-alpine

ARG JAR_FILE=build/libs/shvmsTools-0.0.1-SNAPSHOT.jar

WORKDIR /app

COPY ${JAR_FILE} app.jar

ENV TZ=Asia/Kolkata

ENTRYPOINT ["java", "-jar", "app.jar"]
