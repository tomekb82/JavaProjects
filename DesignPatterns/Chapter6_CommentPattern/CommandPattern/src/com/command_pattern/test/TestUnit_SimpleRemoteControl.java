package com.command_pattern.test;

import com.command_pattern.Command;
import com.command_pattern.SimpleRemoteControl;
import com.command_pattern.light.*;
import com.command_pattern.garage.*;
import com.command_pattern.stereo.*;

public class TestUnit_SimpleRemoteControl {

	public static void main(String[] arg){
		
		//////////////////////////////////////////////////////////////////////////////////
		// Device: Light
		Light light = new Light();
		LightOnCommand lightOn = new LightOnCommand(light);
		LightOffCommand lightOff = new LightOffCommand(light);
		//lightOnCommand.execute();
		//lightOnCommand.undo();
		
		SimpleRemoteControl remote = new SimpleRemoteControl();
		remote.setCommand(lightOn);
		remote.buttonWasPressed();
		remote.buttonWasUnPressed();
		
		SimpleRemoteControl remote2 = new SimpleRemoteControl();
		remote2.setCommand(lightOff);
		remote2.buttonWasPressed();
		remote2.buttonWasUnPressed();
		
		//////////////////////////////////////////////////////////////////////////////////
		// Device: Garage's Door
		
		GarageDoor garage = new GarageDoor();
		GarageDoorOpenCommand garageOpen = new GarageDoorOpenCommand(garage);
		GarageDoorCloseCommand garageClose = new GarageDoorCloseCommand(garage);
		SimpleRemoteControl remote3 = new SimpleRemoteControl();
		SimpleRemoteControl remote4 = new SimpleRemoteControl();
		remote3.setCommand(garageOpen);
		remote3.buttonWasPressed();
		remote4.buttonWasUnPressed();
		remote3.buttonWasUnPressed();
		
		remote4.setCommand(garageClose);
		remote4.buttonWasPressed();
		remote4.buttonWasUnPressed();
		
		//////////////////////////////////////////////////////////////////////////////////
		// Device: Stereo
		
		Stereo stereo= new Stereo();
		StereoOnWithCDCommand stereoOn = new StereoOnWithCDCommand(stereo);
		SimpleRemoteControl remote5 = new SimpleRemoteControl();
		remote5.setCommand(stereoOn);
		remote5.buttonWasPressed();
		remote5.buttonWasUnPressed();
		
		
		
	}
}
