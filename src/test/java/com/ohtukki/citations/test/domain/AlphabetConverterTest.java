package com.ohtukki.citations.test.domain;

import com.ohtukki.citations.domain.AlphabetConverter;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlphabetConverterTest {

    AlphabetConverter ac;
    String readable;
    String bibtex;

    public AlphabetConverterTest() {
        ac = new AlphabetConverter();
        readable = "Äh, Öölanti sählää! Åå";
        bibtex = "\\\"{A}h, \\\"{O}\\\"{o}lanti s\\\"{a}hl\\\"{a}\\\"{a}! {\\AA}{\\aa}";
    }

    @Test
    public void umlautsToBibtexWorksWithUmlauts() {
        assertEquals(bibtex, ac.umlautsToBibtex(readable));
    }
    
    @Test
    public void bibtexToUmlautsWorksWithUmlauts() {
        assertEquals(readable, ac.bibtexToUmlauts(bibtex));
    }
    
    @Test
    public void umlautsToBibtexWorksWithoutUmlauts() {
        assertEquals("aurinko paistaa", ac.umlautsToBibtex("aurinko paistaa"));
    }
    
    @Test
    public void bibtexToUmlautsWorksWithoutUmlauts() {
        assertEquals("Aurinko paistaa", ac.bibtexToUmlauts("Aurinko paistaa"));
    }
    
    @Test
    public void umlautsToBibtexWorksWithEmptyString() {
        assertEquals("", ac.umlautsToBibtex(""));
    }
    
    @Test
    public void bibtexToUmlautsWorksWithEmptyString() {
        assertEquals("", ac.bibtexToUmlauts(""));
    }
}
