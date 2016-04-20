package com.ohtukki.citations.test.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ohtukki.citations.data.CitationCreator;
import com.ohtukki.citations.data.Database;
import com.ohtukki.citations.data.DatabaseJsonDao;
import com.ohtukki.citations.models.*;
import io.gsonfire.GsonFireBuilder;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class CitationCreatorTest {
    Database database;
    
    public CitationCreatorTest() {
        this.database = new DatabaseJsonDao(false);
        this.database.clear();
    }
    
    @Test
    public void allCitationTypesReturnCorrectClass(){
        this.database.save(new ArticleCitation());
        this.database.save(new BookCitation());
        this.database.save(new BookletCitation());
        this.database.save(new ConferenceCitation());
        this.database.save(new InbookCitation());
        this.database.save(new IncollectionCitation());
        this.database.save(new InproceedingsCitation());
        this.database.save(new ManualCitation());
        this.database.save(new MastersthesisCitation());
        this.database.save(new MiscCitation());
        this.database.save(new PHDThesisCitation());
        this.database.save(new ProceedingsCitation());
        this.database.save(new TechReportCitation());
        this.database.save(new UnpublishedCitation());
        
        assertEquals(14, this.database.all().size());
        List<Citation> data = this.database.all();
        
        assertEquals("com.ohtukki.citations.models.ArticleCitation", data.get(0).getClass().getName());
        assertEquals("com.ohtukki.citations.models.BookCitation", data.get(1).getClass().getName());
        assertEquals("com.ohtukki.citations.models.BookletCitation", data.get(2).getClass().getName());
        assertEquals("com.ohtukki.citations.models.ConferenceCitation", data.get(3).getClass().getName());
        assertEquals("com.ohtukki.citations.models.InbookCitation", data.get(4).getClass().getName());
        assertEquals("com.ohtukki.citations.models.IncollectionCitation", data.get(5).getClass().getName());
        assertEquals("com.ohtukki.citations.models.InproceedingsCitation", data.get(6).getClass().getName());
        assertEquals("com.ohtukki.citations.models.ManualCitation", data.get(7).getClass().getName());
        assertEquals("com.ohtukki.citations.models.MastersthesisCitation", data.get(8).getClass().getName());
        assertEquals("com.ohtukki.citations.models.MiscCitation", data.get(9).getClass().getName());
        assertEquals("com.ohtukki.citations.models.PHDThesisCitation", data.get(10).getClass().getName());
        assertEquals("com.ohtukki.citations.models.ProceedingsCitation", data.get(11).getClass().getName());
        assertEquals("com.ohtukki.citations.models.TechReportCitation", data.get(12).getClass().getName());
        assertEquals("com.ohtukki.citations.models.UnpublishedCitation", data.get(13).getClass().getName());
        this.database.clear();
    }
}
