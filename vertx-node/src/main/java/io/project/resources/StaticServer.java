package io.project.resources;

import io.project.application.AppConfiguration;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.dropwizard.DropwizardMetricsOptions;
import io.vertx.ext.dropwizard.MetricsService;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import java.util.Random;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class StaticServer extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaticServer.class);

    @Autowired
    private AppConfiguration configuration;

    @Override
    public void start() {

        MetricsService service = MetricsService.create(vertx);
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.GET)
                .allowedHeader("Content-Type"));
        router.get("/api/v2/health").handler(ctx -> {
            ctx.response().end("I'm ok, I hope you are also ok" + System.currentTimeMillis());
        });

        BridgeOptions options = new BridgeOptions().
                addOutboundPermitted(
                        new PermittedOptions().
                                setAddress("metrics")
                );

        vertx = Vertx.vertx(new VertxOptions().setMetricsOptions(
                new DropwizardMetricsOptions().setEnabled(true)
        ));

        vertx = Vertx.vertx(new VertxOptions().setMetricsOptions(
                new DropwizardMetricsOptions().setJmxEnabled(true)
        ));

        Set<String> metricsNames = service.metricsNames();
        metricsNames.forEach((metricsName) -> {
            System.out.println("Known metrics name::::::## " + metricsName);
        });

        //Router router = Router.router(vertx);

        router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options));

        router.route().handler(StaticHandler.create());

        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(router::accept).listen(configuration.httpPort());

        vertx.setPeriodic(3000, t -> {

            JsonObject metrics = service.getMetricsSnapshot(vertx.eventBus());

            if (metrics != null) {
                // System.out.println(metrics.toString());
                vertx.eventBus().publish("metrics", metrics);
            } else {
                //System.out.println("metrics is null");
            }
        });

        Random random = new Random();
        vertx.eventBus().consumer("whatever", msg -> {
            vertx.setTimer(10 + random.nextInt(50), id -> {
                // System.out.println("send message hello !!");
                vertx.eventBus().send("whatever", "hello");
            });
        });
        vertx.eventBus().send("whatever", "hello");
    }
}
