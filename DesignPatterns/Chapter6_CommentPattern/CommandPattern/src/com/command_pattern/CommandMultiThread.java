package com.command_pattern;

import java.util.Queue;

public class CommandMultiThread implements Runnable{

	Queue<Command> queueCommands;
	CommandThread[] t;
	
	public CommandMultiThread(Queue<Command> queueCommands) {
		
		int i=0;
		this.queueCommands = queueCommands;
		
		t = new CommandThread[20];
		
		while (!queueCommands.isEmpty()) {
			//System.out.println(queueCommands.poll().getClass().getSimpleName()); // == myQueue.remove()
			t[i] = new CommandThread(queueCommands.poll());
			t[i].start(); // Start the thread	
			if(i++ > 20){
				System.out.println("Number of threads exceeds..");
				break;
			}
		}	
	}
}
