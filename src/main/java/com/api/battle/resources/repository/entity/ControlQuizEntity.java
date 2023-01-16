package com.api.battle.resources.repository.entity;

import com.api.battle.domain.entity.ControlQuiz;
import com.api.battle.domain.entity.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("control_quiz")
public class ControlQuizEntity {

  @Id
  private Long id;
  private Long userId;
  private String status;

  public ControlQuizEntity(Long id, Long userId, String status) {
    this.id = id;
    this.userId = userId;
    this.status = status;
  }
  public ControlQuizEntity() { }

  public ControlQuizEntity(ControlQuiz controlQuiz) {
    this(controlQuiz.id(), controlQuiz.userId(), controlQuiz.status().name());
  }

  public ControlQuiz toDomain() {
    return new ControlQuiz(id, userId, Status.valueOf(status));
  }
}
