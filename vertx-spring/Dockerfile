FROM airhacks/java
COPY target/vertx-spring.jar /opt/vertx-spring.jar
ENTRYPOINT ["java","-Xmx512m", "-jar","/opt/vertx-spring.jar"]
EXPOSE 8585