package com.gbabler.challenge_one.repository;

import com.gbabler.challenge_one.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

    Optional<Author> findByEmail(String email);
}
