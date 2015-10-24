package pl.tb.myApp.model.user.entity;

import pl.tb.myApp.model.user.enumeration.Gender;
import pl.tb.myApp.model.util.entity.BasicEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MYAPP_USERS")
//@SequenceGenerator(name = "SEQUENCE_GENERATOR", sequenceName = "SEQ_MYAPP_USERS", allocationSize = 10)
public class User extends BasicEntity{

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // The user's email
  @Column(name = "EMAIL")
  @NotNull
  private String email;
  
  // The user's name
  @Column(name = "NAME")
  @NotNull
  private String name;

  @Column(name = "GENDER")
  @Enumerated(value = EnumType.STRING)
  private Gender gender;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  public User() { }

  public User(long id) {
    setId(id);
  }

  public User(String email, String name, Gender gender) {
    this.email = email;
    this.name = name;
    this.gender = gender;
  }

  // Getter and setter methods

  public String getEmail() {
    return email;
  }
  
  public void setEmail(String value) {
    this.email = value;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public void update(String name, String email, Gender gender) {
    this.name = name;
    this.email = email;
    this.gender = gender;
  }

  @Override
  public String toString() {
    return "User{" +
            "email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", gender=" + gender +
            '}';
  }
} // class User
