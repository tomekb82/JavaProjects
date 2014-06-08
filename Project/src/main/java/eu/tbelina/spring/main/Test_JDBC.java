package eu.tbelina.spring.main;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import eu.tbelina.spring.dao.impl.jdbc.JdbcExpenseDAO;
import eu.tbelina.spring.dao.impl.jdbc.SimpleJdbcExpenseDAO;
import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.impl.ExpenseService;

public class Test_JDBC {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		/* Tests: JdbcTemplete */
		JdbcExpenseDAO jdbcExpenseDAO = (JdbcExpenseDAO)ctx.getBean("jdbcExpenseDAO");
		Expense expense = new Expense(0, "dziecko", 24.5f, 30, new Date());
		jdbcExpenseDAO.dropExpenses();
		jdbcExpenseDAO.createExpenses();
		jdbcExpenseDAO.addExpense(expense);
		System.out.println("JdbcTemplate: " + jdbcExpenseDAO.getExpenses());
		
		/* Tests: SimpleJdbcTemplete */
		SimpleJdbcExpenseDAO simpleJdbcExpenseDAO = (SimpleJdbcExpenseDAO)ctx.getBean("simpleJdbcExpenseDAO");
		simpleJdbcExpenseDAO.dropExpenses();
		simpleJdbcExpenseDAO.createExpenses();
		simpleJdbcExpenseDAO.addExpense(expense);
		System.out.println("SimpleJdbcTemplate: " + simpleJdbcExpenseDAO.getExpenses());

	}

}
