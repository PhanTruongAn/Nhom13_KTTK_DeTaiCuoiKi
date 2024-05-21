FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN mkdir /chapter-service
COPY ${JAR_FILE} /chapter-service/app.jar
ENTRYPOINT ["java","-jar","/chapter-service/app.jar"]


