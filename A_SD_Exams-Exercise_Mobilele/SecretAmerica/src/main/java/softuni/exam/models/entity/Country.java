package softuni.exam.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity{

    @Column
    @Positive
    private double area;
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<Attraction> attractions;

    public Country() {
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(Set<Attraction> attractions) {
        this.attractions = attractions;
    }
}
