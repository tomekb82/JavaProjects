package eu.tbelina.spring.comparator;

import java.util.Date;

import eu.tbelina.spring.model.Expense;

public class ExpenseComparable implements Comparable<ExpenseComparable>{

	private long id;
	private String name;
	private float value;
	private int quantity;
	private Date date;
	
	@Override
	public int compareTo(ExpenseComparable o) {
		
		return this.quantity - o.getQuantity();
	}
	
	public ExpenseComparable(long id, String name, float value, int quantity,
			Date date) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.quantity = quantity;
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "ExpenseComparable [id=" + id + ", name=" + name + ", value="
				+ value + ", quantity=" + quantity + ", date=" + date + "]";
	}

	
	public long getId() {
		return id;
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


	
}
