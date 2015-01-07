/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package dukesbookstore.web.managedbeans;

import dukesbookstore.entity.Book;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 * <p>Managed bean for the <code>/bookcatalog.xhtml</code> page.</p>
 */
@Named("catalog")
@SessionScoped
public class CatalogBean extends AbstractBean implements Serializable {
    private static final long serialVersionUID = -3594317405246398714L;
    private int totalBooks = 0;

    /**
     * <p>Return the currently selected
     * <code>Book</code> instance from the user request.</p>
     */
    protected Book book() {
        Book book = (Book) context()
                               .getExternalContext()
                               .getRequestMap()
                               .get("book");

        return (book);
    }

    /**
     * <p>Add the selected item to our shopping cart.</p>
     */
    public String add() {
        Book book = book();
        cart.add(
            book.getBookId(),
            book);
        message(
            null,
            "ConfirmAdd",
            new Object[] { book.getTitle() });

        return ("bookcatalog");
    }

    /**
     * <p>Show the details page for the current book.</p>
     */
    public String details() {
        context()
            .getExternalContext()
            .getSessionMap()
            .put(
            "selected",
            book());

        return ("bookdetails");
    }

    public int getTotalBooks() {
        totalBooks = cart.getNumberOfItems();

        return totalBooks;
    }

    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public int getBookQuantity() {
        int bookQuantity = 0;
        Book book = book();

        if (book == null) {
            return bookQuantity;
        }

        List<ShoppingCartItem> results = cart.getItems();

        for (Iterator items = results.iterator(); items.hasNext();) {
            ShoppingCartItem item = (ShoppingCartItem) items.next();
            Book bd = (Book) item.getItem();

            if ((bd.getBookId()).equals(book.getBookId())) {
                bookQuantity = item.getQuantity();

                break;
            }
        }

        return bookQuantity;
    }
}
