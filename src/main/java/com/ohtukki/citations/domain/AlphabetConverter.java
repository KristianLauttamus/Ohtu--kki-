/*
 * Package contains utility classes
 */
package com.ohtukki.citations.domain;

/**
 * Class converts umlaut chars in strings to bibtex chars and vice versa
 */
public class AlphabetConverter {

    /**
     * Method converts umlaut chars to bibtex chars
     *
     * @param s String to convert
     * @return Returns the converted string
     */
    public String umlautsToBibtex(String s) {
        s = s.replace("ä", "\\\"{a}");
        s = s.replace("ö", "\\\"{o}");
        s = s.replace("Ä", "\\\"{A}");
        s = s.replace("Ö", "\\\"{O}");
        return s;
    }

    /**
     * Method converts bibtex chars to umlaut chars
     *
     * @param s String to convert
     * @return Returns the converted string
     */
    public String bibtexToUmlauts(String s) {
        s = s.replace("\\\"{a}", "ä");
        s = s.replace("\\\"{o}", "ö");
        s = s.replace("\\\"{A}", "Ä");
        s = s.replace("\\\"{O}", "Ö");
        return s;
    }
}
