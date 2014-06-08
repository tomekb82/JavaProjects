package eu.tbelina.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.impl.ExpenseService;


public class Application {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aop-context.xml");
		
		ExpenseService expense = (ExpenseService)ctx.getBean("expenseService");
		expense.getExpenses();
		expense.addExpense(null);
	}

}
