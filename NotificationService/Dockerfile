FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN mkdir /notification-service
COPY ${JAR_FILE} /notification-service/app.jar
ENTRYPOINT ["java","-jar","/notification-service/app.jar"]


