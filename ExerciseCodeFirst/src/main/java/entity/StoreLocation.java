package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table ( name = "store_location")
public class StoreLocation extends BaseEntity{

    private String locationName;

    private Set<Sale> sales;

    public StoreLocation() {
    }

    @Column(name = "location_name", nullable = false,unique = true)
    public String getLocationName() {
        return locationName;
    }

    @OneToMany(mappedBy = "storeLocation")
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
