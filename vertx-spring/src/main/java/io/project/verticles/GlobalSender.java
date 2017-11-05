package io.project.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class GlobalSender extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        EventBus eb = vertx.eventBus();
        //vertx.setPeriodic(10000, v -> eb.publish("global-feed", System.currentTimeMillis()));

        vertx.setPeriodic(10000, v -> {

            eb.send("global-feed", System.currentTimeMillis(), reply -> {
                if (reply.succeeded()) {
                    System.out.println("global-feed: received reply " + reply.result().body());
                } else {
                    System.out.println("Global-feed: No reply");
                }
            });

        });
    }
}
