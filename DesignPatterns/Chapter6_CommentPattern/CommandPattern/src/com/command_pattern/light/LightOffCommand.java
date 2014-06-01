package com.command_pattern.light;

import com.command_pattern.Command;

public class LightOffCommand implements Command {

	Light light;
	
	public LightOffCommand(Light light){
		this.light = light;
	}
	
	public void execute(){
		light.off();
	}
	
	public void undo(){
		light.on();
	}
}
