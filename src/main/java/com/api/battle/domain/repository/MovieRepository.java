package com.api.battle.domain.repository;

import com.api.battle.domain.entity.Movie;
import reactor.core.publisher.Mono;

public interface MovieRepository {

  public Mono<Movie> findMovieById(Long id);
}
