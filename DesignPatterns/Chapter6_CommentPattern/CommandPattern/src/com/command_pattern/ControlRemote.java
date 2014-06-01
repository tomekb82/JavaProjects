package com.command_pattern;

public class ControlRemote {
	
	Command[] onCommands;
	Command[] offCommands;
	Command undoCommand;
	
	public ControlRemote(){
		onCommands = new Command[7];
		offCommands = new Command[7];
		
		Command noCommand = new NoCommand();
		for(int i=0; i<7; i++){
			onCommands[i] = noCommand;
			offCommands[i] = noCommand;
		}
		undoCommand = noCommand;
	}
	
	public void setCommand(int slot, Command onCommand, Command offCommand){
		
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
	}
	
	public void onButtonWasPushed(int slot){
		if(onCommands[slot] != null){
			onCommands[slot].execute();
			undoCommand = onCommands[slot];
		}
	}
	
	public void offButtonWasPushed(int slot){
		if(offCommands[slot] != null){
			offCommands[slot].execute();
			undoCommand = offCommands[slot];
		}
	}
	
	public void undoButtonWasPushed(){
		if(undoCommand != null){
			undoCommand.undo();
		}
	}
	
	public String toString(){
		StringBuffer stringBuff = new StringBuffer();
		stringBuff.append("\n========= REMOTE CONTROL ============\n");
		for(int i=0; i < onCommands.length; i++){
			stringBuff.append("[slot " + i + "]  " + onCommands[i].getClass().getSimpleName() + "  "+ offCommands[i].getClass().getSimpleName() + "\n");
		}
		return stringBuff.toString();
	}

}
