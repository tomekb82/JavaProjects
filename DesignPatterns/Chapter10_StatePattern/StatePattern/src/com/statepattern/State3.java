package com.statepattern;

public class State3 implements State{

	StateMachine stateMachine;
	
	public State3(StateMachine stateMachine){
		this.stateMachine = stateMachine;
	}
	
	public void gotoState1(){
		System.out.println(this.getClass().getSimpleName() +":gotoState1");
	}
	public void gotoState2(){
		System.out.println(this.getClass().getSimpleName() +":gotoState2");
	}
	public void gotoState3(){
		System.out.println(this.getClass().getSimpleName() +":gotoState3");
	}
	public void gotoState4(){
		stateMachine.setState(stateMachine.getState4());
		System.out.println(this.getClass().getSimpleName() +":gotoState4");
	}
}
