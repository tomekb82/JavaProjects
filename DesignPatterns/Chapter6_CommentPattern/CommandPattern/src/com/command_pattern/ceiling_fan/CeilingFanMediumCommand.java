package com.command_pattern.ceiling_fan;

import com.command_pattern.Command;

public class CeilingFanMediumCommand implements Command{

	CeilingFan ceilingFan;
	int prevSpeed;
	
	public CeilingFanMediumCommand(CeilingFan ceilingFan){
		this.ceilingFan = ceilingFan;
	}
	
	public void execute(){
		prevSpeed = ceilingFan.getSpeed();
		ceilingFan.medium();
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
