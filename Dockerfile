FROM openjdk:8-jdk-alpine

ADD target/*.jar app.jar

ADD ./startup.sh /

EXPOSE 8080

#ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom", "-Xdebug", "-Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=n", "-jar", "app.jar"]
ENTRYPOINT ["./startup.sh"]