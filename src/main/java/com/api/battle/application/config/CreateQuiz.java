package com.api.battle.application.config;

import com.api.battle.resources.repository.entity.MovieEntity;
import com.api.battle.resources.repository.entity.QuizEntity;
import com.api.battle.resources.repository.spring.MovieRepositorySpring;
import com.api.battle.resources.repository.spring.QuizRepositorySpring;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class CreateQuiz implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private MovieRepositorySpring repository;

  @Autowired
  private QuizRepositorySpring quizRepository;


  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {

        System.out.println("Start quiz");
        repository.findAll().collectList().map(this::createQuizesFromList)
          .flux()
          .flatMap(quizFlux -> {
            Collections.shuffle(quizFlux);
            return quizRepository.saveAll(quizFlux);
          }).doOnComplete(() -> {
            System.out.println("Quiz criado com sucesso");
          }).subscribe();

      }

      private List<QuizEntity> createQuizesFromList(List<MovieEntity> listMovies) {
        long sequence = 1;
        List<QuizEntity> quiz = new ArrayList<>();
        for (long i = 0; i < listMovies.size(); i = 0) {
          for (int j = 1; j < listMovies.size(); j++) {
            QuizEntity o = new QuizEntity(null, Arrays.asList(sequence, listMovies.get(j).id()));
            quiz.add(o);
          }
          sequence++;
          listMovies.remove(0);
        }
        return quiz;
      }
}
