package eu.tbelina.spring.service;

import java.util.List;

import eu.tbelina.spring.model.Expense;

public interface IExpenseService {

	public void addExpense(Expense expense);		
	public void updateExpense(Expense expense);
	public void deleteExpense(Expense expense);
	public void deleteExpenseById(long id);
	public Expense getExpenseById(long id);
	public List<Expense> getExpenses();	
	public Expense getExpenseByName(String name);
}
