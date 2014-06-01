package com.facade_pattern;

public class DvdPlayer {

	public void on(){
		System.out.println("Dvd setting on.");
	}
	
	public void off(){
		System.out.println("Dvd setting off.");
	}
	
	public void stop(){
		System.out.println("Dvd stop.");
	}
	
	public void eject(){
		System.out.println("Dvd setting eject.");
	}
	
	public void play(String movie){
		System.out.println("Dvd play: " + movie + ".");
	}
}
