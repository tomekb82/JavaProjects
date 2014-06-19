package eu.tbelina.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="income")
public class Income implements Serializable{
	
	private int id;
	private String name;
	private long value;
	private Date date;
	
	/* Realtions */
	private User user; // OneToMany relation
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INCOME_ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "VALUE")
	public long getValue() {
		return value;
	}
	
	public void setValue(long value) {
		this.value = value;
	}

	@Temporal(TemporalType.DATE)
    @Column(name = "DATE", unique = false, nullable = false, length = 10)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Income [id=" + id + ", name=" + name + ", value=" + value
				+ ", date=" + date + ", user=" + user + "]";
	}


}
