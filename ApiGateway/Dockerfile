FROM openjdk:17
ARG JAR_FILE=target/*.jar
RUN mkdir /gateway-service
COPY ${JAR_FILE} /gateway-service/app.jar
ENTRYPOINT ["java","-jar","/gateway-service/app.jar"]
