package com.api.battle.domain.usecase;

import com.api.battle.domain.entity.ControlQuiz;
import com.api.battle.domain.entity.Quiz;
import com.api.battle.domain.entity.Status;
import com.api.battle.domain.entity.User;
import com.api.battle.domain.exception.QuizExceptionNotFound;
import com.api.battle.domain.exception.TypeException;
import com.api.battle.domain.repository.ControlQuizRepository;
import com.api.battle.domain.repository.QuizAnswerRepository;
import com.api.battle.domain.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FindQuizUseCase {

  @Autowired
  private SearchUserByNameUseCase searchUserByNameUseCase;

  @Autowired
  private QuizAnswerRepository quizAnswerRepository;

  @Autowired
  private QuizRepository quizRepository;

  @Autowired
  private ControlQuizRepository controlQuizRepository;

  public Mono<Quiz> execute(String userName) {
    return searchUserByNameUseCase.execute(userName)
      .flatMap(user ->  controlQuizRepository.findByUserIdAndStatus(user.id(), Status.STARTED.name())
        .switchIfEmpty(Mono.error(new QuizExceptionNotFound("Quiz not started", TypeException.QUIZ_NOT_FOUND)))
        .flatMap(controlQuiz -> quizAnswerRepository.findByControlQuiz(controlQuiz.id())
          .flatMap(quizAnswer -> quizRepository.findById(quizAnswer.quizId() + 1))))
          .switchIfEmpty(Mono.defer(() -> quizRepository.findFirst()));
  }
}
