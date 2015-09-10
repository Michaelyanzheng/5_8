/**
 * 
 */
package zheng.com.db;

import java.util.List;
import java.util.Map;

import zheng.com.db.dao.PersonDao1;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * @author michael
 *
 */
public class Test1 extends AndroidTestCase {
	
	private String TAG = "AndroidTestCase";
	
	public void addPerson(){
		PersonDao1 personDao1 = new PersonDao1(getContext());
		personDao1.addPerson(new Object[]{"michael",18});
	}
	
	public void deletePerson(){
		PersonDao1 personDao1 = new PersonDao1(getContext());
		personDao1.deletePerson(new Object[]{4});
	}
	
	public void updatePerson(){
		PersonDao1 personDao1 = new PersonDao1(getContext());
		personDao1.updatePerson(new Object[]{23,"michael"});
	}
	
	public void selectOnePerson(){
		PersonDao1 personDao1 = new PersonDao1(getContext());
		Map<String, String> list = personDao1.selectOnePerson(new String[]{2+""});
//		Log.d(TAG, list.toString());
	}

}
