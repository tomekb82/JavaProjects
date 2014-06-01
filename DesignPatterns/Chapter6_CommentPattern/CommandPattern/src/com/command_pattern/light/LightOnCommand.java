package com.command_pattern.light;

import com.command_pattern.Command;

public class LightOnCommand implements Command{
	
	Light light;
	
	public LightOnCommand(Light light){
		this.light = light;
	}
	
	public void execute(){
		light.on();
	}
	
	public void undo(){
		light.off();
	}

}
