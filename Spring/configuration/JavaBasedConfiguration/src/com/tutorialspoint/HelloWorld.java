package com.tutorialspoint;

public class HelloWorld {

	private String message;
	public void setMessage(String message){
	this.message = message;
	}
	public void getMessage(){
	System.out.println("Your Message : " + message);
	}
	
	public void init() {
		// initialization logic
		System.out.println("Bean init method");
		}
		public void cleanup() {
		// destruction logic
			System.out.println("Bean destroy method");
		}
}
