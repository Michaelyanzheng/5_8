/**
 * 
 */
package zheng.com.Id;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import zheng.com.tools.Md5;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;

/**
 * @author michael
 *
 */
public class MyContacts {
	
	public static String getContactsJSONArrString(Context context){
		
		Cursor cursor = context.getContentResolver().query(Phone.CONTENT_URI, null, null, null, null);
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		String phone_num;
		
		String tmp ;
		
		while (cursor.moveToNext()) {
			phone_num = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
			
			if (phone_num.charAt(0)=='+'&&phone_num.charAt(1)=='8'&&phone_num.charAt(2)=='6') {
				
				phone_num = phone_num.substring(3);
			}
			
			phone_num = Md5.getMD5Str(phone_num);
			tmp = "{\"phone_md5\":" + phone_num+  "}";
			try {
				jsonObject = new JSONObject(tmp);
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		Log.d("NetConnect", jsonArray.toString());
		return jsonArray.toString();
	}

}
