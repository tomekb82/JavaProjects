package com.duck_package;

public class MiniDuckSimulator {

	public static void main(String[] args){
		Duck mallard = new MallardDuck();
		mallard.performFly();
		mallard.performQuack();
		
		System.out.println("========================");
		
		Duck model = new ModelDuck();
		model.performFly();
		model.setFlyBehavoir(new FlyRocketPowered());
		model.performFly();
		model.performQuack();
		model.setQuackBehavoir(new MuteQuack());
		model.performQuack();
		
	}
}
