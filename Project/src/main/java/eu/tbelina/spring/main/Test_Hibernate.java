package eu.tbelina.spring.main;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import eu.tbelina.spring.dao.impl.hibernate.HibernateExpenseDAO;
import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.IExpenseService;
import eu.tbelina.spring.service.impl.ExpenseService;

public class Test_Hibernate {

	public static void main(String[] args) {

		/* Test: Hibernate */
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-Module.xml");
		Expense expense = new Expense(0, "dziecko", 24.5f, 30, new Date());
		
		IExpenseService expenseService = (IExpenseService) ctx.getBean("expenseService");
		expenseService.addExpense(expense);
		
		System.out.println("hibernate expenseService: " + expenseService.getExpenses());
		
		
	}

}
