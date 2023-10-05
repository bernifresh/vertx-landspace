package jamesbrian.http;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.mutiny.core.http.HttpServer;
import io.vertx.mutiny.ext.web.Router;
import jamesbrian.http.handler.user.UserResponseHandler;

public class HttpVerticle extends AbstractVerticle {

  @Override
  public Uni<Void> asyncStart() {
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    router.route("/user").handler(UserResponseHandler::userDetails);

    return server
        .requestHandler(router)
        .listen(8080)
        .onItem()
        .invoke(() -> System.out.println("Server started"))
        .onFailure()
        .invoke(throwable -> log.error("Error occurred: " + throwable.toString()))
        .replaceWithVoid();
  }

  public static final Logger log = LoggerFactory.getLogger(HttpServer.class);
}
