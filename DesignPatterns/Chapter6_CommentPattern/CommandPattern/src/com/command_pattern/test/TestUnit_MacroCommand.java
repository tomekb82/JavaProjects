package com.command_pattern.test;

import com.command_pattern.*;
import com.command_pattern.light.*;
import com.command_pattern.tv.*;
import com.command_pattern.stereo.*;

public class TestUnit_MacroCommand {

	public static void main(String[] arg){
		
		ControlRemote remote = new ControlRemote();
		
		/*
		 * Devices
		 */
		Light light = new Light("Living Room");
		TV tv = new TV("Living Room");
		Stereo stereo = new Stereo("Living Room");
		
		/*
		 * Commands
		 */
		LightOnCommand lightOn = new LightOnCommand(light);
		LightOffCommand lightOff = new LightOffCommand(light);
		
		TVOnCommand tvOn = new TVOnCommand(tv);
		TVOffCommand tvOff = new TVOffCommand(tv);
		
		StereoOnWithCDCommand stereoOn = new StereoOnWithCDCommand(stereo);
		StereoOffCommand stereoOff = new StereoOffCommand(stereo);
		
		/*
		 * Macro Command
		 */
		
		Command[] partyOn = {lightOn, tvOn, stereoOn};
		Command[] partyOff = {lightOff, tvOff, stereoOff};
		
		MacroCommand partyOnMacro = new MacroCommand(partyOn);
		MacroCommand partyOffMacro = new MacroCommand(partyOff);
		
		/*
		 * Remote Control
		 */
		remote.setCommand(0, partyOnMacro, partyOffMacro);
		
		System.out.println(remote);
		System.out.println("=== Pushing MACRO On ====");
		remote.onButtonWasPushed(0);
		System.out.println("=== Pushing MACRO On ====");
		remote.offButtonWasPushed(0);
		
		
	}
}
