package softuni.exam.models.entity;

import javax.persistence.*;
import softuni.exam.models.enums.DeviceType;



@Entity
@Table(name = "devices")
public class Device extends BaseEntity{

    private String brand;

    private DeviceType deviceType;
    private String model;

    private Double price;
    private int storage;

    private Sale sale;

    @Column(nullable = false)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    @Column(name = "device_type")
    @Enumerated(EnumType.STRING)
    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    @Column(nullable = false, unique = true)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    @Column
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column
    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    @ManyToOne
    public Sale getSale() {
        return sale;
    }

    public Device setSale(Sale sale) {
        this.sale = sale;
        return this;
    }
}
