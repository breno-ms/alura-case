FROM openjdk:21-jdk

COPY target/alura-case.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "alura-case.jar"]