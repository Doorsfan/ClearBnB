package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

// use with collections
@Document
public class User {
  
  @Id // uuid for this document
  private String id;
  private String email;
  private String password;

  // default constructor
  public User() { }

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  @Override
  public String toString() {
    return "\nUser{" +
        "id='" + id + '\'' +
        ", email='" + email + '\'' +
        '}';
  }

  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
}
