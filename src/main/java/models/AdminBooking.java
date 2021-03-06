package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

@Document
public class AdminBooking {

    @Id
    private String id;
    private String bookingId;
    private String userId;
    private String leaseId;
    private String location;
    private String startDate;
    private String endDate;
    private int guests;
    private double totalPrice;
    private Lease bookedStay;

    public AdminBooking() {}
    public AdminBooking(String bookingId, String userId, String leaseId, String location, String startDate, String endDate, int guests, double totalPrice, Lease bookedStay) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.leaseId = leaseId;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guests = guests;
        this.totalPrice = totalPrice;
        this.bookedStay = bookedStay;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "AdminBooking{" +
                "id='" + id + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", userId='" + userId + '\'' +
                ", leaseId='" + leaseId + '\'' +
                ", location='" + location + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", guests=" + guests +
                ", totalPrice=" + totalPrice +
                ", bookedStay=" + bookedStay +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(String leaseId) {
        this.leaseId = leaseId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Lease getBookedStay() {
        return bookedStay;
    }

    public void setBookedStay(Lease bookedStay) {
        this.bookedStay = bookedStay;
    }
}
