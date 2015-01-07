/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;


@Decorator
public abstract class CoderDecorator implements Coder {
    @Inject
    @Delegate
    @Any
    Coder coder;

    @Override
    public String codeString(
        String s,
        int tval) {
        int len = s.length();

        return "\"" + s + "\" staje się " + "\"" + coder.codeString(s, tval)
        + "\", liczba znaków: " + len;
    }
}
