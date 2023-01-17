package com.api.battle.domain.entity;

import java.math.BigDecimal;

public record Movie(Long id, String title, BigDecimal rating, BigDecimal votes) {

  public Boolean highestScoreThan(Movie movie) {
    var score = getScore().compareTo(movie.getScore());
    // TODO faltou cobrir os cenário de score == 0 onde devemos ter um verediot, apesas de ser um cenário remoto
    if (score > 0) {
      return true;
    } else {
      return false;
    }
  }

  public BigDecimal getScore() {
    return rating.multiply(votes);
  }
}
