FROM maven:3.9.1 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package spring-boot:repackage -Pprod

FROM openjdk:24-ea-17-jdk-oraclelinux8
WORKDIR /app
COPY --from=build /app/target/*.war ./app.war
EXPOSE 8080
CMD ["java", "-jar", "app.war"]