package com.gbabler.challenge_one.repository;

import com.gbabler.challenge_one.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
