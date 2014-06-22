package eu.tbelina.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.tbelina.spring.dao.IExpenseDAO;
import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.IExpenseService;

@Transactional(readOnly = true)
//@Service("expenseService")
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
		return getExpenseDAO().getExpenses();
	}
	
	@Override
	public List<Expense> getExpenses(String name, int limit, int offset) {
		return getExpenseDAO().getExpenses(name, limit, offset);
	}

	@Override
	public Expense getExpenseByName(String name) {
		return getExpenseDAO().getExpenseByName(name);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteExpenseById(long id) {
		getExpenseDAO().deleteExpenseById(id);
		
	}

	
}
