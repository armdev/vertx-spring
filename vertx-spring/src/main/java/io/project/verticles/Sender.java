package io.project.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class Sender extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        EventBus eb = vertx.eventBus();        
        vertx.setPeriodic(10000, v -> eb.publish("news-feed", "Some news!"));
    }
}
