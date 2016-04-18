package com.ohtukki.citations.test.models;

import com.ohtukki.citations.models.InproceedingsCitation;
import org.junit.Test;
import static org.junit.Assert.*;

public class InproceedingsCitationTest {
    
    InproceedingsCitation cit;

    @Test
    public void createBibtexEntryWorksWithNullValues() {
        cit = new InproceedingsCitation();
        assertEquals("@INPROCEEDINGS{\n}\n\n", cit.createBibtexEntry());
    }

    @Test
    public void createBibtexEntryWorksWithEmptyStrings() {
        cit = createInproceedingsCitationWithStrings("");
        assertEquals("@INPROCEEDINGS{\n}\n\n", cit.createBibtexEntry());
    }

    @Test
    public void createBibtexEntryWorksWithSomeStrings() {
        cit = new InproceedingsCitation();
        cit.setAuthor("Pekka K. Kirjailija");
        cit.setId("PK95");
        assertEquals("@INPROCEEDINGS{PK95,\nauthor = {Pekka K. Kirjailija},\n}\n\n",
                cit.createBibtexEntry());
    }

    @Test
    public void createBibtexEntryWorksWithUmlauts() {
        cit = new InproceedingsCitation();
        cit.setAuthor("Pekka Ö. Mäki");
        cit.setId("PK95");
        assertEquals("@INPROCEEDINGS{PK95,\nauthor = {Pekka \\\"{O}. M\\\"{a}ki},\n}\n\n",
                cit.createBibtexEntry());
    }
    
    @Test
    public void accessingBooktitleFieldWorks() {
        cit = createInproceedingsCitationWithStrings("testiarvo");
        assertEquals("testiarvo", cit.getBooktitle());
    }
    
    @Test
    public void accessingOrganizationFieldWorks() {
        cit = createInproceedingsCitationWithStrings("testiarvo");
        assertEquals("testiarvo", cit.getOrganization());
    }

    private InproceedingsCitation createInproceedingsCitationWithStrings(String s) {
        InproceedingsCitation temp = new InproceedingsCitation();
        temp.setId(s);
        temp.setAuthor(s);
        temp.setTitle(s);
        temp.setBooktitle(s);
        temp.setYear(s);
        temp.setEditor(s);
        temp.setPages(s);
        temp.setOrganization(s);
        temp.setPublisher(s);
        temp.setAddress(s);
        temp.setMonth(s);
        temp.setNote(s);
        temp.setKey(s);
        return temp;
    }

}
