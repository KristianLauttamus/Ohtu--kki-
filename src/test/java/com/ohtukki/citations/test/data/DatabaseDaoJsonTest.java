package com.ohtukki.citations.test.data;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.ohtukki.citations.data.AuthorFilter;
import com.ohtukki.citations.data.DatabaseJsonDao;
import com.ohtukki.citations.data.PublicationFilter;
import com.ohtukki.citations.data.YearFilter;
import com.ohtukki.citations.models.ArticleCitation;
import com.ohtukki.citations.models.Citation;

public class DatabaseDaoJsonTest {
    private DatabaseJsonDao init() {
        DatabaseJsonDao dao = new DatabaseJsonDao();
        for (int i = 0; i < 10; i++) {
            Citation ref = new ArticleCitation();
            ref.setId("" + i);
            ref.setAuthor("Author["+i+"]");
            dao.save(ref);
        }
        return dao;
    }
    @Test(expected=UnsupportedOperationException.class) 
    public void testFind() {
        DatabaseJsonDao dao = init();
        dao.find("1");
    }
    @Test(expected=UnsupportedOperationException.class) 
    public void testDelete() {
        DatabaseJsonDao dao = init();
        dao.delete("1");
    }
    @Test(expected=UnsupportedOperationException.class) 
    public void testDeleteClass() {
        DatabaseJsonDao dao = init();
        Citation ref = new ArticleCitation();
        ref.setId("1");
        dao.delete(ref);
    }

    @Test
    public void testAddSave() {
        DatabaseJsonDao dao = init();
    }
    @Test
    public void testLoadList() {
        DatabaseJsonDao dao = init();
        try {
            List<Citation> list = dao.all();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                Citation reference = (Citation) iterator.next();
                System.out.println("--:"+reference.getAuthor());
            }
            assertEquals(10,list.size());
            
            Citation ref = new ArticleCitation();
            ref.setId("1");
            dao.destroy(ref);
            assertEquals(9,list.size());
            
            Predicate<Citation> filter = Predicates.notNull();
            filter =  Predicates.and(filter, new AuthorFilter("Author[7]"));
            filter =  Predicates.and(filter, new PublicationFilter(""));
            filter =  Predicates.and(filter, new YearFilter(""));
        
            list = dao.allByPredicate(filter);
            assertEquals(1,list.size());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
