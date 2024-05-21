FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN mkdir /comment-service
COPY ${JAR_FILE} /comment-service/app.jar
ENTRYPOINT ["java","-jar","/comment-service/app.jar"]


