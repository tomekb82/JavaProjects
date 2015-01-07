/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package dukesbookstore.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * <p>Entity class for bookstore example.</p>
 */
@Entity
@Table(name = "WEB_BOOKSTORE_BOOKS")
@NamedQuery(name = "findBooks", query = "SELECT b FROM Book b ORDER BY b.bookId")
public class Book implements Serializable {
    private static final long serialVersionUID = -4146681491856848089L;
    private Boolean onsale;
    private Double price;
    private Integer calendarYear;
    private Integer inventory;
    @Id
    @NotNull
    private String bookId;
    private String description;
    private String firstname;
    private String surname;
    private String title;

    public Book() {
    }

    public Book(
        String bookId,
        String surname,
        String firstname,
        String title,
        Double price,
        Boolean onsale,
        Integer calendarYear,
        String description,
        Integer inventory) {
        this.setBookId(bookId);
        this.setSurname(surname);
        this.setFirstname(firstname);
        this.setTitle(title);
        this.setPrice(price);
        this.setOnsale(onsale);
        this.setCalendarYear(calendarYear);
        this.setDescription(description);
        this.setInventory(inventory);
    }

    public Book(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getOnsale() {
        return onsale;
    }

    public void setOnsale(Boolean onsale) {
        this.onsale = onsale;
    }

    public Integer getCalendarYear() {
        return calendarYear;
    }

    public void setCalendarYear(Integer calendarYear) {
        this.calendarYear = calendarYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ((bookId != null) ? bookId.hashCode() : 0);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Book)) {
            return false;
        }

        Book other = (Book) object;

        if (((this.bookId == null) && (other.bookId != null))
                || ((this.bookId != null) && !this.bookId.equals(other.bookId))) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "bookstore.entities.Book[ bookId=" + bookId + " ]";
    }
}
