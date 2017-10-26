package io.project.application;

import io.vertx.core.Vertx;
import io.project.resources.StaticServer;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories("io.project.repositories")
@EnableTransactionManagement(proxyTargetClass = true)
@Import(SpringConfig.class)
@ComponentScan(basePackages = {"io"}, excludeFilters = {
    @ComponentScan.Filter(Configuration.class)})
@EntityScan("io.project.model")
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Autowired
    private StaticServer staticServer;

    @PostConstruct
    public void deployVerticle() {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(staticServer);

    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
