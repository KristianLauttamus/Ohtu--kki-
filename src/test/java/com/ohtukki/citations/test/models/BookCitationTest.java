package com.ohtukki.citations.test.models;

import com.ohtukki.citations.models.BookCitation;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookCitationTest {

    BookCitation bc;

    @Test
    public void createBibtexEntryWorksWithNullValues() {
        bc = new BookCitation();
        assertEquals("@BOOK{\n}\n\n", bc.createBibtexEntry());
    }

    @Test
    public void createBibtexEntryWorksWithEmptyStrings() {
        bc = createBookCitationWithEmptyStrings();
        assertEquals("@BOOK{\n}\n\n", bc.createBibtexEntry());
    }

    @Test
    public void createBibtexEntryWorksWithSomeStrings() {
        bc = new BookCitation();
        bc.setAuthor("Pekka K. Kirjailija");
        bc.setId("PK95");
        assertEquals("@BOOK{PK95,\nauthor = {Pekka K. Kirjailija},\n}\n\n",
                bc.createBibtexEntry());
    }

    @Test
    public void createBibtexEntryWorksWithUmlauts() {
        bc = new BookCitation();
        bc.setAuthor("Pekka Ö. Mäki");
        bc.setId("PK95");
        assertEquals("@BOOK{PK95,\nauthor = {Pekka \\\"{O}. M\\\"{a}ki},\n}\n\n",
                bc.createBibtexEntry());
    }

    private BookCitation createBookCitationWithEmptyStrings() {
        BookCitation temp = new BookCitation();
        temp.setId("");
        temp.setAuthor("");
        temp.setEditor("");
        temp.setName("");
        temp.setPublisher("");
        temp.setYear("");
        temp.setVolume("");
        temp.setSeries("");
        temp.setAddress("");
        temp.setEdition("");
        temp.setMonth("");
        temp.setNote("");
        temp.setKey("");
        return temp;
    }

}
