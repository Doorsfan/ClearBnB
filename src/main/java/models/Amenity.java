package models;

// DO NOT preface with @Document. Used by Lease
public class Amenity {

    private String wifi;
    private String kitchen;
    private String washer;
    private String heating;
    private String airConditioner;

    @Override
    public String toString() {
        return "Amenity{" +
                "wifi=" + wifi +
                ", kitchen=" + kitchen +
                ", washer=" + washer +
                ", heating=" + heating +
                ", airConditioner=" + airConditioner +
                '}';
    }

    public Amenity() {}

    public Amenity(String wifi, String kitchen, String washer, String heating, String airConditioner) {
        this.wifi = wifi;
        this.kitchen = kitchen;
        this.washer = washer;
        this.heating = heating;
        this.airConditioner = airConditioner;
    }

    public String isWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String isKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String isWasher() {
        return washer;
    }

    public void setWasher(String washer) {
        this.washer = washer;
    }

    public String isHeating() {
        return heating;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    public String isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(String airConditioner) {
        this.airConditioner = airConditioner;
    }
}
