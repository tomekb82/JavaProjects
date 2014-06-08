package eu.tbelina.spring.model;

import java.util.List;

public interface IUser {

	public Expense addExpense(Expense expense);
	public List<Expense> showExpense(Expense expense);
	public Expense updateExpense(Expense expense);
	public void deleteExpense(Expense expense);
	public void calculateExpenses();
		
}
