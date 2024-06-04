FROM openjdk:20-jdk-oracle
LABEL authors="Dani"

COPY target/library-0.0.1-SNAPSHOT.jar /app/library.jar

WORKDIR /app

EXPOSE 8080
CMD ["java", "-jar", "library.jar"]
