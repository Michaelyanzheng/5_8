/**
 * 
 */
package zheng.com.net;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import zheng.com.Config;

/**
 * @author michael
 *
 */
public class GetMessage {
	
	public GetMessage(String phone_md5,String token,int page,int perpage,final SuccessCallback successCallback,final FailCallback failCallback){
		
		
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
							
							JSONArray timelineJsonArray  = jsonObject.getJSONArray(Config.KEY_TIMELINE);
							JSONObject messageObject = null;
							
							List<Message> list = new ArrayList<Message>();
							
							for (int i = 0; i < timelineJsonArray.length(); i++) {
								
								messageObject = timelineJsonArray.getJSONObject(i);
								String msg = messageObject.getString(Config.KEY_MSG);
								String phone_md5 = messageObject.getString(Config.KEY_PHONE_MD5);
								String msgId = messageObject.getString(Config.KEY_MSGID);
								
								Message message = new Message(msg, phone_md5, msgId);
								list.add(message);
								
							}
							
							successCallback.onSuccess(page,perpage,list);
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
		Config.KEY_ACTION,Config.ACTION_TIMELINE,
		Config.KEY_PHONE_MD5,phone_md5,
		Config.KEY_TOKEN,token,
		Config.KEY_PAGE,1+"",
		Config.KEY_PERPAGE,20+"");
	}
	
	
	public static interface SuccessCallback{
		public void onSuccess(int page,int perpage,List<Message> list);
	}
	
	public static interface FailCallback{
		public void onFail(int errorCode);
	}

}
