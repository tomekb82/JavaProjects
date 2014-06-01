package com.starbuzz;

public class Mocha extends CondimentDecorator {
	
	Beverage beverage;
	double cost = .20;
	
	public Mocha(Beverage beverage){
		this.beverage = beverage;
	}
	
	public String getDescription(){
		return beverage.getDescription() + ", Mocha";
	}
	public double cost(){
		return cost + beverage.cost(); 
	}
}
