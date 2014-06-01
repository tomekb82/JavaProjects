package com.duck_package;

public abstract class Duck {
	
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;

	public Duck(){
		
	}
	
	public void setFlyBehavoir(FlyBehavior fb){
		flyBehavior = fb;
	}
	
	public void setQuackBehavoir(QuackBehavior qb){
		quackBehavior = qb;
	}
	
	public abstract display();
	
	public void performFly(){
		flyBehavior.fly();
	}
	
	public void performQuack(){
		quackBehavior.quack();
	}
	
	public void swim(){
		System.out.println("All ducks float..");
	}
}
