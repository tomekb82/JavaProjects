package eu.tbelina.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.impl.ExpenseService;


public class Test_AOP {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-Module2.xml");
		
		ExpenseService expense = (ExpenseService)ctx.getBean("expenseService");
		expense.getExpenses();
		expense.addExpense(null);
	}

}
