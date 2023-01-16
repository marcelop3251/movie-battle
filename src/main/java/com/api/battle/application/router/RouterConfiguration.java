package com.api.battle.application.router;

import com.api.battle.application.handler.HandlerGamer;
import com.api.battle.application.handler.HandlerHealthCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import org.springframework.web.reactive.function.server.RouterFunction;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class RouterConfiguration {

  @Autowired
  private HandlerGamer handlerGamer;

  @Autowired
  private HandlerHealthCheck handlerHealthCheck;

  @Autowired
  private ExceptionHandler exceptionHandler;

  @Bean
  RouterFunction<?> routes() {
    return route(GET("/healthy"), handlerHealthCheck::healthCheck)
      .andRoute(POST("/quiz"), handlerGamer::startQuiz)
      .filter((request, next) -> {
        return next.handle(request)
          .onErrorResume(exception -> exceptionHandler.handler(exception));
      });
  }

  private Mono<ServerResponse> getFallbackValue() {
    return  ServerResponse.status(400).build();
  }

}
