FROM openjdk:17-oracle
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENV TZ=Asia/kolkata
ENTRYPOINT ["java", "-jar", "/app.jar"]
