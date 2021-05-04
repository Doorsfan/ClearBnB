package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

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
    private String startDate;
    private String endDate;
    private double pricePerNight;
    private int maxGuests;
    private int beds;
    private Amenity amenities; // Or should I be using a map?
    private List<String> imageURLs;

}
