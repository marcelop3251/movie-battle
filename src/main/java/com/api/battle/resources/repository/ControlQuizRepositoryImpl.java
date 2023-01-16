package com.api.battle.resources.repository;

import com.api.battle.domain.entity.ControlQuiz;
import com.api.battle.domain.repository.ControlQuizRepository;
import com.api.battle.resources.repository.entity.ControlQuizEntity;
import com.api.battle.resources.repository.spring.ControlQuizRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ControlQuizRepositoryImpl implements ControlQuizRepository {

  @Autowired
  private ControlQuizRepositorySpring repositorySpring;

  @Override
  public Mono<ControlQuiz> findByUserIdAndStatus(Long userId, String status) {
    return repositorySpring.findByUserIdAndStatus(userId, status)
      .map(controlQuizEntity -> controlQuizEntity.toDomain());
  }

  @Override
  public Mono<ControlQuiz> create(ControlQuiz controlQuiz) {
    return repositorySpring.save(new ControlQuizEntity(controlQuiz))
      .map(controlQuizEntity -> controlQuizEntity.toDomain());
  }
}
