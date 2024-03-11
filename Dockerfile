#
# Build stage
#
FROM maven:3.9.6-eclipse-temurin-20-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:20
COPY --from=build /target/springboot-application-0.0.1-SNAPSHOT.jar springboot-application.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","springboot-application.jar"]