package io.project.application;

import io.project.verticles.Receiver;
import io.project.verticles.Sender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.unbrokendome.vertx.spring.EnableVertx;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"io.project"})
@EnableVertx
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public Sender senderVerticle() {
        return new Sender();
    }

    @Bean
    public Receiver receiverVerticle() {
        return new Receiver();
    }

}
