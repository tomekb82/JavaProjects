package eu.tbelina.spring.jax_ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import eu.tbelina.spring.model.Expense;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface IMyWebService {

	@WebMethod public String printMessage(String s);
	@WebMethod public Expense[] getExpenses();
	
}
