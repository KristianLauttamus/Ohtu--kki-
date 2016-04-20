package com.ohtukki.citations.test.models;

import org.junit.Test;
import com.ohtukki.citations.models.*;
import static org.junit.Assert.*;

public class CitationTest {
    @Test
    public void unvalidFieldsWontGetThrough(){
        ArticleCitation articleCitation = new ArticleCitation();
        articleCitation.requiredFields = new String[]{"id", "title"};
        
        articleCitation.setId("Not empty");
        
        assertFalse(articleCitation.validate());
    }
    
    @Test
    public void validFieldsGetValidated(){
        ArticleCitation articleCitation = new ArticleCitation();
        articleCitation.requiredFields = new String[]{"id", "title"};
        
        articleCitation.setId("Not empty");
        articleCitation.setTitle("Not empty");
        
        assertTrue(articleCitation.validate());
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
}
