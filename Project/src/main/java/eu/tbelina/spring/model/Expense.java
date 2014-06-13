package eu.tbelina.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Entity
@Table(name="expenses")
public class Expense implements Serializable{
	
	private long id;
	private String name;
	private float value;
	private int quantity;
	private Date date;
	
	public Expense(){
		this.date = new Date();
	}
	
	public Expense(long id, String name, float value, int quantity, Date date) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.quantity = quantity;
		this.date = date;
	}
	
	public Expense(String name, float value, int quantity, Date date) {
		this.name = name;
		this.value = value;
		this.quantity = quantity;
		this.date = date;
	}
	
	@Id
	@GenericGenerator(name="hilo-strategy", strategy = "hilo")
	@GeneratedValue(generator = "hilo-strategy")
	@Column(name="ID", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="NAME", unique = false, nullable = false)
	@Size(min=3, max=20, message="User name must contain between 3-20 letters")
	@Pattern(regexp="^[a-zA-Z]+$")
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
	//@Size(min=0, max=5, message="Value to big")
	//@Pattern(regexp="[0-9]")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Column(name="Date", unique = false, nullable = true)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + quantity;
		result = prime * result + Float.floatToIntBits(value);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Expense other = (Expense) obj;	
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;		
		if (quantity != other.quantity)
			return false;	
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
			return false;
		return true;
	}
	
	

}
