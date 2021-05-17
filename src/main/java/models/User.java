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
  private String userInfo;

  // default constructor
  public User() { }

  public User(String username, String password, String userInfo) {
    this.username = username;
    this.password = password;
    this.userInfo = userInfo;
  }

  @Override
  public String toString() {
    return "User{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", userInfo=" + userInfo +
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

  public String getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(String userInfo) {
    this.userInfo = userInfo;
  }
}
