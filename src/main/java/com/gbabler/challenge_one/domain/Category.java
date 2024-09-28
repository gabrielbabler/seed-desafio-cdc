package com.gbabler.challenge_one.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    @Deprecated
    public Category() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
