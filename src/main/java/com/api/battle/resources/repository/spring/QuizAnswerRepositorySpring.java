package com.api.battle.resources.repository.spring;

import com.api.battle.domain.entity.QuizAnswer;
import com.api.battle.resources.repository.entity.QuizAnswerEntity;
import java.nio.channels.FileChannel;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface QuizAnswerRepositorySpring extends ReactiveCrudRepository<QuizAnswerEntity, Long> {
  Mono<QuizAnswerEntity> findByControlQuizIdAndQuizId(Long controlQuizId, Long quizId);

  @Query("select * from quiz_answer where control_quiz_id = :id order by id desc limit 1")
  Mono<QuizAnswerEntity> findByIdLast(Long id);
}
