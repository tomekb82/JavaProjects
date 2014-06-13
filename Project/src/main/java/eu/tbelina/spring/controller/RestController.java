package eu.tbelina.spring.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.IExpenseService;

@Controller
@RequestMapping("/rest_expenses")
public class RestController {
	
	private IExpenseService expenseService;
	
	@Autowired
	public RestController(IExpenseService expenseService){
		this.expenseService = expenseService;
	}
	
	/*
	 * GET - HTML
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String getRestExpense(@PathVariable("id") long id, Model model){
		
		model.addAttribute(expenseService.getExpenseById(id));
		return "rest_expense/view";
	}
	
	/*
	 * GET - JSON
	 */
	@RequestMapping(value="/json/{name}", method=RequestMethod.GET, 
			headers={"Accept=application/json"})
	public @ResponseBody 
		Expense getJsonRestExpense(@PathVariable String name){
		
		return expenseService.getExpenseByName(name);
	}
	
	/*
	 * PUT
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public void putRestExpense(@PathVariable("id") long id, @Valid Expense expense){
		
		expenseService.updateExpense(expense);
	}
	
	/* 
	 * DELETE
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	public void deleteRestExpense(@PathVariable("id") long id){
		
		expenseService.deleteExpenseById(id);
	}
	
	/*
	 * POST
	 */
	@RequestMapping(method=RequestMethod.POST)  // handle POST request
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED) // answer with 201 HTTP code 
	public @ResponseBody Expense createRestExpense(
			@Valid Expense expense,
			BindingResult bindingResult, 
			HttpServletResponse response) throws BindException{
		
			if(bindingResult.hasErrors()){
				throw new BindException(bindingResult);
			}
			
			expenseService.addExpense(expense); // add new Expense
			
			response.setHeader("Location", "/expenses/" + expense.getId()); // set resource location
			
			return expense; // return Expense
	}

	
}
