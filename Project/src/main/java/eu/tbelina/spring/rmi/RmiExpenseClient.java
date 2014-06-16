package eu.tbelina.spring.rmi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.IExpenseService;
import eu.tbelina.spring.service.impl.ExpenseService;

public class RmiExpenseClient {  

	public static void main(String[] args)  {  

		ApplicationContext context = new ClassPathXmlApplicationContext("rmi_expenseClient.xml");  

		IExpenseService expenseService = (IExpenseService)context.getBean("expenseService");  

		System.out.println("RMI expenses: "); 
		for(Expense e: expenseService.getExpenses()){
			System.out.println("- " + e.toString());  
		}
		
		System.out.println("RMI expense by name: " + expenseService.getExpenseByName("Filipek")); 

	}  
}  
