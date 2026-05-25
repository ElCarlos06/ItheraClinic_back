# Fase 1: Compilar la aplicación saltándose los tests de forma segura
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Fase 2: Ejecutar la aplicación de forma ultra ligera
FROM eclipse-temurin:21-jre-jammy
COPY --from=build /target/*.jar app.jar

# Exponer el puerto del servidor
EXPOSE 8080

# Comando definitivo de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]