package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;
import java.util.ArrayList;

// use with collections
@Document
public class User {
  
  @Id // uuid for this document
  private String id;
  private String username;
  private String password;
  private ArrayList<Booking> myBookings;

  // default constructor
  public User() { }

  public User(String username, String password, ArrayList<Booking> myBookings) {
    this.username = username;
    this.password = password;
    this.myBookings = myBookings;
  }

  @Override
  public String toString() {
    return "User{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", myBookings=" + myBookings +
            '}';
  }

  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }

  public ArrayList<Booking> getMyBookings() { return myBookings; }

  public void setMyBookings(ArrayList<Booking> newListOfBookings) { this.myBookings = newListOfBookings; }

  public void addBooking(Booking newBooking) { this.myBookings.add(newBooking); }
}
