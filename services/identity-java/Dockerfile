FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
# Make sure you've run 'mvn clean package' before building this!
COPY target/*.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]