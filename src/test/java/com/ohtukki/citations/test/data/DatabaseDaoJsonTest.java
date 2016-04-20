package com.ohtukki.citations.test.data;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.ohtukki.citations.data.DatabaseJsonDao;
import com.ohtukki.citations.models.ArticleCitation;
import com.ohtukki.citations.models.Citation;

public class DatabaseDaoJsonTest {
    private DatabaseJsonDao init() {
        DatabaseJsonDao dao = new DatabaseJsonDao();
        dao.clear();
        for (int i = 0; i < 10; i++) {
            Citation ref = new ArticleCitation();
            ref.setId("" + i);
            ref.setAuthor("Author["+i+"]");
            ref.setTitle("Title["+i+"]");
            ref.setJournal("Journal["+i+"]");
            ref.setYear("Year["+i+"]");
            dao.save(ref);
        }
        return dao;
    }
    @Test 
    public void testFind() {
        DatabaseJsonDao dao = init();
        Citation citation = dao.find("1");
        
        assertEquals("Author[1]", citation.getAuthor());
    }
    @Test 
    public void testDelete() {
        DatabaseJsonDao dao = init();
        dao.delete("2");
        dao.saveAll();
        
        assertEquals(9, dao.all().size());
    }
    @Test
    public void testDeleteClass() {
        DatabaseJsonDao dao = init();
        Citation ref = new ArticleCitation();
        ref.setId("1");
        dao.delete(ref);
        dao.saveAll();
        
        assertEquals(9, dao.all().size());
    }

    @Test
    public void testAddSave() {
        DatabaseJsonDao dao = init();
    }
}
