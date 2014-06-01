package com.proxypattern.state_machine;

import java.rmi.Naming;

public class TestMachineMonitor {

	public static void main(String args[]){
		
		String[] location = {"rmi://192.168.1.3/StateMachine",
							 "rmi://192.168.1.3/StateMachine",
							 "rmi://192.168.1.3/StateMachine"};
		
		MachineMonitor[] monitor = new MachineMonitor[location.length];
		
		for(int i=0; i<location.length; i++){
			
			try{
				
				StateMachineRemote machine = 
						(StateMachineRemote) Naming.lookup(location[i]);
				//machine.gotoState2();
				monitor[i] = new MachineMonitor(machine);
				System.out.println(monitor[i]);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		for(int i=0; i< monitor.length; i++){
			monitor[i].report();
		}		
	}
}
