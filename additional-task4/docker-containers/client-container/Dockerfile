FROM openjdk:17

ENV PATH_ENV=/home/alex/some_dir \
    HOST_ENV=localhost \
    PORT_ENV=5000

WORKDIR /app

COPY filesyncservice.jar /app/filesyncservice.jar

ENTRYPOINT ["java", "-jar", "/app/filesyncservice.jar"]

