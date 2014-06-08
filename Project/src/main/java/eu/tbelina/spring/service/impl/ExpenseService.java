package eu.tbelina.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.tbelina.spring.dao.IExpenseDAO;
import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.IExpenseService;

@Transactional(readOnly = true)
@Service("expenseService")
public class ExpenseService implements IExpenseService{

	IExpenseDAO expenseDAO;
	
	public IExpenseDAO getExpenseDAO() {
		return expenseDAO;
	}

	public void setExpenseDAO(IExpenseDAO expenseDAO) {
		this.expenseDAO = expenseDAO;
	}
    
	@Transactional(readOnly = false)
	@Override
	public void addExpense(Expense expense) {
		System.out.println("service: addExpense");
		getExpenseDAO().addExpense(expense);	
	}

	@Transactional(readOnly = false)
	@Override
	public void updateExpense(Expense expense) {
		getExpenseDAO().updateExpense(expense);
		
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteExpense(Expense expense) {
		getExpenseDAO().deleteExpense(expense);
		
	}

	@Override
	public Expense getExpenseById(long id) {
		return getExpenseDAO().getExpenseById(id);
	}

	@Override
	public List<Expense> getExpenses() {
		/*System.out.println("DEBUG XXX: getExpenses()");
		List<Expense> expenses = new ArrayList<Expense>();
		Expense expense = new Expense(0, "dziecko", 24.5f, 30, new Date());
		expenses.add(expense);
		expenses.add(expense);
		System.out.println("DEBUG XXX: " + expense);
		return expenses;*/
		return getExpenseDAO().getExpenses();
	}

	@Override
	public Expense getUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
