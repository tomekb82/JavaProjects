package eu.tbelina.spring.main;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import eu.tbelina.spring.dao.IExpenseDAO;
import eu.tbelina.spring.dao.impl.jpa.JpaExpenseDAO;
import eu.tbelina.spring.model.Expense;

public class Test_JPA {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		/* Tests: JdbcTemplete */
		IExpenseDAO jpaExpenseDAO = (IExpenseDAO)ctx.getBean("jpaExpenseDAO");
		Expense expense = new Expense(0, "dziecko", 24.5f, 30, new Date());
		jpaExpenseDAO.addExpense(expense);
		System.out.println("JdbcTemplate: " + jpaExpenseDAO.getExpenses());
		
		

	}

}
