package com.api.battle.domain.repository;

import com.api.battle.domain.entity.QuizAnswer;
import reactor.core.publisher.Mono;

public interface QuizAnswerRepository {

  Mono<QuizAnswer> findByControlQuiz(Long id);

  Mono<QuizAnswer> create(QuizAnswer quizAnswer);

  Mono<QuizAnswer> findByControlQuizIdAndQuizId(Long controlQuizId, Long quizId);
}
