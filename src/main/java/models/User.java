package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nosqlite.annotations.Document;
import nosqlite.annotations.Id;
import nosqlite.annotations.Transient;

import java.util.List;

// use with collections
@Document
@JsonIgnoreProperties(ignoreUnknown = true) // don't crash on unknown properties
public class User {
  
  @Id // uuid for this document
  private String id;
  private String firstName;
  private String email;
  private String password;
  
  // ignore this property when saving to the collection
  @Transient
  private List<Cat> cats;
  
  // default constructor
  public User() { }
  
  
  @Override
  public String toString() {
    return "\nUser{" +
        "id='" + id + '\'' +
        ", firstName='" + firstName + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", cats=" + cats +
        '}';
  }
  
  public List<Cat> getCats() {
    return cats;
  }
  
  public void setCats(List<Cat> cats) {
    this.cats = cats;
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
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
