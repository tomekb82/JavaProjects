package com.command_pattern;

import java.util.*;

public class CommandThread extends Thread{
	
	Command cmd;
	int slot;
	
	public CommandThread(Command cmd){
		this.cmd = cmd;
	}
		
	public void run() {
	   cmd.execute();
	}

}
