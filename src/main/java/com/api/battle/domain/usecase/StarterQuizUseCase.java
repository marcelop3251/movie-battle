package com.api.battle.domain.usecase;

import com.api.battle.domain.entity.ControlQuiz;
import com.api.battle.domain.entity.Status;
import com.api.battle.domain.exception.GameStartException;
import com.api.battle.domain.exception.TypeException;
import com.api.battle.domain.repository.ControlQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StarterQuizUseCase {

  @Autowired
  private SearchUserByNameUseCase searchUserByNameUseCase;

  @Autowired
  private ControlQuizRepository controlQuizRepository;

  private static String USER_ALREADY_STARTED = "User %s has already started the quiz";

  public Mono<ControlQuiz> execute(String userName) {
    return searchUserByNameUseCase.execute(userName)
      .flatMap(user ->  {
        return controlQuizRepository.findByUserIdAndStatus(user.id(), Status.STARTED.name())
          .flatMap(controlQuiz -> {
            if (controlQuiz != null && controlQuiz.isStarted()) {
              return Mono.error(new GameStartException(USER_ALREADY_STARTED.formatted(userName), TypeException.GAME_ALREADY_STARTED));
            }
            return Mono.just(controlQuiz);
          }).switchIfEmpty(Mono.defer(() -> startQuiz(new ControlQuiz(null, user.id(), Status.STARTED))));
      });
  }

  private Mono<ControlQuiz> startQuiz(ControlQuiz controlQuiz) {
    return controlQuizRepository.create(controlQuiz);
  }
}
