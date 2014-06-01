package com.command_pattern.garage;

import com.command_pattern.Command;

public class GarageDoorOpenCommand implements Command {

	GarageDoor garage;
	
	public GarageDoorOpenCommand(GarageDoor garage){
		this.garage = garage;
	}
	
	public void execute(){
		garage.up();
	}
	
	public void undo(){
		garage.down();
	}
	
}
