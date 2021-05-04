package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

@Document
public class Booking {

    @Id
    private String id;
    private String userId;
    private String leaseId;
    private String location;
    private String startDate;
    private String endDate;
    private int guests;
    private double totalPrice;

    public Booking() {}
}
