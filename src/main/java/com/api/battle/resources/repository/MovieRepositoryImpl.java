package com.api.battle.resources.repository;

import com.api.battle.domain.entity.Movie;
import com.api.battle.domain.repository.MovieRepository;
import com.api.battle.resources.repository.entity.MovieEntity;
import com.api.battle.resources.repository.spring.MovieRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MovieRepositoryImpl implements MovieRepository {

  @Autowired
  private MovieRepositorySpring movieRepositorySpring;

  @Override
  public Mono<Movie> findMovieById(Long id) {
    return movieRepositorySpring.findById(id).map(MovieEntity::toDomain);
  }
}
