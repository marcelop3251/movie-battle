package com.api.battle.application.handler;

import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HandlerHealthCheck {

  public Mono<ServerResponse> healthCheck(ServerRequest serverRequest) {
    return ServerResponse.ok().body(Mono.just("HEALTHY"), String.class);
  }

}
