FROM openjdk:8-jre-slim

ADD target/*.jar app.jar

RUN bash -c 'touch /app.jar'

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom", "-Xdebug", "-Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=n", "-jar", "app.jar"]