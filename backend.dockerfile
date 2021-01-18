FROM adoptopenjdk:11-jre-hotspot

COPY shop/target/*.jar application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]