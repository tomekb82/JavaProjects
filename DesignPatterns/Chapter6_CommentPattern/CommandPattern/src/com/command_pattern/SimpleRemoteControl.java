package com.command_pattern;

public class SimpleRemoteControl {

	Command slot;
	
	public SimpleRemoteControl(){
		slot = new NoCommand();
	}
	
	public void setCommand(Command command){
		slot = command;
	}
	
	public void buttonWasPressed(){
		slot.execute();
	}
	
	public void buttonWasUnPressed(){
		slot.undo();
	}
  
}
