package com.packtpub.as7development.chapter8.exception;

import java.io.Serializable;

import javax.xml.ws.WebFault;

@WebFault(name = "SeatBookedExceptionFault",targetNamespace = "http://www.packtpub.com/")
public class SeatBookedException extends RuntimeException implements Serializable{
	public SeatBookedException() {
		super(); 
	}

	public SeatBookedException(String error) {
		super(error);
	}
}
