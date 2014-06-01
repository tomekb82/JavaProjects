package com.singleton;

public class Singleton {
	
	private static Singleton uniqueInstance = new Singleton();
	
	private Singleton(){
		System.out.println("Singleton...");
	}
	
	public static Singleton getInstance(){
		return uniqueInstance;
	}

}
