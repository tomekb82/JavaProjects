package com.command_pattern.garage;

import com.command_pattern.Command;

public class GarageDoorCloseCommand implements Command {

	GarageDoor garage;
	
	public GarageDoorCloseCommand(GarageDoor garage){
		this.garage = garage;
	}
	
	public void execute(){
		garage.down();
	}
	
	public void undo(){
		garage.up();
	}
}
