package com.api.battle.domain.repository;

import com.api.battle.domain.entity.ControlQuiz;
import reactor.core.publisher.Mono;

public interface ControlQuizRepository {

  public Mono<ControlQuiz> findByUserIdAndStatus(Long userId, String status);

  public Mono<ControlQuiz> create(ControlQuiz controlQuiz);
}
