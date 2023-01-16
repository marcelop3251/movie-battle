package com.api.battle.application.handler.dto;

import com.api.battle.domain.entity.ControlQuiz;

public record ControlQuizDTO(Long id, Long user, String status) {

  public ControlQuizDTO(ControlQuiz controlQuiz) {
    this(controlQuiz.id(), controlQuiz.userId(), controlQuiz.status().name());
  }
}
