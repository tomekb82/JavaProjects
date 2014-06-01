package com.command_pattern.light;

public class Light {

	String place="";
	
	public Light(){};
	
	public Light(String place){
		this.place = place;
	}
	
	public void on(){
		System.out.println(place + " light is on.");
	}
	
	public void off(){
		System.out.println(place + " light is off.");
	}
}
