package pl.tb.myApp.controller.user.dto;

import pl.tb.myApp.model.enumeration.Gender;

import java.io.Serializable;
public class UserDTO implements Serializable{

  private String email;

  private String name;

  private Gender gender;

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
