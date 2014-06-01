package com.facade_pattern;

public class Amplifier {

	DvdPlayer dvd;
	Integer volume;
	
	public void on(){
		System.out.println("Amplifier setting on.");
	}
	
	public void off(){
		System.out.println("Amplifier setting off.");
	}
	
	public void setDvd(DvdPlayer dvd){
		System.out.println("Amplifier setting dvd.");
		this.dvd = dvd;
	}
	
	public void setSurroundSound(){
		System.out.println("Amplifier setting Surround Sound.");
	}
	
	public void setVolume(Integer volume){
		System.out.println("Amplifier setting volume.");
		this.volume = volume;
	}
	
}
