package com.ohtukki.citations.test.models;

import org.junit.Test;
import com.ohtukki.citations.models.*;
import static org.junit.Assert.*;

public class CitationTest {
    public ArticleCitation articleCitation;
    
    @Test
    public void unvalidFieldsWontGetThrough(){
        articleCitation = new ArticleCitation();
        articleCitation.requiredFields = new String[]{"id", "title"};
        
        articleCitation.setId("Not empty");
        
        assertFalse(articleCitation.validate(false));
    }
    
    @Test
    public void almostDynamicFields(){
        articleCitation = new ArticleCitation();
        articleCitation.setField("id", "test");
        
        assertEquals("test", articleCitation.getField("id"));
    }
    
    @Test
    public void validFieldsGetValidated(){
        articleCitation = new ArticleCitation();
        articleCitation.requiredFields = new String[]{"id", "title"};
        
        articleCitation.setId("Not empty");
        articleCitation.setTitle("Not empty");
        
        assertTrue(articleCitation.validate(false));
    }
    
    @Test
    public void citationFieldsWorkWithoutOptionals(){
        ArticleCitation articleCitation = new ArticleCitation();
        articleCitation.optionalFields = new String[]{};
        articleCitation.requiredFields = new String[]{"id", "test"};
        
        assertEquals(2, articleCitation.getRequiredFields().length);
        assertEquals(0, articleCitation.getOptionalFields().length);
    }
    
    @Test
    public void allCitationTypesHaveIDField(){
        assertTrue(new ArticleCitation().getRequiredFields()[0].equals("id"));
        assertTrue(new BookCitation().getRequiredFields()[0].equals("id"));
        assertTrue(new BookletCitation().getRequiredFields()[0].equals("id"));
        assertTrue(new ConferenceCitation().getRequiredFields()[0].equals("id"));
        assertTrue(new InbookCitation().getRequiredFields()[0].equals("id"));
        assertTrue(new IncollectionCitation().getRequiredFields()[0].equals("id"));
        assertTrue(new InproceedingsCitation().getRequiredFields()[0].equals("id"));
        assertTrue(new ManualCitation().getRequiredFields()[0].equals("id"));
        assertTrue(new MastersthesisCitation().getRequiredFields()[0].equals("id"));
        assertTrue(new MiscCitation().getRequiredFields()[0].equals("id"));
        assertTrue(new PHDThesisCitation().getRequiredFields()[0].equals("id"));
        assertTrue(new ProceedingsCitation().getRequiredFields()[0].equals("id"));
        assertTrue(new TechReportCitation().getRequiredFields()[0].equals("id"));
        assertTrue(new UnpublishedCitation().getRequiredFields()[0].equals("id"));
    }
    
    @Test
    public void allCitationTypesHaveCorrectCitationType(){
        assertTrue(new ArticleCitation().getCitationType().equals("Article"));
        assertTrue(new BookCitation().getCitationType().equals("Book"));
        assertTrue(new BookletCitation().getCitationType().equals("Booklet"));
        assertTrue(new ConferenceCitation().getCitationType().equals("Conference"));
        assertTrue(new InbookCitation().getCitationType().equals("Inbook"));
        assertTrue(new IncollectionCitation().getCitationType().equals("Incollection"));
        assertTrue(new InproceedingsCitation().getCitationType().equals("Inproceedings"));
        assertTrue(new ManualCitation().getCitationType().equals("Manual"));
        assertTrue(new MastersthesisCitation().getCitationType().equals("Mastersthesis"));
        assertTrue(new MiscCitation().getCitationType().equals("Misc"));
        assertTrue(new PHDThesisCitation().getCitationType().equals("PHDThesis"));
        assertTrue(new ProceedingsCitation().getCitationType().equals("Proceedings"));
        assertTrue(new TechReportCitation().getCitationType().equals("TechReport"));
        assertTrue(new UnpublishedCitation().getCitationType().equals("Unpublished"));
    }
    
