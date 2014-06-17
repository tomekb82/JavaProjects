package eu.tbelina.spring.jax_rs;
 
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.sun.jersey.api.*;

import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.IExpenseService;
 
@Component
@Path("/exp")
public class JaxRsExpenseService {
 
	IExpenseService expenseService;

	@Autowired
	public JaxRsExpenseService(IExpenseService expenseService){
		this.expenseService = expenseService;
	}

	@GET
	@Path("/{name}")
	public Response getMsg(@PathParam("name") String name) {
 
		Expense expense = expenseService.getExpenseByName(name);
			
		String output = "Expense is: " + expense.toString();
 
		return Response.status(200).entity(output).build();
	}
	
	@GET
	public Response getMsg2() {
 
		List<Expense> expenses = expenseService.getExpenses();
		
		String output = "Expenses are: ";
		for(Expense e: expenses){
			output += e.toString() + ", ";
		}
		return Response.status(200).entity(output).build();
 
	}
 
}