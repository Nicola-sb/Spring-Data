package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entity.StarType;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class StarSeedDto {

    @Expose
    @Size(min = 6)
    private String description;
    @Expose
    @Size(min = 2, max = 30)
    private String name;
    @Expose
    @Min(0)
    private double lightYears;
    @Expose
    private StarType starType;
    @Expose
    private long constellation;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(double lightYears) {
        this.lightYears = lightYears;
    }

    public StarType getStarType() {
        return starType;
    }

    public void setStarType(StarType starType) {
        this.starType = starType;
    }

    public long getConstellation() {
        return constellation;
    }

    public void setConstellation(long constellation) {
        this.constellation = constellation;
    }
}
