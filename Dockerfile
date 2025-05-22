#Stage 1: Build using maven
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

#Copy project files
COPY . .

#Copy and generate the .jar of the main module
RUN mvn clean install -DskipTest

#Stage 2: Final image more slight
FROM eclipse-temurin:17-jdk

WORKDIR /app

#Copy the generated jar from the last stage
COPY --from=build /app/pokedex-api-app/target/pokedex-api-1.0.0.jar app.jar

#Expose the port 
EXPOSE 8080

#Excecute the application
ENTRYPOINT ["java", "-jar", "app.jar"]