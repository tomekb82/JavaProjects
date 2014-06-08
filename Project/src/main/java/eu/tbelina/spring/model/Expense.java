package eu.tbelina.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity()
@Table(name="expenses")
public class Expense implements Serializable{
	
	private String name;
	private float value;
	private int quantity;
	private Date date;
	
	public Expense(long id, String name, float value, int quantity, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.quantity = quantity;
		this.date = date;
	}
	
	@Id
	@Column(name="ID", unique = true, nullable = false)
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="NAME", unique = false, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="VALUE", unique = false, nullable = false)
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
	@Column(name="QUANTITY", unique = false, nullable = false)
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Column(name="Date", unique = false, nullable = false)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Expense [id=" + id + ", name=" + name + ", value=" + value
				+ ", quantity=" + quantity + ", date=" + date + "]";
	}

}
