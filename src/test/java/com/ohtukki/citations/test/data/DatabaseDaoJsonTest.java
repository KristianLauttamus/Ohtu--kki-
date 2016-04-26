package com.ohtukki.citations.test.data;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ohtukki.citations.data.DatabaseJsonDao;
import com.ohtukki.citations.models.ArticleCitation;
import com.ohtukki.citations.models.BookCitation;
import com.ohtukki.citations.models.Citation;


public class DatabaseDaoJsonTest {

    DatabaseJsonDao dao;

    @Before
    public void init() {
        dao = new DatabaseJsonDao();
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
    }
    @Test 
    public void testFind() {
        Citation citation = dao.find("1");
        
        assertEquals("Author[1]", citation.getAuthor());
    }
    @Test 
    public void testDelete() {
        dao.delete("2");
        dao.saveAll();
        
        assertEquals(9, dao.all().size());
    }
    @Test
    public void testDeleteClass() {
        Citation ref = new ArticleCitation();
        ref.setId("1");
        dao.delete(ref);
        dao.saveAll();
        
        assertEquals(9, dao.all().size());
    }

    @Test
    public void testFilter() {
        Predicate<Citation> filter = Predicates.notNull();
        List<Citation> list = dao.allByPredicate(filter);    
        assertEquals(list.size(), dao.all().size());
    }
    @Test
    public void testDestroy() {
        Citation c = new ArticleCitation();
        c.setId("1");
        Predicate<Citation> filter = Predicates.notNull();
        int count = dao.allByPredicate(filter).size();    
        dao.destroy(c);
        int newcount = dao.allByPredicate(filter).size();    
        assertEquals(newcount, count-1);
    }

    @Test
    public void testUpdate() {
        Citation c = dao.find("1");
        c.setYear("2014");
        dao.update("1", c);
        assertEquals("2014", dao.find("1").getYear());
    }
    
    // Tests for unexpected things
    @Test
    public void allCatchesException() {
        DatabaseJsonDao temp = new DatabaseJsonDao(null);
        temp.all();
        // if test passes, code works
    }
    
    @Test
    public void saveAllCatchesException() {
        DatabaseJsonDao temp = new DatabaseJsonDao(null);
        temp.saveAll();
        // if test passes, code works
    }
    
    @Test
    public void clearCatchesException() {
        DatabaseJsonDao temp = new DatabaseJsonDao(null);
        temp.clear();
        // if test passes, code works
    }
    
    
 }
