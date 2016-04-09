package com.ohtukki.citations.test.data;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.ohtukki.citations.data.AuthorFilter;
import com.ohtukki.citations.data.DatabaseJsonDao;
import com.ohtukki.citations.models.ArticleCitation;
import com.ohtukki.citations.models.Citation;
import com.ohtukki.citations.test.TestCitation;

public class DatabaseDaoJsonTest {
	@Test
	public void testAddSave() {
		DatabaseJsonDao dao = new DatabaseJsonDao("references.json");
    	for (int i = 0; i < 10; i++) {
    		Citation ref = new ArticleCitation();
    		ref.setAuthor("Author["+i+"]");
    		dao.save(ref);
		}
    	try {
			dao.saveJson();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testLoadList() {
		DatabaseJsonDao dao = new DatabaseJsonDao("references.json");
    	try {
			dao.loadJson();
			List<Citation> list = dao.all();
			//List<Citation> list = dao.allByPredicate(new AuthorFilter("Author[7]"));
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Citation reference = (Citation) iterator.next();
				System.out.println("--:"+reference.getAuthor());
			}
			assertEquals(10,list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
