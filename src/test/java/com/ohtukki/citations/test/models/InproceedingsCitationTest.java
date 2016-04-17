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
        cit = createInproceedingsCitationWithEmptyStrings();
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

    private InproceedingsCitation createInproceedingsCitationWithEmptyStrings() {
        InproceedingsCitation temp = new InproceedingsCitation();
        temp.setId("");
        temp.setAuthor("");
        temp.setName("");
        temp.setBooktitle("");
        temp.setYear("");
        temp.setEditor("");
        temp.setPages("");
        temp.setOrganization("");
        temp.setPublisher("");
        temp.setAddress("");
        temp.setMonth("");
        temp.setNote("");
        temp.setKey("");
        return temp;
    }

}
