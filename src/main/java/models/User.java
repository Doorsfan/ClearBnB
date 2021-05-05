package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

// use with collections
@Document
public class User {
  
  @Id // uuid for this document
  private String id;
  private String username;
  private String password;

  // default constructor
  public User() { }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public String toString() {
    return "\nUser{" +
        "id='" + id + '\'' +
        ", username='" + username + '\'' +
        '}';
  }

  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return username;
  }
  
  public void setEmail(String email) {
    this.username = username;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
}
