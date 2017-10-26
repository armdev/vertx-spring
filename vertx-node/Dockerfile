FROM airhacks/java
COPY target/vertx-spring-node.jar /opt/vertx-spring-node.jar
ENTRYPOINT ["java","-Xmx512m", "-jar","/opt/vertx-spring-node.jar"]
EXPOSE 8080