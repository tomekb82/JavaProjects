package com.starbuzz;

public class Soy extends CondimentDecorator {
	
	Beverage beverage;
	double cost = .15;
	
	public Soy(Beverage beverage){
		this.beverage = beverage;
	}
	
	public String getDescription(){
		return beverage.getDescription() + ", Soy";
	}
	public double cost(){
		return cost + beverage.cost(); 
	}
}
