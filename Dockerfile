#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /home/app
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn clean package

#
# Run stage
#
FROM openjdk:17
COPY --from=build /bar-app-api/target/bar-app-api-0.0.1-SNAPSHOT.jar /usr/local/lib/bar-app-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/bar-app-api-0.0.1-SNAPSHOT.jar"]