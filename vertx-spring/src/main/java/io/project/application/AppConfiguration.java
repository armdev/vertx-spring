package io.project.application;

import io.project.verticles.GlobalReceiver;
import io.project.verticles.GlobalSender;
import org.springframework.context.annotation.Configuration;
import org.unbrokendome.vertx.spring.SpringVertx;
import org.unbrokendome.vertx.spring.VertxConfigurer;

@Configuration
public class AppConfiguration implements VertxConfigurer {

    @Override
    public void configure(SpringVertx.Builder builder) {
        builder
                // Register a custom Verticle for deployment
                .verticle(new GlobalReceiver())
                .verticle(new GlobalSender())
                // Modify the VertxOptions
                .options(opt -> opt.setClustered(false));
    }
}
