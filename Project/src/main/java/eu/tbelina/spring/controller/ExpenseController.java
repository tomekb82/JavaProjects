package eu.tbelina.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.IExpenseService;

@Controller
public class ExpenseController {

	private IExpenseService expenseService;
	
	@Autowired
	public ExpenseController(IExpenseService expenseService){
		this.expenseService = expenseService;
	}
	
	@RequestMapping("/expenses")
	public String showAllExpenses(Map<String, Object> model){
		//for(Expense e: expenseService.getExpenses()){
		//    System.out.println("debug ctrl=" + e.toString());
		//}
		model.put("expenses", expenseService.getExpenses());
		return "expenses";
	}
	
	
}
