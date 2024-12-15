package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entity.enums.Genre;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class BookSeedDto {

    @Expose
    private String title;

    @Expose
    private String author;

    @Expose
    private String description;

    @Expose
    private boolean available;

    @Expose
    private Genre genre;

    @Expose
    private Double rating;

    @Size(min = 3,max = 40)
    public String getTitle() {
        return title;
    }

    public BookSeedDto setTitle(String title) {
        this.title = title;
        return this;
    }

    @Size(min = 3,max = 40)
    public String getAuthor() {
        return author;
    }

    public BookSeedDto setAuthor(String author) {
        this.author = author;
        return this;
    }

    @Size(min = 5)
    public String getDescription() {
        return description;
    }

    public BookSeedDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isAvailable() {
        return available;
    }

    public BookSeedDto setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public BookSeedDto setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    @Positive
    public Double getRating() {
        return rating;
    }

    public BookSeedDto setRating(Double rating) {
        this.rating = rating;
        return this;
    }
}
