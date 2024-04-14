FROM openjdk:21
COPY . /app
WORKDIR /app
ENTRYPOINT ["java", "-jar", "target/shopAccountingSystem-0.0.1-SNAPSHOT.jar"]