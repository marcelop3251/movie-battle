package com.api.battle.resources.repository.entity;

import com.api.battle.domain.entity.Movie;
import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("movie")
public record MovieEntity(@Id Long id, String title, String externalId, BigDecimal rating, BigDecimal votes) {

    public Movie toDomain() {
        return new Movie(id, title, rating, votes);
    }
}
