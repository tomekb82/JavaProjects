package eu.tbelina.spring.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.IExpenseService;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

	private IExpenseService expenseService;
	
	@Autowired
	public ExpenseController(IExpenseService expenseService){
		this.expenseService = expenseService;
	}
	
	/*
	 * Example: expenses/
	 */
	@RequestMapping("/")
	public String showAllExpenses(Map<String, Object> model){
		model.put("expenses", expenseService.getExpenses());
		return "/jsp/expenses";
	}
	
	/*
	 * Example: expenses/expense?name=Filipek
	 */
	@RequestMapping(value="/expense", method=GET)
	public String showExpenseByName(
		@RequestParam("name") String name, Model model){
		Expense expense = expenseService.getExpenseByName(name);
		model.addAttribute(expense);
		return "/jsp/expenseByName";
	}
	
	/*
	 * Example: expenses?new
	 */
	@RequestMapping(method=RequestMethod.GET, params="new")
	public String createExpense(Model model){
		
		//Expense expense = new Expense();
		//expense.setDate(new Date());
		model.addAttribute(new Expense());
		return "/jsp/expenses/edit";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String addExpenseFromForm(@Valid Expense expense, BindingResult bindingResult){
		
		/* Check errors */
		if(bindingResult.hasErrors()){
			System.out.println("EEEEEEEERRRRRRROR binding");
			return"/jsp/expenses/edit";
		}
		
		/* Save data */
		
		System.out.println("TTTTTTT=" + expense.toString());
		expenseService.addExpense(expense);
		
		/* Redirect after POST */
		return "redirect:/jsp//expenses/" + expense.getName();
	}
	
	@RequestMapping(value="/{name}", method=RequestMethod.GET)
	public String showExpense(@PathVariable String name, Model model){
		
		model.addAttribute(expenseService.getExpenseByName(name));
		return "/jsp/expense/view";
	}
	
	
	
	
}
