package com.api.battle.resources.repository.spring;

import com.api.battle.resources.repository.entity.ControlQuizEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ControlQuizRepositorySpring extends ReactiveCrudRepository<ControlQuizEntity, Long> {

  Mono<ControlQuizEntity> findByUserIdAndStatus(Long userId, String status);
}
