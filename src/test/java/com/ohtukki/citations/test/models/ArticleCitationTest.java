package com.ohtukki.citations.test.models;

import com.ohtukki.citations.models.ArticleCitation;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArticleCitationTest {

    ArticleCitation ac;

    public ArticleCitationTest() {
    }

    @Test
    public void createBibtexEntryWorksWithNullValues() {
        ac = new ArticleCitation();
        assertEquals("@ARTICLE{\n}\n\n", ac.createBibtexEntry());
    }

    @Test
    public void createBibtexEntryWorksWithEmptyStrings() {
        ac = createArticleCitationWithEmptyStrings();
        assertEquals("@ARTICLE{\n}\n\n", ac.createBibtexEntry());
    }

    @Test
    public void createBibtexEntryWorksWithSomeStrings() {
        ac = new ArticleCitation();
        ac.setAuthor("Pekka K. Kirjailija");
        ac.setId("PK95");
        assertEquals("@ARTICLE{PK95,\nauthor = {Pekka K. Kirjailija},\n}\n\n",
                ac.createBibtexEntry());
    }
    
    @Test
    public void createBibtexEntryWorksWithUmlauts() {
        ac = new ArticleCitation();
        ac.setAuthor("Pekka Ö. Mäki");
        ac.setId("PK95");
        assertEquals("@ARTICLE{PK95,\nauthor = {Pekka \\\"{O}. M\\\"{a}ki},\n}\n\n",
                ac.createBibtexEntry());
    }

    private ArticleCitation createArticleCitationWithEmptyStrings() {
        ArticleCitation temp = new ArticleCitation();
        temp.setId("");
        temp.setAuthor("");
        temp.setTitle("");
        temp.setJournal("");
        temp.setYear("");
        temp.setVolume("");
        temp.setNumber("");
        temp.setPages("");
        temp.setMonth("");
        temp.setNote("");
        temp.setKey("");
        return temp;
    }
}
