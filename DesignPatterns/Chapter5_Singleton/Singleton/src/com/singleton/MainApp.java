package com.singleton;

public class MainApp {

	public static void main(String[] args){
		
		Singleton.getInstance();
		Singleton.getInstance();
		Singleton.getInstance();
		
		Singleton2.getInstance();
		Singleton2.getInstance();
		
	}
}
