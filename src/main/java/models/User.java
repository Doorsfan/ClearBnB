package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;
import java.util.ArrayList;
import models.Booking;
import models.UserInfo;

// use with collections
@Document
public class User {
  
  @Id // uuid for this document
  private String id;
  private String username;
  private String password;
  private ArrayList<Booking> myBookings = new ArrayList<Booking>();
  private UserInfo userInfo = new UserInfo("","","","","","","","",false);

  // default constructor
  public User() { }

  public User(String username, String password, ArrayList<Booking> myBookings, UserInfo userInfo) {
    this.username = username;
    this.password = password;
    this.myBookings = myBookings;
    this.userInfo = userInfo;
  }

  @Override
  public String toString() {
    return "User{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", myBookings=" + myBookings +
            ", userInfo=" + userInfo +
            '}';
  }

  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
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
