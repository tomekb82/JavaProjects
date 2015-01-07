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
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 * <p>Managed bean for the
 * <code>/bookdetails.xhtml</code> page.</p>
 */
@Named("details")
@SessionScoped
public class BookDetailsBean extends AbstractBean implements Serializable {
    private static final long serialVersionUID = 2209748452115843974L;

    /**
     * <p>Add the displayed item to our shopping cart.</p>
     */
    public String add() {
        Book book = (Book) context()
                               .getExternalContext()
                               .getSessionMap()
                               .get("selected");
        cart.add(
            book.getBookId(),
            book);
        message(
            null,
            "ConfirmAdd",
            new Object[] { book.getTitle() });

        return ("bookcatalog");
    }
}
