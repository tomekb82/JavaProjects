package com.command_pattern.test;

import com.command_pattern.*;
import com.command_pattern.ceiling_fan.CeilingFan;
import com.command_pattern.ceiling_fan.CeilingFanHighCommand;
import com.command_pattern.ceiling_fan.CeilingFanMediumCommand;
import com.command_pattern.ceiling_fan.CelingFanOffCommand;
import com.command_pattern.garage.GarageDoor;
import com.command_pattern.garage.GarageDoorCloseCommand;
import com.command_pattern.garage.GarageDoorOpenCommand;
import com.command_pattern.light.Light;
import com.command_pattern.light.LightOffCommand;
import com.command_pattern.light.LightOnCommand;
import com.command_pattern.stereo.Stereo;
import com.command_pattern.stereo.StereoOffCommand;
import com.command_pattern.stereo.StereoOnWithCDCommand;

import java.util.*;

public class TestUnit_QueueThreads {

	public static void main(String[] arg){
		
		/*
		 * Queues
		 */
/*		//Queue<String> myQueue = new PriorityQueue<String>();
		Queue<Integer> myQueue = new LinkedList<Integer>();
		
		int nElements=10;
		for (int i = 0;  i < nElements; i++) {
			myQueue.offer(i); // == myQueue.add(i);
		}	
		while (!myQueue.isEmpty()) {
			System.out.print(myQueue.poll()); // == myQueue.remove()
		}
		System.out.print("\n");*/
		
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
		Stereo stereo = new Stereo("Living Room");
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
		Command[] listCommands 
			= {livingRoomLightOn, livingRoomLightOff, 
			   kitchenLightOn, kitchenLightOff,
			   garageDoorOpen, garageDoorClose,
			   stereoOnWithCD, stereOff,
			   ceilingFanHigh, ceilingFanOff,
			   ceilingFanMedium, ceilingFanOff};
		
		/*
		 * Commands Queues -adding
		 */
		Queue<Command> queueCommands = new LinkedList<Command>();
		for (int i = 0;  i < listCommands.length; i++) {
			queueCommands.offer(listCommands[i]); // == myQueue.add(i);
		}	
		
		/*
		 * Multi Thread Command executor:)
		 */
		CommandMultiThread multiThread = new CommandMultiThread(queueCommands);
				
	}
}
