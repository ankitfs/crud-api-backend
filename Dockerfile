FROM openjdk:21
WORKDIR /app
COPY target/crudapplication.jar crudapplication.jar
EXPOSE 8080
CMD ["java", "-jar", "crudapplication.jar"]