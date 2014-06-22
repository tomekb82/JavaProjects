package eu.tbelina.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.model.UserRest;
import eu.tbelina.spring.service.IExpenseService;

@Controller
@RequestMapping("/rest_expenses/json")
public class ExpenseRestController {
	
	private IExpenseService expenseService;
	
	@Autowired
	public ExpenseRestController(IExpenseService expenseService){
		this.expenseService = expenseService;
	}
	
	/*
	 * GET - HTML
	 */
	/*@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String getRestExpense(@PathVariable("id") long id, Model model){
		
		model.addAttribute(expenseService.getExpenseById(id));
		return "rest_expense/view";
	}*/
	
	/*
	 * GET - JSON
	 */
	@RequestMapping(value="/{name}", method=RequestMethod.GET, 
			headers={"Accept=application/json"})
	public @ResponseBody 
		Expense getJsonRestExpense(@PathVariable String name){
		
		return expenseService.getExpenseByName(name);
	}
	/*
	 * GET - JSON
	 */
	@RequestMapping(method=RequestMethod.GET, 
			headers={"Accept=application/json"})
	public @ResponseBody 
		List<Expense> getJsonRestAllExpenses(){
		
		return expenseService.getExpenses();
	}
	
	/*
	 * GET - JSON with LIMIT + OFFSET
	 */
	@RequestMapping(value="/limit_offset", method=RequestMethod.GET, 
			headers={"Accept=application/json"})
	public @ResponseBody 
		List<Expense> getJsonRestAllExpensesLimitOffset(
				 @QueryParam("limit") Integer limit,
				 @QueryParam("offset") Integer offset,
				 @QueryParam("query") String query){
		
		return expenseService.getExpenses(query, limit, offset);
	}
	
	/*
	 * PUT
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public void putRestExpense(@PathVariable("id") long id, @Valid Expense expense){
		
		expenseService.updateExpense(expense);
	}
	
	/*
	 * PUT - JSON
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.PUT)
	@ResponseBody
	public void putJsonRestExpense(@PathVariable("name") String name, @RequestBody Expense expense){
		
	//@RequestMapping(value="/{name}", method=RequestMethod.PUT,
	//		headers={"Accept=application/json"})
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	//public void putJsonRestExpense(@PathVariable String name, @RequestBody Expense expense){
		
		//System.out.println("RestController: putJsonRestExpense, expense=" + expense.toString());
		expenseService.updateExpense(expense);
	}
	/* 
	 * DELETE
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	//@ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
	public void deleteRestExpense(@PathVariable("id") long id){

		System.out.println("RestController: deleteRestExpense, id=" + id);
		expenseService.deleteExpenseById(id);
	}
	
	/*
	 * POST
	 */
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Expense postJsonRestExpense(@RequestBody Expense expense) {
		
	
	//@RequestMapping(method=RequestMethod.POST)  // handle POST request
	//@ResponseStatus(org.springframework.http.HttpStatus.CREATED) // answer with 201 HTTP code 
	//public @ResponseBody Expense postJsonRestExpense(
	//		@Valid Expense expense,
	//		BindingResult bindingResult, 
	//		HttpServletResponse response) throws BindException{
		
	//		if(bindingResult.hasErrors()){
	//			throw new BindException(bindingResult);
	//		}
			
			System.out.println("RestController: postJsonRestExpense, expense=" + expense.toString());
			expenseService.addExpense(expense); // add new Expense
			
	//		response.setHeader("Location", "/expenses/" + expense.getId()); // set resource location
			
			return expense; // return Expense
	}

	
}
