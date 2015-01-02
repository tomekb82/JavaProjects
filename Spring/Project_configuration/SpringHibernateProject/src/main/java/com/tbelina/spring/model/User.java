package com.tbelina.spring.model;

import java.lang.Boolean; 
import java.lang.Integer; 
import java.lang.String; 
import java.util.HashSet; 
import java.util.Set; 

import javax.persistence.CascadeType; 
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.EnumType; 
import javax.persistence.Enumerated; 
import javax.persistence.FetchType; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.JoinColumn; 
import javax.persistence.JoinTable; 
import javax.persistence.ManyToMany; 
import javax.persistence.OneToMany; 
import javax.persistence.Table; 
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.tbelina.spring.model.enums.UserRole;

/**
* 
*/
@Entity
@Table(name = "users")
public class User {

	protected Integer id; 
	protected String firstName;  
	protected String lastName;
	protected String login;
	protected String password;
	protected String cleartextPassword;
	protected Boolean active;  
	protected UserRole role;  

	public User() {
	}

	/**
	* 
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	
	/**
	* 
	*/
	@Column(name = "firstName")
	public String getFirstName() {
		return firstName;
	}
	
	/**
	* 
	*/
	@Column(name = "lastName")
	public String getLastName() {
		return lastName;
	}
	
	/**
	* 
	*/
	@Column(name = "login")
	public String getLogin() {
		return this.login;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	* 
	*/
	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	/**
	* 
	*/
	@Column(name = "cleartext_password")
	public String getCleartextPassword() {
		return this.cleartextPassword;
	}

	/**
	* 
	*/
	@Column(name = "active")
	public Boolean getActive() {
		return this.active;
	}

	/**
	* 
	*/
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	public UserRole getRole() {
		return this.role;
	}

	/**
	* 
	*/
	public void setId(Integer id) {
		this.id = id;
	}

	

	/**
	* 
	*/
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	* 
	*/
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	* 
	*/
	public void setCleartextPassword(String password) {
		this.cleartextPassword = password;
	}

	/**
	* 
	*/
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	/**
	* 
	*/
	public Boolean isActive() {
		return this.active;
	}

	/**
	* 
	*/
	public void setRole(UserRole role) {
		this.role = role;
	}
}
