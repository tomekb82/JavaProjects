package com.music.adapter;

import com.music.view.DJView;
import com.music.controller.*;

public class HeartController implements ControllerInterface {

	HeartModelInterface model;
	DJView view;
	
	public HeartController(HeartModelInterface model){
		this.model = model;
		view = new DJView(this, new HeartAdapter(model));
		view.createView();
		view.createControls();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}
	
	public void start(){}
	
	public void stop(){}
	
	public void increaseBPM(){}
	
	public void decreaseBPM(){}
	
	public void setBPM(int bpm){}
}
