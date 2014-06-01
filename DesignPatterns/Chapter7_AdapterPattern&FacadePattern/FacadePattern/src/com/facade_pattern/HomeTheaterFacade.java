package com.facade_pattern;

public class HomeTheaterFacade {

	Amplifier amp;
	Tuner tuner;
	DvdPlayer dvd;
	CdPlayer cd;
	Projector projector;
	TheaterLights lights;
	Screen screen;
	
	public HomeTheaterFacade(Amplifier amp,
				Tuner tuner,
				DvdPlayer dvd,
				CdPlayer cd,
				Projector projector,
				TheaterLights lights,
				Screen screen){
		
		this.amp = amp;
		this.tuner = tuner;
		this.dvd = dvd;
		this.cd = cd;
		this.projector = projector;
		this.lights = lights;
		this.screen = screen;
	}
	
	public void watchMovie(String movie){
		System.out.println("Get ready to warch a movie ...");
		
		lights.dim(9);
		screen.down();
		projector.on();
		projector.wideScreenMode();
		amp.on();
		amp.setDvd(dvd);
		amp.setSurroundSound();
		amp.setVolume(10);
		dvd.on();
		dvd.play(movie);
	}
	
	public void endMovie(){
		System.out.println("Shutting movie theater down ...");
		
		lights.on();
		screen.up();
		projector.off();
		amp.off();
		dvd.stop();
		dvd.eject();
		dvd.off();
	}
	
}
