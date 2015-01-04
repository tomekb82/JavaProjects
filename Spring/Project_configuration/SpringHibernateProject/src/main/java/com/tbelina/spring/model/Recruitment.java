package com.tbelina.spring.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.text.Position;

import com.amazonaws.services.simpleworkflow.flow.core.Task;

/**
* 
*/
@Entity
@Table(name = "recruitments")
public class Recruitment {

	protected Integer id;  
	//protected Set<Candidate> candidates = new HashSet<Candidate>();  
	//protected Position position;  
	//protected Set<User> users = new HashSet<User>();  
	protected String name;  
	protected Date start;  
	protected Date duration;  
	protected String place;  
	protected Boolean active;  

	public Recruitment() {
	}

	/**
	* 
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	/**
	* 
	* @see Candidate
	*/
	/*@OneToMany(mappedBy = "recruitment", targetEntity = Candidate.class,
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<Candidate> getCandidates() {
		return this.candidates;
	}*/

	/**
	* 
	* 
	*/
	/*@ManyToOne
	@JoinColumn(name = "position_id", referencedColumnName = "id")
	public Position getPosition() {
		return this.position;
	}
*/
	/**
	* 
	* @see User
	*/
	/*@ManyToMany(mappedBy = "recruitments", targetEntity = User.class,
		fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<User> getUsers() {
		return this.users;
	}*/

	/**
	* 
	*/
	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	/**
	* 
	*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start")
	public Date getStart() {
		return this.start;
	}

	/**
	* 
	*/
	@Temporal(TemporalType.TIME)
	@Column(name = "duration")
	public Date getDuration() {
		return this.duration;
	}

	/**
	* 
	*/
	@Column(name = "place")
	public String getPlace() {
		return this.place;
	}

	/**
	* 
	*/
	@Column(name = "active")
	public Boolean getActive() {
		return this.active;
	}

	/**
	* 
	*/
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	* 
	* @see Candidate
	*/
	/*public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}
*/
	/**
	* 
	* @see Task
	*/
	/*public void setPosition(Position position) {
		this.position = position;
	}*/

	/**
	* 
	* @see User
	*/
	/*public void setUsers(Set<User> users) {
		this.users = users;
	}*/

	/**
	* 
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* 
	*/
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	* 
	*/
	public void setDuration(Date duration) {
		this.duration = duration;
	}

	/**
	* 
	*/
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	* 
	*/
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	/**
	* 
	*/
	public Boolean isActive() {
		return this.active;
	}
}
