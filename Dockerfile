FROM eclipse-temurin:17-jdk-alpine
VOLUME /build
COPY build/libs/shvmsTools-0.0.1-SNAPSHOT-plain.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
