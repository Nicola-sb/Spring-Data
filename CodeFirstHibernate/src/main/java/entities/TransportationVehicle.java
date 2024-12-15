package entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TransportationVehicle extends Vehicle {

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    private int loadCapacity;

    public TransportationVehicle(String type, double price, int loadCapacity) {
        super(type, price);
        this.loadCapacity = loadCapacity;
    }

    public TransportationVehicle() {
    }
}
