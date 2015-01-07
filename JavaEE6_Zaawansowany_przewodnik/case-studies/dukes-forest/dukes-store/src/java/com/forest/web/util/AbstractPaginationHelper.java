/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package com.forest.web.util;

import javax.faces.model.DataModel;


public abstract class AbstractPaginationHelper {
    public static final int DEFAULT_SIZE = 10;
    private transient int page;
    private transient int pageSize;

    public AbstractPaginationHelper(int pageSize) {
        this.pageSize = pageSize;
    }

    public abstract int getItemsCount();

    public abstract DataModel createPageDataModel();

    public int getPageFirstItem() {
        return page * pageSize;
    }

    public int getPageLastItem() {
        int i = (getPageFirstItem() + pageSize) - 1;
        int count = getItemsCount() - 1;

        if (i > count) {
            i = count;
        }

        if (i < 0) {
            i = 0;
        }

        return i;
    }

    public boolean isHasNextPage() {
        return (((page + 1) * pageSize) + 1) <= getItemsCount();
    }

    public void nextPage() {
        if (isHasNextPage()) {
            page++;
        }
    }

    public boolean isHasPreviousPage() {
        return page > 0;
    }

    public void previousPage() {
        if (isHasPreviousPage()) {
            page--;
        }
    }

    public int getPageSize() {
        return pageSize;
    }
}
