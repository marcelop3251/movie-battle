package com.api.battle.resources.repository;

import com.api.battle.domain.entity.QuizAnswer;
import com.api.battle.domain.repository.QuizAnswerRepository;
import com.api.battle.resources.repository.entity.QuizAnswerEntity;
import com.api.battle.resources.repository.spring.QuizAnswerRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class QuizAnswerRepositoryImpl implements QuizAnswerRepository {

  @Autowired
  private QuizAnswerRepositorySpring quizAnswerRepositorySpring;

  @Override
  public Mono<QuizAnswer> findByControlQuiz(Long id) {
    return quizAnswerRepositorySpring.findByIdLast(id)
      .map(quizAnswerEntity -> quizAnswerEntity.toDomain());
  }

  @Override
  public Mono<QuizAnswer> create(QuizAnswer quizAnswer) {
    return quizAnswerRepositorySpring.save(new QuizAnswerEntity(quizAnswer))
      .map(quizAnswerEntity -> quizAnswerEntity.toDomain());
  }

  @Override
  public Mono<QuizAnswer> findByControlQuizIdAndQuizId(Long controlQuizId, Long quizId) {
    return quizAnswerRepositorySpring.findByControlQuizIdAndQuizId(controlQuizId, quizId)
      .map(QuizAnswerEntity::toDomain);
  }
}
