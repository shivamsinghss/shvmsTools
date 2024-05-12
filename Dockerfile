FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE}/shvmsTools-0.0.1-SNAPSHOT.jar shvmsTools.jar
EXPOSE 8080
ENV TZ=Asia/Kolkata
ENTRYPOINT ["java", "-jar", "/shvmsTools.jar"]

