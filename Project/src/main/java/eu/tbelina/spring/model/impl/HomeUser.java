package eu.tbelina.spring.model.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.model.IUser;

@Component
public class HomeUser extends User{

	public HomeUser(){
		
	}
	public HomeUser(String firstName, String lastName) {
		super(firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	

	

}
