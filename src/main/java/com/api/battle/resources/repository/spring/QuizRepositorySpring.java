package com.api.battle.resources.repository.spring;

import com.api.battle.resources.repository.entity.QuizEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepositorySpring extends ReactiveCrudRepository<QuizEntity, Long> {
}
