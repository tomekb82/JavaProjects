package com.statepattern;

public class TestStateMachine {

	public static void main(String args[]){
		
		StateMachine stateMachine = new StateMachine();
		
		stateMachine.gotoState1();
		System.out.println("==============");
		stateMachine.gotoState3();
		System.out.println("==============");
		stateMachine.gotoState2();
		System.out.println("==============");
		stateMachine.gotoState3();
		System.out.println("==============");
		stateMachine.gotoState4();
		System.out.println("==============");
		stateMachine.gotoState2();
		System.out.println("==============");
		stateMachine.gotoState1();
		System.out.println("==============");
	}
}
