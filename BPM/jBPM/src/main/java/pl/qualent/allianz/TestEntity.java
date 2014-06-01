package pl.qualent.allianz;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TestEntity")
public class TestEntity implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 2323508538328842021L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
		 
	@Column
	private String testString;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTestString() {
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}
}
