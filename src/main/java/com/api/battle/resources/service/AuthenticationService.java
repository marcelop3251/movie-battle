package com.api.battle.resources.service;

import com.api.battle.resources.repository.spring.UserRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthenticationService implements ReactiveUserDetailsService {

    @Autowired
    private UserRepositorySpring userRepositorySpring;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepositorySpring.findByName(username).map(UserDetaisService::new);
    }
}
