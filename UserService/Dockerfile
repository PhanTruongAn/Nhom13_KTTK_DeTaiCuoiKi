FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN mkdir /user-service
COPY ${JAR_FILE} /user-service/app.jar
ENTRYPOINT ["java","-jar","/user-service/app.jar"]


