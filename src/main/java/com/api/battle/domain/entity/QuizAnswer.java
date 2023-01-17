package com.api.battle.domain.entity;

public record QuizAnswer(
  Long id,
  Long controlQuizId,
  Long quizId,
  Hit hit
) {

  public QuizAnswer(Long id, Long controlQuizId, Long quizId) {
    this(id, controlQuizId, quizId, null);
  }

}
