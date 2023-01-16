package com.api.battle.resources.repository;

import com.api.battle.domain.entity.User;
import com.api.battle.domain.repository.UserRepository;
import com.api.battle.resources.repository.entity.UserEntity;
import com.api.battle.resources.repository.spring.UserRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserRepositoryImpl implements UserRepository {

  @Autowired
  private UserRepositorySpring repositorySpring;

  @Override
  public Mono<User> findByUserName(String userName) {
    return repositorySpring.findByName(userName)
      .map(UserEntity::toDomain);
  }
}
