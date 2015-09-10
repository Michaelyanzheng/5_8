/**
 * 
 */
package zheng.com;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author michael
 *
 */
public class Config {
	
	public static final String SERVER_URL = "http://192.168.2.100:8080/Test/api.jsp";
	
	public static final String KEY_ACTION = "action";
	public static final String KEY_TOKEN = "token";
	public static final String KEY_PHONE = "phone";
	public static final String KEY_STATUS = "status";
	public static final String KEY_PHONE_MD5 = "phone_md5";
	public static final String KEY_CODE = "code";
	public static final String KEY_CONTACTS = "constacts";
	public static final String KEY_PAGE = "page";
	public static final String KEY_PERPAGE = "perpage";
	public static final String KEY_MSG = "msg";
	public static final String KEY_MSGID = "msgId";
	public static final String KEY_TIMELINE = "timeline";
	public static final String KEY_ITEMS = "items";
	public static final String KEY_CONTENT = "content";
	
	public static final String APP_ID = "zheng.com";
	public static final String SETCHAT = "UTF-8";


	public static final int RESULT_SUCCESS = 1;
	public static final int RESULT_FAIL = 0;
	public static final int RESULT_INVALID = 2;
	
	public static final String ACTION_SEND_PASS = "send_pass";
	public static final String ACTION_LOGIN = "login";
	public static final String ACTION_UPLOAD_CONTACTS = "upload_contacts";
	public static final String ACTION_TIMELINE = "timeline";
	public static final String ACTION_GET_COMMENT = "get_comment";
	public static final String ACTION_PUBLISH = "publish";

	public static final int ACTION_RESULT_PUBLISH = 10000;



	





	public static void setCachedToken(Context context,String token){
		
		SharedPreferences.Editor editor = context.getSharedPreferences(Config.APP_ID,Context.MODE_PRIVATE).edit();
		editor.putString(Config.KEY_TOKEN, token);
		editor.commit();
	}
	
	public static String getCachedToken(Context context){
		
		return context.getSharedPreferences(Config.APP_ID, Context.MODE_APPEND).getString(Config.KEY_TOKEN, null);
	}
	
	public static void setCachedPhone(Context context,String phone){
		
		SharedPreferences.Editor editor = context.getSharedPreferences(Config.APP_ID,Context.MODE_PRIVATE).edit();
		editor.putString(Config.KEY_PHONE, phone);
		editor.commit();
	}
	
	public static String getCachedPhone(Context context){
		
		return context.getSharedPreferences(Config.APP_ID, Context.MODE_APPEND).getString(Config.KEY_PHONE, null);
	}
	
	

}
