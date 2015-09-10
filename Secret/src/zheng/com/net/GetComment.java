/**
 * 
 */
package zheng.com.net;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.util.Log;
import zheng.com.Config;


/**
 * @author michael
 *
 */
public class GetComment {
	
	public GetComment(String phone_md5,String token,int page,int perpage,String msgId,final SuccessCallback successCallback,final FailCallback failCallback){
		
		new NetConnect(Config.SERVER_URL, HttpMethod.POST, new NetConnect.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				
				try {
					JSONObject jsonObject = new JSONObject(result);
					
					switch (jsonObject.getInt(Config.KEY_STATUS)) {
					case Config.RESULT_SUCCESS:
						if (successCallback != null) {
							
							int page = jsonObject.getInt(Config.KEY_PAGE);
							int perpage = jsonObject.getInt(Config.KEY_PERPAGE);
							
							JSONArray itemsJsonArray = jsonObject.getJSONArray(Config.KEY_ITEMS);
							
							JSONObject itemObject = null;
							
							List<Comment> list = new ArrayList<Comment>();
							Comment comment = null;
							
							for (int i = 0; i < itemsJsonArray.length(); i++) {
								itemObject = itemsJsonArray.getJSONObject(i);
								comment = new Comment(itemObject.getString(Config.KEY_CONTENT), itemObject.getString(Config.KEY_PHONE_MD5));
								list.add(comment);								
							}
							
							successCallback.onSuccess(page, perpage, list);
						}
						
						break;
					case Config.RESULT_INVALID:
						if (failCallback != null) {
							failCallback.onFail(Config.RESULT_INVALID);
						}
						break;

					default:
						if (failCallback != null) {
							failCallback.onFail(Config.RESULT_FAIL);
						}
						break;
					}
				} catch (JSONException e) {
					e.printStackTrace();
					Log.d("NetConnect", "--------------JSONException------------------");
					if (failCallback != null) {
						failCallback.onFail(Config.RESULT_FAIL);
					}
				}
				
			}
		}, new NetConnect.FailCallback() {
			
			@Override
			public void onFail() {
				
				if (failCallback != null) {
					failCallback.onFail(Config.RESULT_FAIL);
				}
				
			}
		}, 
		Config.KEY_ACTION,Config.ACTION_GET_COMMENT,
		Config.KEY_PHONE_MD5,phone_md5,
		Config.KEY_TOKEN,token,
		Config.KEY_PAGE,page+"",
		Config.KEY_PERPAGE,perpage+"",
		Config.KEY_MSGID,msgId);
		
		
		
	}

	
	
	public static interface SuccessCallback{
		public void onSuccess(int page,int perpage,List<Comment> list);
	}
	
	public static interface FailCallback{
		public void onFail(int error);
	}
}
