FROM airhacks/java
COPY target/vertx-node.jar /opt/vertx-node.jar
ENTRYPOINT ["java","-Xmx512m", "-jar","/opt/vertx-node.jar"]
EXPOSE 8585