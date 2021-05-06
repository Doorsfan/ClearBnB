package models;

// DO NOT preface with @Document. Used by Lease
public class Amenity {

    private boolean wifi;
    private boolean kitchen;
    private boolean washer;
    private boolean heating;
    private boolean airConditioner;

    public Amenity() {}

    public Amenity(boolean wifi, boolean kitchen, boolean washer, boolean heating, boolean airConditioner) {
        this.wifi = wifi;
        this.kitchen = kitchen;
        this.washer = washer;
        this.heating = heating;
        this.airConditioner = airConditioner;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public boolean isWasher() {
        return washer;
    }

    public void setWasher(boolean washer) {
        this.washer = washer;
    }

    public boolean isHeating() {
        return heating;
    }

    public void setHeating(boolean heating) {
        this.heating = heating;
    }

    public boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(boolean airConditioner) {
        this.airConditioner = airConditioner;
    }
}
