FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN mkdir /novel-service
COPY ${JAR_FILE} /novel-service/app.jar
ENTRYPOINT ["java","-jar","/novel-service/app.jar"]


