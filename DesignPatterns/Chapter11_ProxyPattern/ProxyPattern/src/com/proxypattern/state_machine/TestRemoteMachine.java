package com.proxypattern.state_machine;

import java.rmi.*;
import java.rmi.server.*;

public class TestRemoteMachine {

	public static void main(String args[]){

		StateMachineRemote stateMachine = null;
		try{	
			stateMachine = new StateMachine(args[0]);
			stateMachine.gotoState2();
			Naming.rebind("//" + args[0] + "/StateMachine", stateMachine);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
