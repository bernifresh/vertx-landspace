package jamesbrian.http.handler.user;

import io.vertx.mutiny.core.http.HttpServerResponse;
import io.vertx.mutiny.ext.web.RoutingContext;
import jamesbrian.model.user.User;
import java.util.UUID;

public final class UserResponseHandler {
  public static void userDetails(RoutingContext ctx) {
    HttpServerResponse response = ctx.response();

    response.putHeader("content-type", "application/json");

    ctx.jsonAndForget(new User(UUID.randomUUID(), "Bernard", "Largin"));
  }
}
