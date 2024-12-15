package softuni.exam.models.entity;

import softuni.exam.models.entity.enums.Genre;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    private String title;
    private String author;
    private String description;
    private boolean available;
    private Genre genre;
    private Double rating;

    @Column(nullable = false, unique = true)
    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    @Column(nullable = false)
    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Book setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(nullable = false)
    public boolean isAvailable() {
        return available;
    }

    public Book setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Genre getGenre() {
        return genre;
    }

    public Book setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    @Column(nullable = false)
    public Double getRating() {
        return rating;
    }

    public Book setRating(Double rating) {
        this.rating = rating;
        return this;
    }
}
