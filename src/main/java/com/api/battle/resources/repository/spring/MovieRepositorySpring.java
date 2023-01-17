package com.api.battle.resources.repository.spring;

import com.api.battle.resources.repository.entity.MovieEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepositorySpring extends ReactiveCrudRepository<MovieEntity, Long> {
}
