package com.gbabler.challenge_one.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "abstract")
    private String abztract;

    @Column(name = "summary")
    private String summary;

    @Column(name = "price")
    private Float price;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    public Book(String title, String abztract, String summary, Float price, Integer numberOfPages, String isbn,
                LocalDate releaseDate, Category category, Author author) {
        this.title = title;
        this.abztract = abztract;
        this.summary = summary;
        this.price = price;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
        this.category = category;
        this.author = author;
    }

    @Deprecated
    public Book() {
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public String getAbztract() {
        return abztract;
    }

    public String getSummary() {
        return summary;
    }

    public Float getPrice() {
        return price;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCategory(Category categoryId) {
        this.category = categoryId;
    }

    public Category getCategory() {
        return category;
    }
}
