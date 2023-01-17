package com.api.battle.application.handler.dto;

import com.api.battle.domain.entity.Quiz;
import java.util.List;
import java.util.stream.Collectors;

public record QuizDTO(Long id, List<MoviesDTO> movies) {

  public QuizDTO(Quiz quiz) {
    this(quiz.id(), quiz.movies()
      .stream()
      .map(MoviesDTO::new)
      .collect(Collectors.toList()));
  }
}
