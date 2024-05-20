FROM openjdk:17-alpine

WORKDIR /app

COPY build/libs/shvmsTools-0.0.1-SNAPSHOT.jar app.jar

ENV TZ=Asia/Kolkata

ENTRYPOINT ["java", "-jar", "app.jar"]
