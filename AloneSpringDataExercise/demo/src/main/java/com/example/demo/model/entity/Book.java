package com.example.demo.model.entity;

import com.example.demo.model.entity.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Collate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

//•	Book - id, title (between 1...50 symbols), description (optional, up to 1000 symbols), edition type (NORMAL, PROMO or GOLD),
// price, copies, release date (optional), age restriction (MINOR, TEEN or ADULT)

    private String title;
    private String description;
    private EditionType editionType;
    private BigDecimal price;
    private Integer copies;
    private LocalDate releaseDate;
    private AgeRestriction ageRestriction;

    public Book() {
    }

    public Book(String title, String description, EditionType editionType, BigDecimal price, Integer copies, LocalDate releaseDate, AgeRestriction ageRestriction) {
        this.title = title;
        this.description = description;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
    }

    @Column(name = "title",length = 50,nullable = false)
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
     @Column( name = "price")
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
    @Enumerated(EnumType.ORDINAL)
    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }
}
