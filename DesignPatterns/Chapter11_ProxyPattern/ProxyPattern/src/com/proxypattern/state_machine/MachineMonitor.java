package com.proxypattern.state_machine;

import java.rmi.*;

public class MachineMonitor {
	
	StateMachineRemote stateMachine;
	
	public MachineMonitor(StateMachineRemote stateMachine){
		this.stateMachine = stateMachine;
	}
	
	public void report(){
		try{
			System.out.println("State Machine:" + stateMachine.getLocation());
			System.out.println("Current State:" + stateMachine.getState().getClass().getSimpleName());
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}

}
