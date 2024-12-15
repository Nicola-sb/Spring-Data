package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bikes")
@DiscriminatorValue("bike")
public class Bike extends Vehicle{

    private static final String BIKE_TYPE = "Bike";

    public int getGearsCount() {
        return gearsCount;
    }

    public void setGearsCount(int gearsCount) {
        this.gearsCount = gearsCount;
    }

    private int gearsCount;

    public Bike(int gearsCount) {
        super(BIKE_TYPE, 250);
        this.gearsCount = gearsCount;
    }
}
