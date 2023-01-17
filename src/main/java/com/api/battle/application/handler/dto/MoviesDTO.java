package com.api.battle.application.handler.dto;

import com.api.battle.domain.entity.Movie;

public record MoviesDTO(Long id, String title) {

  public MoviesDTO(Movie movie) {
    this(movie.id(), movie.title());
  }
}
