package com.example.demo.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    private String title;
    private String description;
    private EditionType editionType;
    private BigDecimal price;
    private Integer copies;
    private LocalDate releaseDate;
    private AgeRestriction ageRestriction;
    //Имам всичките филдове, но трябва да си направя и двете релации
    private Author author;
    private Set<Category> categories;

    public Book(String book) {

    }

    public Book(EditionType editionType, LocalDate releaseDate, Integer copies, BigDecimal price, AgeRestriction ageRestriction, String title, Author author, Set<Category> categories) {
        this.editionType=editionType;
        this.releaseDate=releaseDate;
        this.copies=copies;
        this.price=price;
        this.ageRestriction=ageRestriction;
        this.title=title;
        this.author=author;
        this.categories=categories;
    }

    @ManyToOne
    //В случая правим unidirectional връзка, защото това ми е базовото ентити(тук се държи foreign keya(author_id));
    //За да се държи foreign key, аз правя unidirectional връзката от тази страна от Book към Author
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @ManyToMany
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Book() {
    }

    @Column(name = "title",length = 60, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "description",columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.ORDINAL)
    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Column(name = "copies")
    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }
    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    @Enumerated
    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }
}
