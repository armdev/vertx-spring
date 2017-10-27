#FROM openjdk:8-jdk-alpine
FROM airhacks/java
VOLUME /tmp
ADD target/eureka-node.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8761