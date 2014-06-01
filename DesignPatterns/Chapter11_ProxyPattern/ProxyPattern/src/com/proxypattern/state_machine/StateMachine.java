package com.proxypattern.state_machine;

import java.rmi.*;
import java.rmi.server.*;

public class StateMachine extends UnicastRemoteObject implements StateMachineRemote {

	State state1;
	State state2;
	State state3;
	State state4;
	State state;
	
	String location;
	
	public StateMachine(String location) throws RemoteException{
		state1 = new State1(this);
		state2 = new State2(this);
		state3 = new State3(this);
		state4 = new State4(this);
		state = state1;
		
		this.location = location;
	}
	
	public String getLocation(){
		return location;
	}
	
	public void setState(State state){
		this.state = state;
	}
	public void gotoState1(){
		state.gotoState1(); 
	}
	public void gotoState2(){
		state.gotoState2();
	}
	public void gotoState3(){
		state.gotoState3();
	}
	public void gotoState4(){
		state.gotoState4();
	}

	public State getState(){
		 return state; 
	}
	public State getState1(){
		 return state1; 
	}
	public State getState2(){
		 return state2; 
	}
	public State getState3(){
		 return state3; 
	}
	public State getState4(){
		 return state4; 
	}
}
