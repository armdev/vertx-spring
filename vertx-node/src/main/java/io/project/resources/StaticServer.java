package io.project.resources;

import io.project.application.AppConfiguration;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.bridge.BridgeEventType;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.project.repositories.UserRepository;

@Component
public class StaticServer extends AbstractVerticle {

    private Map<String, JsonObject> products = new HashMap<>();

    @Autowired
    AppConfiguration configuration;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        ///event bus
        BridgeOptions options = new BridgeOptions().addOutboundPermitted(new PermittedOptions().setAddress("news-feed"));

        router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options, event -> {
            if (event.type() == BridgeEventType.SOCKET_CREATED) {
                System.out.println("A socket was created");
            }
            event.complete(true);
        }));
        
        

        setUpInitialData();
        //rest
        router.route().handler(BodyHandler.create());
        
        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.GET)
                .allowedHeader("Content-Type"));

        router.get("/api/products/:productID").handler(this::handleGetProduct);

        router.put("/api/products/:productID").handler(this::handleAddProduct);

        router.get("/api/products").handler(this::handleListProducts);

        router.get("/api/users").handler(this::handleFlights);

        router.get("/api/health").handler(ctx -> {
            ctx.response().end("I'm ok, I hope you are also ok");
        });
        
        router.route("/*").handler(StaticHandler.create());

        vertx.setPeriodic(1000, t -> vertx.eventBus().publish("news-feed", "news from the server!"));
        vertx.createHttpServer().requestHandler(router::accept).listen(configuration.httpPort());

    }

    private void handleGetProduct(RoutingContext routingContext) {
        String productID = routingContext.request().getParam("productID");
        HttpServerResponse response = routingContext.response();
        if (productID == null) {
            sendError(400, response);
        } else {
            JsonObject product = products.get(productID);
            if (product == null) {
                sendError(404, response);
            } else {
                response.putHeader("content-type", "application/json").end(product.encodePrettily());
            }
        }
    }

    private void handleAddProduct(RoutingContext routingContext) {
        String productID = routingContext.request().getParam("productID");
        HttpServerResponse response = routingContext.response();
        if (productID == null) {
            sendError(400, response);
        } else {
            JsonObject product = routingContext.getBodyAsJson();
            if (product == null) {
                sendError(400, response);
            } else {
                products.put(productID, product);
                response.end();
            }
        }
    }

    private void handleListProducts(RoutingContext routingContext) {
        System.out.println(System.currentTimeMillis());
        long startTime = System.currentTimeMillis();
        JsonArray arr = new JsonArray();
        products.forEach((k, v) -> arr.add(v));
        routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime + " ms or  ");
        long seconds = TimeUnit.MILLISECONDS.toSeconds(totalTime);
        //System.out.println("Total time: " + totalTime + " ms or  " + seconds + " seconds");
    }

    private void handleFlights(RoutingContext routingContext) {
        long startTime = System.currentTimeMillis();
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(userRepository.findAll()));

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime + " ms or  ");
    }

    private void sendError(int statusCode, HttpServerResponse response) {
        response.setStatusCode(statusCode).end();
    }

    private void setUpInitialData() {
        addProduct(new JsonObject().put("id", "prod3568").put("name", "Egg Whisk").put("price", 3.99).put("weight", 150));
        addProduct(new JsonObject().put("id", "prod7340").put("name", "Tea Cosy").put("price", 5.99).put("weight", 100));
        addProduct(new JsonObject().put("id", "prod8643").put("name", "Spatula").put("price", 1.00).put("weight", 80));
        addProduct(new JsonObject().put("id", "prod8644").put("name", "Gazan").put("price", 1.00).put("weight", 80));
        addProduct(new JsonObject().put("id", "prod8645").put("name", "Gazan").put("price", 1.00).put("weight", 80));
        addProduct(new JsonObject().put("id", "prod8646").put("name", "Gazan").put("price", 1.00).put("weight", 80));
        addProduct(new JsonObject().put("id", "prod8647").put("name", "Gazan").put("price", 1.00).put("weight", 80));
        addProduct(new JsonObject().put("id", "prod8648").put("name", "Gazan").put("price", 1.00).put("weight", 80));
        addProduct(new JsonObject().put("id", "prod8649").put("name", "Gazan").put("price", 1.00).put("weight", 80));
        addProduct(new JsonObject().put("id", "prod8650").put("name", "Gazan").put("price", 1.00).put("weight", 80));

    }

    private void addProduct(JsonObject product) {
        products.put(product.getString("id"), product);
    }
}
