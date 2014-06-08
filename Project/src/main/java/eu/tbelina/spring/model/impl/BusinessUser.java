package eu.tbelina.spring.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.tbelina.spring.model.Address;
import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.model.IUser;

@Component
public class BusinessUser extends User{

	private Address address;
	
	public BusinessUser(){
		
	}
	public BusinessUser(String firstName, String lastName, Address address) {
		super(firstName, lastName);
		this.address = address;
	}

	

	
	
}
