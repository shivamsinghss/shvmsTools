FROM eclipse-temurin:17-jdk-alpine
COPY build/libs/shvmsTools-0.0.1-SNAPSHOT.jar shvmsTools.jar
EXPOSE 8080
ENV TZ=Asia/Kolkata
ENTRYPOINT ["java", "-jar", "shvmsTools.jar"]
