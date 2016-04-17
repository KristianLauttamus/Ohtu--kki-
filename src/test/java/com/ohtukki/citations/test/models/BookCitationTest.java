package com.ohtukki.citations.test.models;

import com.ohtukki.citations.models.BookCitation;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookCitationTest {

    BookCitation cit;

    @Test
    public void createBibtexEntryWorksWithNullValues() {
        cit = new BookCitation();
        assertEquals("@BOOK{\n}\n\n", cit.createBibtexEntry());
    }

    @Test
    public void createBibtexEntryWorksWithEmptyStrings() {
        cit = createBookCitationWithStrings("");
        assertEquals("@BOOK{\n}\n\n", cit.createBibtexEntry());
    }

    @Test
    public void createBibtexEntryWorksWithSomeStrings() {
        cit = new BookCitation();
        cit.setAuthor("Pekka K. Kirjailija");
        cit.setId("PK95");
        assertEquals("@BOOK{PK95,\nauthor = {Pekka K. Kirjailija},\n}\n\n",
                cit.createBibtexEntry());
    }

    @Test
    public void createBibtexEntryWorksWithUmlauts() {
        cit = new BookCitation();
        cit.setAuthor("Pekka Ö. Mäki");
        cit.setId("PK95");
        assertEquals("@BOOK{PK95,\nauthor = {Pekka \\\"{O}. M\\\"{a}ki},\n}\n\n",
                cit.createBibtexEntry());
    }
    
    @Test
    public void accessingEditionFieldWorks() {
        cit = createBookCitationWithStrings("testiarvo");
        assertEquals("testiarvo", cit.getEdition());
    }
    
    @Test
    public void accessingSeriesFieldWorks() {
        cit = createBookCitationWithStrings("testiarvo");
        assertEquals("testiarvo", cit.getSeries());
    }
    
    @Test
    public void accessingEditorFieldWorks() {
        cit = createBookCitationWithStrings("testiarvo");
        assertEquals("testiarvo", cit.getEditor());
    }
    
    @Test
    public void accessingAddressFieldWorks() {
        cit = createBookCitationWithStrings("testiarvo");
        assertEquals("testiarvo", cit.getAddress());
    }

    private BookCitation createBookCitationWithStrings(String s) {
        BookCitation temp = new BookCitation();
        temp.setId(s);
        temp.setAuthor(s);
        temp.setEditor(s);
        temp.setName(s);
        temp.setPublisher(s);
        temp.setYear(s);
        temp.setVolume(s);
        temp.setSeries(s);
        temp.setAddress(s);
        temp.setEdition(s);
        temp.setMonth(s);
        temp.setNote(s);
        temp.setKey(s);
        return temp;
    }

}
