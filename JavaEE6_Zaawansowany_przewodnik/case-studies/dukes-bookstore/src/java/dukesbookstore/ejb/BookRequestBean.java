/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package dukesbookstore.ejb;

import dukesbookstore.entity.Book;
import dukesbookstore.exception.BookNotFoundException;
import dukesbookstore.exception.BooksNotFoundException;
import dukesbookstore.exception.OrderException;
import dukesbookstore.web.managedbeans.ShoppingCart;
import dukesbookstore.web.managedbeans.ShoppingCartItem;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * <p>Stateless session bean for the bookstore example.</p>
 */
@Stateless
@Named
public class BookRequestBean {
    private static final Logger logger = Logger.getLogger(
                "dukesbookstore.ejb.BookRequestBean");
    @PersistenceContext
    private EntityManager em;

    public BookRequestBean() throws Exception {
    }

    public void createBook(
        String bookId,
        String surname,
        String firstname,
        String title,
        Double price,
        Boolean onsale,
        Integer calendarYear,
        String description,
        Integer inventory) {
        try {
            Book book = new Book(
                        bookId,
                        surname,
                        firstname,
                        title,
                        price,
                        onsale,
                        calendarYear,
                        description,
                        inventory);
            logger.log(Level.INFO, "Created book {0}", bookId);
            em.persist(book);
            logger.log(Level.INFO, "Persisted book {0}", bookId);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }

    public List<Book> getBooks() throws BooksNotFoundException {
        try {
            return (List<Book>) em.createNamedQuery("findBooks")
                                  .getResultList();
        } catch (Exception ex) {
            throw new BooksNotFoundException(
                    "Could not get books: " + ex.getMessage());
        }
    }

    public Book getBook(String bookId) throws BookNotFoundException {
        Book requestedBook = em.find(Book.class, bookId);

        if (requestedBook == null) {
            throw new BookNotFoundException("Couldn't find book: " + bookId);
        }

        return requestedBook;
    }

    public void buyBooks(ShoppingCart cart) throws OrderException {
        Collection<ShoppingCartItem> items = cart.getItems();
        Iterator<ShoppingCartItem> i = items.iterator();

        try {
            while (i.hasNext()) {
                ShoppingCartItem sci = (ShoppingCartItem) i.next();
                Book bd = (Book) sci.getItem();
                String id = bd.getBookId();
                int quantity = sci.getQuantity();
                buyBook(id, quantity);
            }
        } catch (Exception ex) {
            throw new OrderException("Commit failed: " + ex.getMessage());
        }
    }

    public void buyBook(
        String bookId,
        int quantity) throws OrderException {
        try {
            Book requestedBook = em.find(Book.class, bookId);

            if (requestedBook != null) {
                int inventory = requestedBook.getInventory();

                if ((inventory - quantity) >= 0) {
                    int newInventory = inventory - quantity;
                    requestedBook.setInventory(newInventory);
                } else {
                    throw new OrderException(
                            "Not enough of " + bookId
                            + " in stock to complete order.");
                }
            }
        } catch (Exception ex) {
            throw new OrderException(
                    "Couldn't purchase book: " + bookId + ex.getMessage());
        }
    }

    public void updateInventory(ShoppingCart cart) throws OrderException {
        try {
            buyBooks(cart);
        } catch (Exception ex) {
            throw new OrderException(
                    "Inventory update failed: " + ex.getMessage());
        }
    }
}
