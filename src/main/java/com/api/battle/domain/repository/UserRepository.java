package com.api.battle.domain.repository;

import com.api.battle.domain.entity.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

  public Mono<User> findByUserName(String userName);
}
