FROM docker.io/openjdk:11.0
WORKDIR /app
COPY ./target/inventarios-api-1.0-SNAPSHOT.jar /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","inventarios-api-1.0-SNAPSHOT.jar"]
