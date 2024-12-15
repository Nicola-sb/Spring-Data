package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SellerSeedDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String personalNumber;

    @NotNull
    @Size(min = 2,max = 30)
    public String getFirstName() {
        return firstName;
    }

    public SellerSeedDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotNull
    @Size(min = 2,max = 30)
    public String getLastName() {
        return lastName;
    }

    public SellerSeedDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    @NotNull
    @Size(min = 3, max = 6)
    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }
}
