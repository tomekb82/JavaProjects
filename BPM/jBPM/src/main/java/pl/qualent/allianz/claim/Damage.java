package pl.qualent.allianz.claim;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.qualent.allianz.jBPMService;

@Component
public class Damage {
			
	private String vehicle;
	private String vehicleType;
	private String prodYear;
	private String brand;
	private String model;
	private String version;
	private float engineCapacity;
	private long registrationNo;
	private String vin;
	private float course;
	private String description;
	private String country;
	private String province;
	private String district;
	private String city;

	public Damage(){
		vehicle = "";
		vehicleType = "";
		prodYear = "";
		brand = "";
		model = "";
		version = "";
		engineCapacity = 0;
		registrationNo = 0;
		vin = "";
		course = 0;
		description = "";
		country = "";
		province = "";
		district = "";
		city = "";
	}

//Vehicle setters/getters
public void setVehicle(String vehicle){
	this.vehicle = vehicle;
	}
	
	public String getVehicle(){
		return this.vehicle;
	}

	//Vehicle type setters/getters
	public void setVehicleType(String vehicleType){
		this.vehicleType = vehicleType;
	}
	
	public String getVehicleType(){
		return this.vehicleType;
	}

	public String getProdYear(){
		return this.prodYear;
	}
	public void setProdYear(String prodYear){
		this.prodYear = prodYear;
	}
	
	public String getBrand(){
		return this.brand;
	}
	public void setBrand(String brand){
		this.brand = brand;
	}
	
	public String getModel(){
		return this.model;
	}
	public void setModel(String model){
		this.model = model;
	}
	
	public String getVersion(){
		return this.version;
	}
	public void setVersion(String version){
		this.version = version;
	}
	
	public float getEngineCapacity(){
		return this.engineCapacity;
	}
	public void setEngineCapacity(float  engineCapacity){
		this.engineCapacity = engineCapacity;
	}
	
	public long getRegistrationNo(){
		return this.registrationNo;
	}
	public void setRegistrationNo(long registrationNo){
		this.registrationNo = registrationNo;
	}
	
	public String getVin(){
		return this.vin;
	}
	public void setVin(String vin){
		this.vin = vin;
	}
	
	public float getCourse(){
		return this.course;
	}
	public void setCourse(float course){
		this.course = course;
	}
	
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getCountry(){
		return this.country;
	}
	public void setCountry(String country){
		this.country = country;
	}
	
	public String getProvince(){
		return this.province;
	}
	public void setProvince(String province){
		this.province = province;
	}
	
	public String getDistrict(){
		return this.district;
	}
	public void setDistrict(String district){
		this.district = district;
	}
	
	public String getCity(){
		return this.city;
	}
	public void setCity(String city){
		this.city = city;
	}
}