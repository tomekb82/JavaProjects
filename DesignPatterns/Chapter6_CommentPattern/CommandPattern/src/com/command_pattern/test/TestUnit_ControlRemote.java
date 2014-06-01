package com.command_pattern.test;

import com.command_pattern.ControlRemote;
import com.command_pattern.light.*;
import com.command_pattern.garage.*;
import com.command_pattern.stereo.*;
import com.command_pattern.ceiling_fan.*;

public class TestUnit_ControlRemote {

	public static void main(String[] args){
		
		ControlRemote remote = new ControlRemote();
		
		/* -------------------------------------------------
		 * Lights:
		 */
		Light livingRoomLight = new Light("Living Room");
		Light kitchenLight = new Light("Kitchen");
		LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
		LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
		LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
		LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);
		
		/* -------------------------------------------------
		 * Garage Door:
		 */
		GarageDoor garageDoor = new GarageDoor();
		GarageDoorOpenCommand garageDoorOpen = new GarageDoorOpenCommand(garageDoor);
		GarageDoorCloseCommand garageDoorClose = new GarageDoorCloseCommand(garageDoor);
		
		
		/* -------------------------------------------------
		 * Stereo:
		 */
		Stereo stereo = new Stereo();
		StereoOnWithCDCommand stereoOnWithCD = new StereoOnWithCDCommand(stereo);
		StereoOffCommand stereOff = new StereoOffCommand(stereo);
		
		/* -------------------------------------------------
		 * Ceiling Fan:
		 */
		CeilingFan ceilingFan = new CeilingFan("Living Room");
		CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);
		CeilingFanMediumCommand ceilingFanMedium = new CeilingFanMediumCommand(ceilingFan);
		CelingFanOffCommand ceilingFanOff = new CelingFanOffCommand(ceilingFan);
		
		
		/* -------------------------------------------------
		 * Setting commands
		 */
		remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
		remote.setCommand(1, kitchenLightOn, kitchenLightOff);
		remote.setCommand(2, garageDoorOpen, garageDoorClose);
		remote.setCommand(3, stereoOnWithCD, stereOff);
		remote.setCommand(4, ceilingFanHigh, ceilingFanOff);
		remote.setCommand(5, ceilingFanMedium, ceilingFanOff);
		
		/* -------------------------------------------------
		 * Executing commands
		 */
		remote.onButtonWasPushed(0);
		remote.offButtonWasPushed(0);
		System.out.println(remote);
		remote.undoButtonWasPushed();
		remote.offButtonWasPushed(0);
		remote.onButtonWasPushed(0);
		System.out.println(remote);
		remote.undoButtonWasPushed();
		
		System.out.println("------------------------------");
		remote.onButtonWasPushed(1);
		remote.offButtonWasPushed(1);
		remote.onButtonWasPushed(2);
		remote.offButtonWasPushed(2);
		remote.onButtonWasPushed(3);
		remote.offButtonWasPushed(3);
		System.out.println(remote);
		
		System.out.println("------------------------------");
		remote.onButtonWasPushed(4);
		remote.onButtonWasPushed(5);
		remote.offButtonWasPushed(4);
		remote.undoButtonWasPushed();
		
		
	}
}
