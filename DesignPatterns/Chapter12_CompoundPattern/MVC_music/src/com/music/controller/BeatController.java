package com.music.controller;

import com.music.model.BeatModelInterface;
import com.music.view.*;
/*
 * Controller: is in the middle of MVC, glues together VIEW and MODEL
 */
public class BeatController implements ControllerInterface{

	/* reference to MODEL */
	BeatModelInterface model;
	/* reference to VIEW */
	DJView view;
		
	/*
	 * Contructor:
	 *  - creates VIEW
	 *  - passed the MODEL
	 */
	public BeatController(BeatModelInterface model){
		this.model = model;
		view = new DJView(this,model);
		view.createView();
		view.createControls();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
		model.initialize();
	}
	
	public void start(){
		System.out.println("Controller: start()");
		model.on();
		view.disableStartMenuItem();
		view.enableStopMenuItem();
	}
	
	public void stop(){
		System.out.println("Controller: stop()");
		model.off();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}
	/*
	 * If button clicked:
	 *  - controller gets current BPM from the MODEL
	 *  - controller adds one
	 *  - controller sets a new BPM to the MODEL
	 */
	public void increaseBPM(){
		int bpm = model.getBPM();
		model.setBPM(bpm + 1);
	}
	/*
	 * If button clicked:
	 *  - controller gets current BPM from the MODEL
	 *  - controller substract one
	 *  - controller sets a new BPM to the MODEL
	 */
	public void decreaseBPM(){
		int bpm = model.getBPM();
		model.setBPM(bpm - 1);
	}
	/*
	 * If user interface is used to set BPM:
	 *  - controller instructs the MODEL to set its BPM
	 */
	public void setBPM(int bpm){
		model.setBPM(bpm);
	}
	
}
