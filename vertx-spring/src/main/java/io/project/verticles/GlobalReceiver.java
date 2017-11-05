package io.project.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class GlobalReceiver extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        EventBus eb = vertx.eventBus();

        eb.consumer("global-feed", message -> {

            System.out.println("Received message: " + message.body());

            message.reply("signed" + message.body());
        });

        System.out.println("Receiver ready!");
    }
}
