/**
 * 
 */
package zheng.com.db.server;

import java.util.List;
import java.util.Map;

/**
 * @author michael
 *
 */
public interface Server1 {
	
	boolean addPerson(Object[] bindArgs);
	
	boolean deletePerson(Object[] bindArgs);
	
	boolean updatePerson(Object[] bindArgs);
	
	Map<String, String> selectOnePerson(String[] bindArgs);
	
	boolean selectAllPerson(Object[] bindArgs);

}
