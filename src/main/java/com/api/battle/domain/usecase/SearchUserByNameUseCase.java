package com.api.battle.domain.usecase;

import com.api.battle.domain.entity.User;
import com.api.battle.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SearchUserByNameUseCase {

  @Autowired
  private UserRepository userRepository;

  public Mono<User> execute(String userName) {
    return userRepository.findByUserName(userName);
  }
}
