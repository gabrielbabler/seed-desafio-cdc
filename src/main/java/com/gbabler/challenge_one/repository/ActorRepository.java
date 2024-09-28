package com.gbabler.challenge_one.repository;

import com.gbabler.challenge_one.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, String> {

    Optional<Actor> findByEmail(String email);
}
