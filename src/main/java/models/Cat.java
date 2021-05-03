package models;

import nosqlite.annotations.Document;
import nosqlite.annotations.Id;

@Document
public class Cat {
  
  @Id
  private String id;
  private String name;
  private String color;
  private int age;
  private String ownerId;
  
  public Cat() {
    this.name = name;
    this.color = color;
    this.age = age;
    this.ownerId = ownerId;
  }
  
  @Override
  public String toString() {
    return "\nCat{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", color='" + color + '\'' +
        ", age=" + age +
        '}';
  }
  
  public String getOwnerId() {
    return ownerId;
  }
  
  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getColor() {
    return color;
  }
  
  public void setColor(String color) {
    this.color = color;
  }
  
  public int getAge() {
    return age;
  }
  
  public void setAge(int age) {
    this.age = age;
  }
}
