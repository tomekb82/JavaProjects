package com.facade_pattern;

public class HomeTheaterTestDrive {

	
	public static void main(String[] args){
		
		Amplifier amp = new Amplifier();
		Tuner tuner = new Tuner();
		DvdPlayer dvd = new DvdPlayer();
		CdPlayer cd = new CdPlayer();
		Projector projector = new Projector();
		TheaterLights lights = new TheaterLights();
		Screen screen = new Screen();
		
		HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, tuner,dvd,
				cd,projector,lights,screen);
		homeTheater.watchMovie("Pan Tadeusz");
		homeTheater.endMovie();	
	}
}
