package sofuni.exam.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "moons")
public class Moon extends BaseEntity{


    @Column(nullable = false)
    private LocalDate discovered;

    @Column(name = "distance_from_planet")
    private Integer distanceFromPlanet;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private Double radius;

    @ManyToOne(optional = false)
    private Planet planet;

    @ManyToOne(optional = false)
    private Discoverer discoverer;

    public Moon() {
    }

    public LocalDate getDiscovered() {
        return discovered;
    }

    public void setDiscovered(LocalDate discovered) {
        this.discovered = discovered;
    }

    public Integer getDistanceFromPlanet() {
        return distanceFromPlanet;
    }

    public void setDistanceFromPlanet(Integer distanceFromPlanet) {
        this.distanceFromPlanet = distanceFromPlanet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Discoverer getDiscoverer() {
        return discoverer;
    }

    public void setDiscoverer(Discoverer discoverer) {
        this.discoverer = discoverer;
    }
}
