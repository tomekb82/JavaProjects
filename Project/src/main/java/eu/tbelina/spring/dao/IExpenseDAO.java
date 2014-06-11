package eu.tbelina.spring.dao;

import java.util.List;

import eu.tbelina.spring.model.Expense;

public interface IExpenseDAO{
	
	public void addExpense(Expense expense);
	public void updateExpense(Expense expense);
	public void deleteExpense(Expense expense);
	public List<Expense> getExpenses();
	public Expense getExpenseById(long id);
	
}