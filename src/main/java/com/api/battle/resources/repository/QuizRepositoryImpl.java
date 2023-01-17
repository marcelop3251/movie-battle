package com.api.battle.resources.repository;

import com.api.battle.domain.entity.Movie;
import com.api.battle.domain.entity.Quiz;
import com.api.battle.domain.repository.QuizRepository;
import com.api.battle.resources.repository.entity.QuizEntity;
import com.api.battle.resources.repository.spring.MovieRepositorySpring;
import com.api.battle.resources.repository.spring.QuizRepositorySpring;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class QuizRepositoryImpl implements QuizRepository {

  @Autowired
  private QuizRepositorySpring quizRepositorySpring;

  @Autowired
  private MovieRepositorySpring movieRepositorySpring;


  @Override
  public Mono<Quiz> findById(Long id) {
    return quizRepositorySpring.findById(id)
      .flatMap(quizEntity -> {
        return getMoviesAndBuildQuiz(quizEntity);
      });
  }

  @Override
  public Mono<Quiz> findFirst() {
    return quizRepositorySpring.findById(1l).flatMap(quizEntity -> {
      return getMoviesAndBuildQuiz(quizEntity);
    });
  }

  private Mono<Quiz> getMoviesAndBuildQuiz(QuizEntity quizEntity) {
    return Flux.fromArray(quizEntity.movieId().toArray())
      .flatMap(movieId -> movieRepositorySpring.findById((Long) movieId).map(movieEntity -> movieEntity.toDomain()))
      .collectList().map(movies -> new Quiz(quizEntity.id(), movies));
  }
}


