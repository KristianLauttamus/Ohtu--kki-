package fi.hy.ohtu.akkia;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import fi.hy.ohtu.akkia.dao.ReferenceDAO;
import fi.hy.ohtu.akkia.dao.ReferenceDAOJsonImpl;

public class ReferenceDAOTest {
	@Test
	public void testAddSave() {
    	ReferenceDAO dao = new ReferenceDAOJsonImpl("references.json");
    	for (int i = 0; i < 10; i++) {
    		Reference ref = new Reference();
    		ref.setReferenceKey("ReferenceKey["+i+"]");
    		ref.setTitle("Title["+i+"]");
    		ref.setAuthor("Author["+i+"]");
    		ref.setPublisher("Publisher["+i+"]");
    		ref.setYear("200"+i+"]");
    		dao.addReference(ref);
		}
    	try {
			dao.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testLoadList() {
    	ReferenceDAO dao = new ReferenceDAOJsonImpl("references.json");
    	try {
			dao.load();
			List<Reference> list = dao.getAllReferences();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Reference reference = (Reference) iterator.next();
				System.out.println("--:"+reference.getReferenceKey());
			}
			assertEquals(10,list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
