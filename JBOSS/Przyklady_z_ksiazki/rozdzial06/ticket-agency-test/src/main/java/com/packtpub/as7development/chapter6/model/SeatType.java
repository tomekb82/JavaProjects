package com.packtpub.as7development.chapter6.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
 

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue; 
/**
 * The persistent class for the seat_type database table.
 * 
 */
@Entity
@Table(name="seat_type")
public class SeatType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 1, max = 25, message = "Wpisz opis miejsca (maksymalnie 25 znaków)")
	@Pattern(regexp = "[A-Za-z ]*", message = "Opis może zawierać tylko litery lub spacje")
	private String description;

	@NotNull
	private int price;

	@NotNull
	private int quantity;



	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	//bi-directional many-to-one association to Seat
	@OneToMany(mappedBy="seatType", fetch=FetchType.EAGER)
	private List<Seat> seats;

	public SeatType() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Seat> getSeats() {
		return this.seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}