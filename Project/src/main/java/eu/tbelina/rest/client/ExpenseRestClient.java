package eu.tbelina.rest.client;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.model.UserRest;

@Component(value = "expenseRestClient")
public class ExpenseRestClient {

	private final static String expenseServiceUrl = "http://localhost:8080/spring/rest_expenses/json/";
	private final static String EXPENSE_NAME = "sss";
	
	// Inject RestTemplate 
	private RestTemplate restTemplate;

	public Expense[] getAllExpenses() {
		return this.restTemplate.getForObject(expenseServiceUrl,
				Expense[].class);
	}
	public Expense getExpenseByName(final String name) {
		return this.restTemplate.getForObject(expenseServiceUrl + "{name}",
				Expense.class, name);
	}
	
	public Expense createExpense(final Expense expense) {
		return this.restTemplate.postForObject(expenseServiceUrl, expense, Expense.class);
	}

	public void updateExpense(final Expense expense) {
		this.restTemplate.put(expenseServiceUrl + "{name}", 
				expense, expense.getName());
	}
	
	public void deleteExpense(final long id) {
		this.restTemplate.delete(expenseServiceUrl + "{id}", id);
	}
	
	/**********************************************************************/
	public Expense retrieveExpense_ver1(String username){
		return new RestTemplate().getForObject(
				"http://localhost:8080/spring/rest_expenses/json/{name}", 
				Expense.class, username);
	}
	
	public Expense retrieveExpense_ver2(String username){
		
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("name", username);
		return new RestTemplate().getForObject(
				"http://localhost:8080/spring/rest_expenses/json/{name}", 
				Expense.class, urlVariables);
	}
	
	/*
	 * INFO: getForEntity - could analize http status
	 */
	public Expense retrieveExpense_ver3(String username) throws Exception{
		
		ResponseEntity<Expense> response = new RestTemplate().getForEntity(
				"http://localhost:8080/spring/rest_expenses/json/{name}", 
				Expense.class, username);
		
		if(response.getStatusCode() == HttpStatus.NOT_MODIFIED){
			throw new Exception();//NotModifiedException();
		}
		return response.getBody();
	}
	/**********************************************************************/
	/*
	
   
	public UserRest getUserWithHeaders(final int id) {
	      
		final ResponseEntity<UserRest> responseEntity = this.restTemplate
				.getForEntity(userServiceUrl + "{id}", UserRest.class, id);

		System.out.println("Response Status : " + responseEntity.getStatusCode());

		final HttpHeaders headers = responseEntity.getHeaders();
		System.out.println("headers in response are : " + headers);
		
		return responseEntity.getBody();
	}
	*/
	
	/* Main function */
	public static void main(final String[] args) {
	   	   
		final ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"rest_expense.xml");
		final ExpenseRestClient restClient = (ExpenseRestClient) appContext
	            .getBean("expenseRestClient");

		// GET
		Expense expense = restClient.getExpenseByName(EXPENSE_NAME);
		System.out.println("EXPENSE by name " + expense.toString());
		
		// POST
		Expense newExpense = new Expense("owpoewpw",12,13,new Date());
		newExpense = restClient.createExpense(newExpense);
		System.out.println("Expense created with name= " + newExpense.getName());

		// GET
		Expense[] expenses;
		expenses = restClient.getAllExpenses();
		System.out.println("All expenses:");
		for(Expense e: expenses)
			System.out.println(" - " + e.toString());
				
		// PUT
		Expense changedExpense = newExpense;
		expense.setValue(666);
		restClient.updateExpense(changedExpense);
		expenses = restClient.getAllExpenses();
		System.out.println("after PUT , All expenses:");
		for(Expense e: expenses)
		    System.out.println(" - " + e.toString());
			
		// DELETE
		restClient.deleteExpense(newExpense.getId());
		expenses = restClient.getAllExpenses();
		System.out.println("All expenses:");
		for(Expense e: expenses)
		    System.out.println(" - " + e.toString());
    
   } 

    public RestTemplate getRestTemplate() {
		return restTemplate;
		}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
