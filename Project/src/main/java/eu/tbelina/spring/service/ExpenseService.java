package eu.tbelina.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import eu.tbelina.spring.model.Expense;

@Service
public class ExpenseService implements IExpenseService{

	@Override
	public void addExpense(Expense expense) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateExpense(Expense expense) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteExpense(Expense expense) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Expense getExpenseById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Expense> getExpenses() {
		// TODO Auto-generated method stub
		List<Expense> expenses = new ArrayList<Expense>();
		Expense expense = new Expense(0, "dziecko", 24.5f, 30, new Date());
		expenses.add(expense);
		expenses.add(expense);
		return expenses;
	}

	@Override
	public Expense getUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
