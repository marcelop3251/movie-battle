package com.api.battle.application.handler;

import com.api.battle.application.handler.dto.ControlQuizDTO;
import com.api.battle.domain.usecase.StarterQuizUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HandlerGamer {

  @Autowired
  private StarterQuizUseCase starterQuizUseCase;

  public Mono<ServerResponse> startQuiz(ServerRequest serverRequest) {
    return serverRequest.principal()
      .map(p -> p.getName())
      .flatMap(user -> starterQuizUseCase.execute(user))
      .flatMap(controlQuiz ->  {
        return ServerResponse.ok().bodyValue(new ControlQuizDTO(controlQuiz));
      });
  }

}
