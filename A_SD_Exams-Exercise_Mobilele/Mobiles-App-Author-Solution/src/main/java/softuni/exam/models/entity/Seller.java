package softuni.exam.models.entity;

import javax.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity{

    private String firstName;

    private String lastName;

    private String personalNumber;

    private Set<Sale> sales = new HashSet<>();


    @Column(name = "first_name",nullable = false)
    public String getFirstName() {
        return firstName;
    }
    @Column(name = "last_name",unique = true, nullable = false)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(nullable = false,unique = true)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "personal_number",unique = true, nullable = false)
    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
