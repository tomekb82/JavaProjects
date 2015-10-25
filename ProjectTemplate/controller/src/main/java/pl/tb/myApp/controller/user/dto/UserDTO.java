package pl.tb.myApp.controller.user.dto;

import pl.tb.myApp.model.user.enumeration.Gender;

import java.io.Serializable;
public class UserDTO implements Serializable{

  private Long id ;

  private String email;

  private String name;

  private Gender gender;

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

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
