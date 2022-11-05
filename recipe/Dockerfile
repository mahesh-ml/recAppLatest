#Start with a base image containing Java runtime
FROM openjdk:18-slim as build


# Add the application's jar to the container
COPY target/recipe-0.0.1-SNAPSHOT.jar recipe-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/recipe-0.0.1-SNAPSHOT.jar"]