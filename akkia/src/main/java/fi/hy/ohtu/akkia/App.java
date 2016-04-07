package fi.hy.ohtu.akkia;

import fi.hy.ohtu.akkia.dao.ReferenceDAO;
import fi.hy.ohtu.akkia.dao.ReferenceDAOJsonImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
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
}
