package softuni.exam.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class AttractionDto {

    @Size(min = 5, max = 40)
    @NotNull
    private String name;
    @Size(min = 10, max = 100)
    @NotNull
    private String description;
    @Size(min = 3, max = 30)
    @NotNull
    private String type;
    @PositiveOrZero
    private int elevation;

    @NotNull
    private long country;

    public AttractionDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public long getCountry() {
        return country;
    }

    public void setCountry(long country) {
        this.country = country;
    }
}
