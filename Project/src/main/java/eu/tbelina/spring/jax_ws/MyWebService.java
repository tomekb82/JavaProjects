package eu.tbelina.spring.jax_ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;

import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.IExpenseService;

//Service Implementation
@WebService(serviceName="MyWebService", endpointInterface = "eu.tbelina.spring.jax_ws.IMyWebService")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class MyWebService implements IMyWebService{

	//Dependency Injection (DI) via Spring
	MyBObject myBObject;
	IExpenseService expenseService;

	@WebMethod(exclude=true)
	public void setMyBObject(MyBObject bObject) {
		this.myBObject = bObject;
	}

	@WebMethod(operationName="printMessage")
	public String printMessage(String s) {
		return myBObject.printMessage(s);
	}
	
	@WebMethod(exclude=true)
	public void setExpenseService(IExpenseService expenseService) {
		this.expenseService = expenseService;
	}
	 
	// INFO: List MUST BE converted to array because JAXB not working with interfaces
	@WebMethod(operationName="getExpenses")
	public Expense[] getExpenses() {
		
		List<Expense> exList = expenseService.getExpenses(); 
		Expense[] expenseArray = new Expense[exList.size()];
		expenseArray = exList.toArray(expenseArray);
		
		return expenseArray;
	}
	
}
