package com.ohtukki.citations.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.ohtukki.citations.data.DatabaseJsonDao;
import com.ohtukki.citations.models.Citation;

public class DatabaseDaoJsonTest {
	@Test
	public void testAddSave() {
		DatabaseJsonDao dao = new DatabaseJsonDao("references.json");
    	for (int i = 0; i < 10; i++) {
    		Citation ref = new TestCitation();
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
