/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package sb;

import javax.ejb.Remote;


/**
 * Remote interface for Publisher enterprise bean. Declares one
 * business method.
 */
@Remote
public interface PublisherRemote {
    void publishNews();
}
