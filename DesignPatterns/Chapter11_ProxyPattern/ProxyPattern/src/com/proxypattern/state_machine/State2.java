package com.proxypattern.state_machine;

public class State2 implements State{

	transient StateMachine stateMachine;
	
	public State2(StateMachine stateMachine){
		this.stateMachine = stateMachine;
	}
	
	public void gotoState1(){
		System.out.println(this.getClass().getSimpleName() +":gotoState1");
	}
	public void gotoState2(){
		System.out.println(this.getClass().getSimpleName() +":gotoState2");
	}
	public void gotoState3(){
		stateMachine.setState(stateMachine.getState3());
		System.out.println(this.getClass().getSimpleName() +":gotoState3");
	}
	public void gotoState4(){
		System.out.println(this.getClass().getSimpleName() +":gotoState4");
	}
}
