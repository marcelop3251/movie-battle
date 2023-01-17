package com.api.battle.resources.repository.entity;

import com.api.battle.domain.entity.Hit;
import com.api.battle.domain.entity.QuizAnswer;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("quiz_answer")
public class QuizAnswerEntity {
  @Id
  private Long id;
  private Long controlQuizId;
  private Long quizId;
  private String hit;

  public QuizAnswerEntity(QuizAnswer quizAnswer) {
    this.id = quizAnswer.id();
    this.controlQuizId = quizAnswer.controlQuizId();
    this.quizId = quizAnswer.quizId();
    this.hit = quizAnswer.hit().name();
  }

  public QuizAnswerEntity() { }

  public QuizAnswer toDomain() {
    if (hit != null) {
      return new QuizAnswer(id, controlQuizId, quizId, Hit.valueOf(hit));
    } else {
      return new QuizAnswer(id, controlQuizId, quizId);
    }
  }
}
