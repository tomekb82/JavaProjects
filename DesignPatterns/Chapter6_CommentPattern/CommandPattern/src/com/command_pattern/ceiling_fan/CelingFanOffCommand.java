package com.command_pattern.ceiling_fan;

import com.command_pattern.Command;

public class CelingFanOffCommand implements Command {

	CeilingFan ceilingFan;
	int prevSpeed;
	
	public CelingFanOffCommand(CeilingFan ceilingFan){
		this.ceilingFan = ceilingFan;
	}
	
	public void execute(){
		prevSpeed = ceilingFan.getSpeed();
		ceilingFan.off();
	}
	
	public void undo(){
		if(prevSpeed == CeilingFan.HIGH){
			ceilingFan.high();
		}else if(prevSpeed == CeilingFan.MEDIUM){
			ceilingFan.medium();
		}else if(prevSpeed == CeilingFan.LOW){
			ceilingFan.low();
		}else if(prevSpeed == CeilingFan.OFF){
			ceilingFan.off();
		}
	}
}
