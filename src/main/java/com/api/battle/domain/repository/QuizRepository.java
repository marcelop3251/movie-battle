package com.api.battle.domain.repository;

import com.api.battle.domain.entity.Quiz;
import reactor.core.publisher.Mono;

public interface QuizRepository {

  public Mono<Quiz> findById(Long id);
  public Mono<Quiz> findFirst();
}