    @Test
    public void createBibtexEntryWorksWithNullValues() {
        articleCitation = new ArticleCitation();
        assertEquals("@ARTICLE{\n}\n\n", articleCitation.createBibtexEntry());
    }

    @Test
    public void createBibtexEntryWorksWithEmptyStrings() {
        articleCitation = createArticleCitationWithEmptyStrings();
        assertEquals("@ARTICLE{\n}\n\n", articleCitation.createBibtexEntry());
    }

    @Test
    public void createBibtexEntryWorksWithSomeStrings() {
        articleCitation = new ArticleCitation();
        articleCitation.setAuthor("Pekka K. Kirjailija");
        articleCitation.setId("PK95");
        assertEquals("@ARTICLE{PK95,\nauthor = {Pekka K. Kirjailija},\n}\n\n",
                articleCitation.createBibtexEntry());
    }
    
    @Test
    public void createBibtexEntryWorksWithUmlauts() {
        articleCitation = new ArticleCitation();
        articleCitation.setAuthor("Pekka Ö. Mäki");
        articleCitation.setId("PK95");
        assertEquals("@ARTICLE{PK95,\nauthor = {Pekka \\\"{O}. M\\\"{a}ki},\n}\n\n",
                articleCitation.createBibtexEntry());
    }
    
    @Test
    public void createBookCitationWorks() {
        BookCitation cit = new BookCitation();
        cit.setAuthor("Pekka Ö. Mäki");
        cit.setId("PK95");
        assertEquals("@BOOK{PK95,\nauthor = {Pekka \\\"{O}. M\\\"{a}ki},\n}\n\n",
                cit.createBibtexEntry());
    }
    
    @Test
    public void createInproceedingsCitationWorks() {
        InproceedingsCitation cit = new InproceedingsCitation();
        cit.setAuthor("Pekka Ö. Mäki");
        cit.setId("PK95");
        assertEquals("@INPROCEEDINGS{PK95,\nauthor = {Pekka \\\"{O}. M\\\"{a}ki},\n}\n\n",
                cit.createBibtexEntry());
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
    
    @Test
    public void ruleLogicWorks() {
        BookCitation cit = new BookCitation();
        assertFalse(cit.fieldIsRequired("foo", ""));
        assertFalse(cit.fieldIsRequired("foo", "bar"));
        assertFalse(cit.fieldIsRequired("foo", "required_if_empty"));
        assertFalse(cit.fieldIsRequired("foo", "only_other"));
    }
    
    // Some tests for unexpected things to happen
    @Test
    public void validateFieldCatchesException() {
        articleCitation = new ArticleCitation();
        assertFalse(articleCitation.validateField(null));
    }
    
    @Test
    public void getRequiredFieldsWorksWithEmptyFields() {
        articleCitation = new ArticleCitation();
        articleCitation.requiredFields = new String[]{};
        assertTrue(articleCitation.getRequiredFields().length == 0);
    }
    
    @Test
    public void getRequiredFieldsCatchesException() {
        articleCitation = new ArticleCitation();
        articleCitation.requiredFields = null;
        articleCitation.getRequiredFields();
        // if test passes, code works
    }
    
    @Test
    public void getOptionalFieldsCatchesException() {
        articleCitation = new ArticleCitation();
        articleCitation.optionalFields = null;
        articleCitation.getOptionalFields();
        // if test passes, code works
    }
    
    @Test
    public void setFieldCatchesException() {
        articleCitation = new ArticleCitation();
        articleCitation.setField(null, "some value");
        // if test passes, code works
    }
    
    @Test
    public void getFieldCatchesException() {
        articleCitation = new ArticleCitation();
        articleCitation.getField(null);
        // if test passes, code works
    }
    
    @Test
    public void getFieldSplitWorks() {
        BookCitation cit = new BookCitation();
        cit.type = "foo:bar";
        assertEquals("foo", cit.getField("type"));
    }
}
