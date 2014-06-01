package com.command_pattern.stereo;

public class Stereo {

	String place="";
	
	public Stereo (){
		
	}
	public Stereo(String place){
		this.place = place;
	}
	
	public void on(){
		System.out.println(place + " setting stereo on.");
	}
	
	public void off(){
		System.out.println(place + " settting stereo off.");
	}
	
	public void setCD(){
		System.out.println(place + " setting CD.");
	}
	
	public void setVolume(Integer volume){
		System.out.println("Volume set: " + volume + ".");
	}
}
