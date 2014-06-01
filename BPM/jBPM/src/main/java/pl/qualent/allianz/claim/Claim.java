package pl.qualent.allianz.claim;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class Claim implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1674339832742851582L;
	
	 @NotEmpty(message="Należy wybrać rodzaj szkody")
	 String cause;
	 @NotNull(message="Należy wybrać zgłaszającego")
	 Integer applicant;	
	 @NotNull(message="Należy wybrać poszkodowanego")
	 Integer victim;
	 @NotEmpty
	 String firstName;
	 @NotEmpty
	 String lastName;
	 int citizenship;
	 String pesel;
	 int sex;	
	 int country;
	 String postalCode;
	 @NotEmpty
	 String city;
	 @NotEmpty
	 String street;
	 int hause;
	 int flat;
	 String postOffice;

	public Claim(){
		cause = "";
		firstName = "";
		lastName = "";
		citizenship = 0;	
		pesel = "";
		sex = 0;
		country = 0;
		postalCode = "";
		city = "";
		street = "";
		hause = 0;
		postOffice = "";
	}
 //Cause setters/getters
	public void setCause(String cause){
		this.cause = cause;
	}
	
	public String getCause(){
		return this.cause;
	}
	 //Applicant setters/getters
	public void setApplicant(Integer applicant){
		this.applicant = applicant;
	}
	
	public Integer getApplicant(){
		return this.applicant;
	}

	//Victim setters/getters
	public void setVictim(Integer victim){
		this.victim = victim;
	}
	
	public Integer getVictim(){
		return this.victim;
	}

	//first/last name setters/getters
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	//Citizenship setters/getters
	public void setCitizenship(int citizenship){
		this.citizenship = citizenship;
	}
	
	public int getCitizenship(){
		return this.citizenship;
	}

	//PESEL setters/getters
	public void setPesel(String pesel){
		this.pesel = pesel;
	}
	
	public String getPesel(){
		return this.pesel;
	}
	//Sex setters/getters
	public void setSex(int sex){
		this.sex = sex;
	}
	
	public int getSex(){
		return this.sex;
	}

	//Country setters/getters
	public void setCountry(int country){
		this.country = country;
	}
	
	public int getCountry(){
		return this.country;
	}

	//Postal setters/getters
	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}
	
	public String getPostalCode(){
		return this.postalCode;
	}
	//Adress setters/getters
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return this.city;
	}
	public void setStreet(String street){
		this.street = street;
	}
	
	public String getStreet(){
		return this.street;
	}
	public void setHause(int hause){
		this.hause = hause;
	}
	
	public int getHause(){
		return this.hause;
	}
	public void setFlat(int flat){
		this.flat = flat;
	}
	
	public int getFlat(){
		return this.flat;
	}
	public void setPostOffice(String postOffice){
		this.postOffice = postOffice;
	}
	
	public String getPostOffice(){
		return this.postOffice;
	}
	
	public void setData(Claim claim){
		this.cause = claim.cause;
		this.applicant = claim.applicant;	
		this.victim = claim.victim;
		this.firstName = claim.firstName;
		this.lastName = claim.lastName;
		this.citizenship = claim.citizenship;	
		this.pesel = claim.pesel;
		this.sex = claim.sex;
		this.country = claim.country;
		this.postalCode = claim.postalCode;
		this.city = claim.city;
		this.street = claim.street;
		this.hause = claim.hause;
		this.flat = claim.flat;
		this.postOffice = claim.postOffice;
	}
}