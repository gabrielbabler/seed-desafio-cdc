package com.gbabler.challenge_one.controller;

import com.gbabler.challenge_one.dto.CategoryRequest;
import com.gbabler.challenge_one.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * ICP = 3
 * categoryRepository = 1
 * categoryRequest = 1
 * toModel = 1
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryRepository.save(categoryRequest.toModel());
    }
}