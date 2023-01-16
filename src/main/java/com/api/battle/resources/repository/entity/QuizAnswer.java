package com.api.battle.resources.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public record QuizAnswer(@Id Long id, Long controlQuizId, Long quizId, String hit, Long optionId) {
}
