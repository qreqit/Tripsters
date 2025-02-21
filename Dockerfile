# Builder stage
FROM maven:3.8.8-eclipse-temurin-17 AS builder
WORKDIR /app

# Копіюємо pom.xml і встановлюємо залежності (це прискорить кешування)
COPY pom.xml .
RUN mvn dependency:go-offline

# Копіюємо весь код і збираємо проєкт
COPY src ./src
RUN mvn clean package -DskipTests

# Отримуємо JAR файл
ARG JAR_FILE=target/*.jar
RUN mkdir -p /app/application
RUN cp ${JAR_FILE} /app/application/application.jar
WORKDIR /app/application
RUN java -Djarmode=layertools -jar application.jar extract

# Final stage
FROM openjdk:17-jdk-slim
WORKDIR application
COPY --from=builder /app/application/dependencies/ ./
COPY --from=builder /app/application/spring-boot-loader/ ./
COPY --from=builder /app/application/snapshot-dependencies/ ./
COPY --from=builder /app/application/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
EXPOSE 8080
