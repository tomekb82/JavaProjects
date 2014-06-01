package com.command_pattern.stereo;

import com.command_pattern.Command;

public class StereoOnWithCDCommand implements Command {

	Stereo stereo;
	
	public StereoOnWithCDCommand(Stereo stereo){
		this.stereo = stereo;
	}
	
	public void execute(){
		stereo.on();
		stereo.setCD();
		stereo.setVolume(11);
	}
	
	public void undo(){
		stereo.off();
	}
	
}
