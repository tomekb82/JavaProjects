package eu.tbelina.spring.test.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import eu.tbelina.spring.model.Expense;

public class RestControllerTest {

	private final static String EXPENSE_NAME = "sss";
	
	@Before
	public void setUp() throws Exception {
	}

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
	
	public Expense[] retrieveAllExpenses(){
		return new RestTemplate().getForObject(
				"http://localhost:8080/spring/rest_expenses/json/",
				Expense[].class);
	}
	
	/* 
	 * GET: (using getForObject, getForEntity)
	 */
	//@Test
	public void testGetExpense() {
		
		Expense expense;
		//expense = retrieveExpense_ver1(EXPENSE_NAME);
		//expense = retrieveExpense_ver2(EXPENSE_NAME);
		try {
			expense = retrieveExpense_ver3(EXPENSE_NAME);
			System.out.println("Expense i JSON: " + expense);
		} catch (Exception e) {
			System.out.println("Exception: value on server not modified.");
			e.printStackTrace();
		}		
	}
	//@Test
	public void testGetAllExpenses() {
		
		Expense[] expenses;
		expenses = retrieveAllExpenses();
		System.out.println("All expenses:");
		for(Expense e: expenses)
		    System.out.println(" - " + e.toString());
			
	}
	
	/*
	 * PUT
	 */
	
	public void updateExpense_ver1(Expense expense){
		try{
			String url = "http://localhost:8080/spring/rest_expenses/json/" + expense.getName();
			new RestTemplate().put(new URI(url), expense);
		}catch(URISyntaxException e){
			System.out.println("Couldn't actualize Expense entity.");
		}
	}
	public void updateExpense_ver2(Expense expense){
		new RestTemplate().put("http://localhost:8080/spring/rest_expenses/json/{name}",
				expense, expense.getName());
	}
	public void updateExpense_ver3(Expense expense){
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", expense.getName());
		new RestTemplate().put("http://localhost:8080/spring/rest_expenses/json/{name}",
				expense, params);
	}
	
	//TODO:
	//@Test
	public void testPutExpense() {
		
		Expense expense = new Expense();
		expense.setName("sss");
		expense.setDate(new Date());
		expense.setQuantity(12);
		expense.setValue(23);
		updateExpense_ver2(expense);
	}
	
	
	public void deleteExpense_ver1(long id){
		try{
			
			new RestTemplate().delete(new URI("http://localhost:8080/spring/rest_expenses/json/" + id));
		}catch(URISyntaxException e){
			
		}
	}
	public void deleteExpense_ver2(long id){
		new RestTemplate().delete("http://localhost:8080/spring/rest_expenses/json/{id}", id);
	}
	
	//TODO:
	//@Test
	public void testDeleteExpense(){
		
		deleteExpense_ver1(163840);
		//deleteExpense_ver2(163840);
	}
	
	public Expense postExpense_ver1(Expense expense){
		
		RestTemplate rest = new RestTemplate();
		return rest.postForObject("http://localhost:8080/spring/rest_expenses/json/", 
				expense, Expense.class);	
	}
	
	/* Get header */
	public String postExpense_ver2(Expense expense){
		
		RestTemplate rest = new RestTemplate();
		return rest.postForLocation("http://localhost:8080/spring/rest_expenses/json/", 
				expense).toString();	
	}
	
	//TODO: 
	//@Test
	public void testPostExpense(){
		
		Expense expense = new Expense("owpoewpw",12,13,new Date());
		//expense.setName("owpoewpw");
		//expense.setDate(new Date());
		//expense.setQuantity(12);
		//expense.setValue(23);
		postExpense_ver1(expense);
		
	}
}
