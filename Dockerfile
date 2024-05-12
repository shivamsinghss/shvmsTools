FROM  eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} shvmsTools.jar
ENV TZ=Asia/kolkata
ENTRYPOINT ["java", "-jar", "/shvmsTools.jar"]
