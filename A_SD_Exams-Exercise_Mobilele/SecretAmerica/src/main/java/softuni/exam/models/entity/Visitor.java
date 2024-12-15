package softuni.exam.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "visitors")
public class Visitor extends BaseEntity {

    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;

//    @OneToOne така го бях направил аз
    @ManyToOne(optional = false)
    @JoinColumn(name = "attraction_id",nullable = false)
    private Attraction attraction;

    @OneToOne(optional = false)
    private PersonalData personalData;



    @ManyToOne (optional = false)//това също го бях добавил
    @JoinColumn(name = "country_id",nullable = false)
    private Country country;


    public Visitor() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}