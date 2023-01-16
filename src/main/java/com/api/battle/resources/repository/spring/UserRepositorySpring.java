package com.api.battle.resources.repository.spring;

import com.api.battle.resources.repository.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepositorySpring extends ReactiveCrudRepository<UserEntity, Long> {

    Mono<UserEntity> findByName(String username);

}
