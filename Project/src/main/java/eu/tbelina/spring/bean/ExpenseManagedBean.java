package eu.tbelina.spring.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.service.IExpenseService;

/**
 * 
 * User Managed Bean
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 * 
 */
@ManagedBean(name = "expenseMB")
@SessionScoped
public class ExpenseManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "showExpenses";
	private static final String ERROR = "error";
	
	// Spring User Service is injected...
	@ManagedProperty(value = "#{expenseService}")
	IExpenseService expenseService;

	List<Expense> expenseList;

	private long id;
	private String name;
	private float value;
	private int quantity;
	private Date date;
	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	public String addExpense() {
		try {
			Expense expense = new Expense();
			expense.setId(getId());
			expense.setName(getName());
			expense.setQuantity(getQuantity());
			expense.setValue(getValue());
			expense.setDate(new Date());//getDate());
			getExpenseService().addExpense(expense);
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return ERROR;
	}
	
	public void reset() {
		this.setId(0);
		this.setName("");
		this.setQuantity(0);
		this.setValue(0);
	}

	public List<Expense> getExpenseList() {
		expenseList = new ArrayList<Expense>();
		expenseList.addAll(getExpenseService().getExpenses());
		System.out.println("getExpenseList" + expenseList.get(0));
		return expenseList;
	}
	
	public IExpenseService getExpenseService() {
		return expenseService;
	}

	public void setExpenseService(IExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}


}