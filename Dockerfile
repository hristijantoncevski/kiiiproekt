FROM openjdk:18
EXPOSE 9091
COPY target/kiiiproekt-0.0.1-SNAPSHOT.jar kiiiproekt.jar
ENTRYPOINT ["java", "-jar", "kiiiproekt.jar"]