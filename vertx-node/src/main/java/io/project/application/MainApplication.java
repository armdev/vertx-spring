package io.project.application;

import io.project.model.Flight;
import io.vertx.core.Vertx;
import io.project.resources.StaticServer;
import io.vertx.ext.web.Router;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient
@EnableAsync
@EnableFeignClients
@EnableJpaRepositories("io.project.repositories")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableScheduling
@EnableHystrix
@EnableCircuitBreaker
//@EnableDiscoveryClient
@EnableCaching
@Import(SpringConfig.class)
//@Import(AppConfiguration.class)
@ComponentScan(basePackages = {"io"}, excludeFilters = {
    @ComponentScan.Filter(Configuration.class)})
@EntityScan(basePackageClasses = Flight.class)
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Autowired
    private StaticServer staticServer;

    @PostConstruct
    public void deployVerticle() {
        // Vertx.vertx().deployVerticle(staticServer);
        //Vertx.vertx().deployVerticle(eventBusResource);        
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);
        vertx.deployVerticle(staticServer);

    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
