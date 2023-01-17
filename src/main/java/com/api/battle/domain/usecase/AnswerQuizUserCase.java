package com.api.battle.domain.usecase;

import com.api.battle.domain.entity.ControlQuiz;
import com.api.battle.domain.entity.Hit;
import com.api.battle.domain.entity.QuizAnswer;
import com.api.battle.domain.exception.QuizExceptionNotFound;
import com.api.battle.domain.exception.TypeException;
import com.api.battle.domain.repository.MovieRepository;
import com.api.battle.domain.repository.QuizAnswerRepository;
import com.api.battle.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AnswerQuizUserCase {

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private QuizAnswerRepository quizAnswerRepository;

  @Autowired
  private SearchControlQuizByUserIdUseCase searchControlQuizByUserIdUseCase;

  @Autowired
  private UserRepository userRepository;

  // TODO Receber apenas a resposta escolhida e validar a outra opção a partir do quiz
  // TODO Validar a opção enviada pertenc ao quiz
  public Mono<QuizAnswer> execute(Long firstAnswer, Long secondAnswer, Long quizId, String userName) {
     return movieRepository.findMovieById(firstAnswer).zipWith(movieRepository.findMovieById(secondAnswer)).flatMap(tuple -> {
        return Mono.just(tuple.getT1().highestScoreThan(tuple.getT2()));
      }).flatMap(result -> {
        if (result) {
          return createQuizAnswer(userName, quizId, Hit.YES);
        } else {
          return createQuizAnswer(userName, quizId, Hit.NO);
        }
      });
  }

  private Mono<QuizAnswer> createQuizAnswer(String userName, Long quizId, Hit hit) {
    return userRepository.findByUserName(userName)
      .flatMap(user -> searchControlQuizByUserIdUseCase.execute(user.id())
        .flatMap(controlQuiz -> quizAnswerRepository.findByControlQuizIdAndQuizId(controlQuiz.id(), quizId)
          .flatMap(quizAnswer -> Mono.error(new QuizExceptionNotFound("Quiz already answer", TypeException.QUIZ_NOT_FOUND)))
          .switchIfEmpty(Mono.just(controlQuiz)))
        .flatMap(controlQuiz ->  quizAnswerRepository
          .create(new QuizAnswer(null, ((ControlQuiz) controlQuiz).id(), quizId, hit))));
  }
}
