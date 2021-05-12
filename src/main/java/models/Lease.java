package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

import java.util.Arrays;
import java.util.List;

@Document
public class Lease {
    @Id
    private String id;
    private String ownerId;
    private String title;
    private String location;
    private String description;
    private String typeOfHousing;
    private boolean entireResidence;
    private String startDate;
    private String endDate;
    private double price;
    private int maxGuests;
    private int beds;
    private String[] amenities; // Or should I be using a map?
    private List<String> imageURLs;

    public Lease() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeOfHousing() {
        return typeOfHousing;
    }

    public void setTypeOfHousing(String typeOfHousing) {
        this.typeOfHousing = typeOfHousing;
    }

    public boolean isEntireResidence() {
        return entireResidence;
    }

    public void setEntireResidence(boolean entireResidence) {
        this.entireResidence = entireResidence;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public String[] getAmenities() {
        return amenities;
    }

    public void setAmenities(String[] amenities) {
        this.amenities = amenities;
    }

    public List<String> getImageURLs() {
        return imageURLs;
    }

    @Override
    public String toString() {
        return "Lease{" +
                "id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", typeOfHousing='" + typeOfHousing + '\'' +
                ", entireResidence=" + entireResidence +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", price=" + price +
                ", maxGuests=" + maxGuests +
                ", beds=" + beds +
                ", amenities=" + Arrays.toString(amenities) +
                ", imageURLs=" + imageURLs +
                '}';
    }
}
