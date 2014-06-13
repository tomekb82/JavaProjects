package eu.tbelina.spring.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.tbelina.spring.dao.impl.jdbc.JdbcExpenseDAO;
import eu.tbelina.spring.dao.impl.jdbc.SimpleJdbcExpenseDAO;
import eu.tbelina.spring.dao.impl.jpa.JpaExpenseDAO;
import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.IExpenseService;
import eu.tbelina.spring.service.impl.ExpenseService;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ExpenseServiceTest {

	@Autowired
	IExpenseService expenseService;
	
	@Autowired
	JdbcExpenseDAO jdbcExpenseDAO;
	
	@Autowired
	SimpleJdbcExpenseDAO simpleJdbcExpenseDAO;
	
	@Autowired
	JpaExpenseDAO jpaExpenseDAO;
	
	@Before
	public void setUp() throws Exception {
		//jdbcExpenseDAO.dropExpenses();
		//simpleJdbcExpenseDAO.dropExpenses();
		//jdbcExpenseDAO.createExpenses();		
		//simpleJdbcExpenseDAO.createExpenses();
	}

	// TODO:
	//@Test
	public void testJPA(){
	
	//	Expense expense = new Expense(0, "dziecko", 24.5f, 30, new Date());
	//	jpaExpenseDAO.addExpense(expense);
	//	System.out.println("JdbcTemplate: " + jpaExpenseDAO.getExpenses());
	
	}
	
	@Test
	public void testAddExpense(){
		Expense expense = new Expense("filipek", 25.6f, 4, new Date());
		expenseService.addExpense(expense);
		//assertTrue("testAddExpense(): error", expenseService.getExpenseById(2).equals(expense));	
		assertTrue("testAddExpense(): error", expenseService.getExpenseByName("filipek").equals(expense));
	}
	
	@Test
	public void testUpdateExpense(){
		Expense expense = new Expense("Filip", 30.6f, 4, new Date());
		expenseService.addExpense(expense);
		Expense changedExpense  = expenseService.getExpenseByName("Filip");
		changedExpense.setName("dominik");
		expenseService.updateExpense(changedExpense);
		assertTrue("testUpdateExpense(): error", expenseService.getExpenseByName("dominik").equals(changedExpense));
	}
	
	@Test
	public void testDeleteExpense(){
		Expense expense = new Expense("kamila", 50.6f, 4, new Date());
		expenseService.addExpense(expense);
		assertTrue("testAddExpense(): error", expenseService.getExpenseByName("kamila").equals(expense));
		expenseService.deleteExpense(expense);
		boolean test = true;
		if (expenseService.getExpenses() != null){
			for(Expense e: expenseService.getExpenses()){
				if(e.equals(expense))
					test = false;
			}
		}
		
		assertTrue(test);
	}

}
