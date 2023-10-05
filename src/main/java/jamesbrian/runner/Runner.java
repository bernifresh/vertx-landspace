package jamesbrian.runner;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Launcher;
import io.vertx.core.VertxOptions;
import io.vertx.mutiny.core.Vertx;
import jamesbrian.http.HttpVerticle;

public class Runner extends Launcher {

  public static VertxOptions options() {
    var eventLoopPoolSize = 2 * Runtime.getRuntime().availableProcessors();

    return options(eventLoopPoolSize);
  }

  private static VertxOptions options(int eventLoopPoolSize) {
    return new VertxOptions().setEventLoopPoolSize(eventLoopPoolSize);
  }

  public static void main(String[] args) {
    new Launcher().dispatch(args);

    Vertx vertx = Vertx.vertx(Runner.options());

    System.out.println("Start Deployment");
    vertx.deployVerticleAndAwait(HttpVerticle::new, new DeploymentOptions());
    System.out.println("Finished Deployment");
  }
}
