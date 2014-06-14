package eu.tbelina.spring.test.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.mchange.util.AssertException;

import eu.tbelina.spring.controller.ExpenseController;
import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.impl.ExpenseService;

public class ExpenseControllerTest {
	
	@Test
	public void shouldDisplayRecentExpenses(){
				
		List<Expense> expectedExpenses = Arrays.asList(new Expense(), new Expense(), new Expense());
		
		// Mock service implementation
		ExpenseService expenseService = mock(ExpenseService.class);
		
		when(expenseService.getExpenses()).thenReturn(expectedExpenses);
		
		// Create controller
		ExpenseController controller = new ExpenseController(expenseService);
		
		// Run operating method
		HashMap<String, Object> model = new HashMap<String, Object>();
		String viewName = controller.showAllExpenses(model);
		
		assertEquals("expenses", viewName);
		
		// Check result
		assertSame(expectedExpenses, model.get("expenses"));
		verify(expenseService).getExpenses();
		
	}

}
