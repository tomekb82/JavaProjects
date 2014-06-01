package com.template_pattern_2;

import java.util.Arrays;

public class MainApp {

	public static void main(String[] args){
		
		Duck[] ducks = {
				new Duck("Daffy", 8),
				new Duck("Dewey", 2),
				new Duck("Howard", 7),
				new Duck("Louie", 2),
		};
		
		System.out.println("Before sorting");
		display(ducks);
		
		Arrays.sort(ducks);
		
		System.out.println("After sorting");
		display(ducks);
		
	}
	
	public static void display(Duck[] ducks){
		for(int i=0; i < ducks.length; i++){
			System.out.println(ducks[i]);
		}
	}
}
