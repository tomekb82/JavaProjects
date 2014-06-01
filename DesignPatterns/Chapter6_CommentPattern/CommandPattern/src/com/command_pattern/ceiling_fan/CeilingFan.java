package com.command_pattern.ceiling_fan;

public class CeilingFan {

	public static final int HIGH = 3;
	public static final int MEDIUM= 2;
	public static final int LOW = 1;
	public static final int OFF = 0;

	String location;
	int speed;
	
	public CeilingFan(String location){
		this.location = location;
		speed = OFF;
	}
	
	public void high(){
		System.out.println("Ceiling Fan: HIGH");
		speed = HIGH;
	}
	
	public void medium(){
		System.out.println("Ceiling Fan: MEDIUM");
		speed = MEDIUM;
	}
	
	public void low(){
		System.out.println("Ceiling Fan: LOW");
		speed = LOW;
	}
	
	public void off(){
		System.out.println("Ceiling Fan: OFF");
		speed = OFF;
	}
	
	public int getSpeed(){
		return speed;
	}
}
