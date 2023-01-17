package com.api.battle.resources.repository.entity;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("quiz")
public record QuizEntity(@Id Long id, List<Long> movieId) {
}
