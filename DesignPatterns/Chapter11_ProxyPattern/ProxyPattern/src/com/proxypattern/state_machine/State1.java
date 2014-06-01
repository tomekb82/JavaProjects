package com.proxypattern.state_machine;

public class State1 implements State{

	transient StateMachine stateMachine;
	
	public State1(StateMachine stateMachine){
		this.stateMachine = stateMachine;
	}
	
	public void gotoState1(){
		System.out.println(this.getClass().getSimpleName() +":gotoState1");
	}
	public void gotoState2(){
		stateMachine.setState(stateMachine.getState2());
		System.out.println(this.getClass().getSimpleName() +":gotoState2");
	};
	public void gotoState3(){
		System.out.println(this.getClass().getSimpleName() +":gotoState3");
	};
	public void gotoState4(){
		System.out.println(this.getClass().getSimpleName() +":gotoState4");
	};
}
