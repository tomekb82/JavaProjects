package eu.tbelina.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public class UserRest {

	 @Override
	public String toString() {
		return "UserRest [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	public static final int INVALID_ID = -1;
	 private int id = INVALID_ID;
	 
	 @Id
		@GenericGenerator(name="hilo-strategy", strategy = "hilo")
		@GeneratedValue(generator = "hilo-strategy")
	 public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private String name;
	 private int age;

	 
}
