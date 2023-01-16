package com.api.battle.resources.repository.entity;

import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public record Movie(@Id Long id, String title, String externalId, BigDecimal rating, BigDecimal votes) {
}
