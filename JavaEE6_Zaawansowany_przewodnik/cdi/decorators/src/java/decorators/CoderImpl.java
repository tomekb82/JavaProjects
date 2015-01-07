/*
 * Copyright 2013 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package decorators;


/**
 * Implementacja interfejsu Coder przesuwającego litery w tekście.
 */
public class CoderImpl implements Coder {
    /**
     * Przesuwa litery tekstu o liczbę liter w alfabecie
     * wskazaną jako drugi argument.
     *
     * @param s     wejściowy tekst
     * @param tval  liczba znaków przesunięcia
     * @return      tekst po przekształceniu
     */
    @Logged
    @Override
    public String codeString(
        String s,
        int tval) {
        final int SPACE_CHAR = 32;
        final int CAPITAL_A = 65;
        final int CAPITAL_Z = 90;
        final int SMALL_A = 97;
        final int SMALL_Z = 122;

        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < sb.length(); i++) {
            int cp = sb.codePointAt(i);
            int cplus = cp + tval;

            if (cp == SPACE_CHAR) // spacja
             {
                cplus = SPACE_CHAR;
            }

            if ((cp >= CAPITAL_A) && (cp <= CAPITAL_Z)) { // duża litera

                if (cplus > CAPITAL_Z) {
                    cplus = cplus - 26;
                }
            } else if ((cp >= SMALL_A) && (cp <= SMALL_Z)) { // mała litera

                if (cplus > SMALL_Z) {
                    cplus = cplus - 26;
                }
            } else { // znaki przestanowe itp.
                cplus = cp;
            }

            char c = (char) cplus;
            sb.setCharAt(i, c);
        }

        return (sb.toString());
    }
}
