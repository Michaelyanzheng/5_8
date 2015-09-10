/**
 * 
 */
package contentProvider.com;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.test.AndroidTestCase;

/**
 * @author michael
 *
 */
public class MyTest extends AndroidTestCase {

	/**
	 * 
	 */
	public MyTest() {
	}
	
	
	public void insert(){
		ContentResolver contentResolver = getContext().getContentResolver();
		Uri url = Uri.parse("content://contentProvider.com.StudentProvider/student");
		ContentValues values = new ContentValues();
		values.put("name", "michael");
		values.put("address", "shenzhen");
		contentResolver.insert(url, values);
	}

}
