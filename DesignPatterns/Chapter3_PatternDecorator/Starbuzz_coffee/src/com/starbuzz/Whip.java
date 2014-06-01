package com.starbuzz;

public class Whip extends CondimentDecorator {
	
	Beverage beverage;
	double cost = .10;
	
	public Whip(Beverage beverage){
		this.beverage = beverage;
	}
	
	public String getDescription(){
		return beverage.getDescription() + ", Whip";
	}
	public double cost(){
		return cost + beverage.cost(); 
	}

}
