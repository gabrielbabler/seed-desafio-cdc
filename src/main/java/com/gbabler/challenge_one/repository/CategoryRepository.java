package com.gbabler.challenge_one.repository;

import com.gbabler.challenge_one.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByName(String name);
}
