FROM openjdk:17-alpine
VOLUME [ "/tmp" ]
EXPOSE 8080
ARG JAR_FILE=target/crudapplication.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]