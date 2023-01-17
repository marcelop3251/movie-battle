package com.api.battle.domain.usecase;

import com.api.battle.domain.entity.ControlQuiz;
import com.api.battle.domain.entity.Status;
import com.api.battle.domain.exception.QuizExceptionNotFound;
import com.api.battle.domain.exception.TypeException;
import com.api.battle.domain.repository.ControlQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SearchControlQuizByUserIdUseCase {

  @Autowired
  private ControlQuizRepository controlQuizRepository;

  public Mono<ControlQuiz> execute(Long userId) {
    return controlQuizRepository.findByUserIdAndStatus(userId, Status.STARTED.name())
      .switchIfEmpty(Mono.error(new QuizExceptionNotFound("Quiz not started", TypeException.QUIZ_NOT_FOUND)));
  }
}
