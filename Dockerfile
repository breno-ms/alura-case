FROM openjdk:21-jdk

COPY src /app/src
COPY pom.xml /app
COPY mvnw /app
COPY .mvn /app/.mvn

WORKDIR /app

RUN ./mvnw clean install
RUN ls -R /app/target
RUN cp target/*.jar /app/alura-case.jar

EXPOSE 8080

CMD ["java", "-jar", "alura-case.jar"]
