/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package producermethods;


/**
 * Implementacja Coder, która nie zmienia tekstu, a jedynie wyświetla
 * przekazane argumenty.
 */
public class TestCoderImpl implements Coder {
    /**
     * Zwraca tekst zawierający przekazane argumenty.
     *
     * @param s     wejściowy tekst
     * @param tval  liczba znaków przesunięcia
     * @return      tekst z przekazanymi argumentami
     */
    @Override
    public String codeString(
        String s,
        int tval) {
        return ("tekst wejściowy to " +s+", przesunięcie: " + tval);
    }
}
