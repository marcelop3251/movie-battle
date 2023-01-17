package com.api.battle.application.handler;

import com.api.battle.application.handler.dto.AnswerDTO;
import com.api.battle.application.handler.dto.ControlQuizDTO;
import com.api.battle.application.handler.dto.QuizDTO;
import com.api.battle.domain.usecase.AnswerQuizUserCase;
import com.api.battle.domain.usecase.FindQuizUseCase;
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

  @Autowired
  private FindQuizUseCase findQuizUseCase;

  @Autowired
  private AnswerQuizUserCase answerQuizUserCase;

  public Mono<ServerResponse> startQuiz(ServerRequest serverRequest) {
    return serverRequest.principal()
      .map(p -> p.getName())
      .flatMap(user -> starterQuizUseCase.execute(user))
      .flatMap(controlQuiz ->  {
        return ServerResponse.ok().bodyValue(new ControlQuizDTO(controlQuiz));
      });
  }

  public Mono<ServerResponse> getQuiz(ServerRequest serverRequest) {
    return serverRequest.principal()
      .map(p -> p.getName())
      .flatMap(user ->  findQuizUseCase.execute(user)).flatMap(quiz -> {
        return ServerResponse.ok().bodyValue(new QuizDTO(quiz));
      });
  }

  public Mono<ServerResponse> answerQuiz(ServerRequest serverRequest) {
    return serverRequest.bodyToMono(AnswerDTO.class)
      .flatMap(answerDTO -> serverRequest.principal()
        .map(p -> p.getName())
      .flatMap(user -> answerQuizUserCase.execute(answerDTO.firstAnswer(), answerDTO.secondAnswer(), answerDTO.quizId(), user))
        .flatMap(s -> ServerResponse.ok().bodyValue(s)));
  }
}
