package com.command_pattern.tv;

public class TV {

	String place;
	
	public TV(String place){
		this.place = place;
	}
	
	public void on(){
			System.out.println(place + " setting TV on");
	}
	
	public void off(){
		System.out.println(place + " setting TV off");
	}
}
