package com.command_pattern.tv;

import com.command_pattern.Command;

public class TVOffCommand implements Command{

	TV tv;
	
	public TVOffCommand(TV tv){
		this.tv = tv;
	}
	
	public void execute(){
		tv.off();
	}
	
	public void undo(){
		tv.on();
	}
}
