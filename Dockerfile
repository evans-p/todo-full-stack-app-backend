FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package spring-boot:repackage -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.war ./app.war
EXPOSE 8080
CMD ["java", "-jar", "app.war"]