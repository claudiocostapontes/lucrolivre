FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copia qualquer jar que esteja na pasta target
COPY target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]