package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "library_members")
public class LibraryMember extends BaseEntity {

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public LibraryMember setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public LibraryMember setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(nullable = true)
    public String getAddress() {
        return address;
    }

    public LibraryMember setAddress(String address) {
        this.address = address;
        return this;
    }

    @Column(nullable = false, unique = true)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LibraryMember setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
