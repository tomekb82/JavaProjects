package com.facade_pattern;

public class TheaterLights {

	Integer dim;
	
	public void dim(Integer dim){
		System.out.println("TheaterLights setting dim= " +dim+".");
		this.dim = dim;
	}
	
	public void on(){
		System.out.println("TheaterLights setting on.");
	}
}
