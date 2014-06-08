package eu.tbelina.spring.model.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.model.IUser;

public class User implements IUser{

	private String firstName;
	private String lastName;
	
	private List<Expense> expenses;
	
	public User(){
		
	}
	public User(String firstName, String lastName){	
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public Expense addExpense(Expense expense) {
		expenses.add(expense);
		return expense;
	}

	@Override
	public List<Expense> showExpense(Expense expense) {
		return expenses;
	}

	@Override
	public Expense updateExpense(Expense expense) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteExpense(Expense expense) {
		// TODO Auto-generated method stub
	}

	@Override
	public void calculateExpenses() {
		// TODO Auto-generated method stub
		
	}

}
