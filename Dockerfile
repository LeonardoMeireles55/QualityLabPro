FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /usr/src/app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/QualityLabPro-0.0.1-beta.jar app.jar
CMD ["java","-jar", "-Dspring.profiles.active=prod","app.jar"]
# CMD ["sh", "-c", "cp -r /home/leonardo/IdeaProjects/QualityLabPro/target/* /usr/src/app/ && mvn clean package -DskipTests && java -jar -Dspring.profiles.active=prod app.jar"]
