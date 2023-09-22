# imagem base do OpenJDK
FROM openjdk:8-jre

# arquivo JAR
COPY target/AnyCityForecast.jar /app/AnyCityForecast.jar

# diretorio
WORKDIR /app

# Comando bash dividido por espaços
CMD ["java", "-jar", "AnyCityForecast.jar"]