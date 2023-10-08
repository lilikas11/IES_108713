FROM maven:3.6.3-openjdk-11 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src


RUN mvn dependency:purge-local-repository clean install

RUN mvn clean install

FROM openjdk:11-jre-slim

WORKDIR /app
COPY --from=builder /app/target/seu-projeto.jar .

CMD ["java", "-jar", "seu-projeto.jar"]
