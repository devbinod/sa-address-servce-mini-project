FROM maven:latest as builder
WORKDIR /build
RUN rm -rf *
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:17.0.2-jdk as deploy
MAINTAINER BINOD PANT
WORKDIR /app
COPY --from=builder /build/target/*.jar address-service.jar
CMD ["java","-jar","address-service.jar"]