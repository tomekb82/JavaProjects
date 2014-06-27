package com.packtpub.as7development.chapter10.exception;

 

public class NotEnoughMoneyException extends RuntimeException {
	public NotEnoughMoneyException(String string) {
		super(string);
	}
	public NotEnoughMoneyException() {
		super();
	}
}
