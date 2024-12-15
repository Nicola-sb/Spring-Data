package softuni.exam.models.dto;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import softuni.exam.models.enums.DeviceType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceSeedDto {

    @XmlElement(name = "brand")
    private String brand;
    @XmlElement(name = "device_type")
    private DeviceType deviceType;
    @XmlElement(name = "model")
    private String model;
    @XmlElement(name = "price")
    private Double price;
    @XmlElement(name = "storage")
    private int storage;
    @XmlElement(name = "sale_id")
    private Long sale;

    @Size(min = 2,max = 20)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    @Size(min = 1,max = 20)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public Long getSale() {
        return sale;
    }

    public DeviceSeedDto setSale(Long sale) {
        this.sale = sale;
        return this;
    }
}
