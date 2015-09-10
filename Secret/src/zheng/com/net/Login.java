/**
 * 
 */
package zheng.com.net;

import org.json.JSONException;
import org.json.JSONObject;

import zheng.com.Config;

/**
 * @author michael
 *
 */
public class Login {
	
	public Login(String phone_md5,String code,final SuccessCallback successCallback,final FailCallback failCallback){
		
		new NetConnect(Config.SERVER_URL, HttpMethod.POST, new NetConnect.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				
				try {
					JSONObject jsonObject = new JSONObject(result);
					
					switch (jsonObject.getInt(Config.KEY_STATUS)) {
					case Config.RESULT_SUCCESS:
						if (successCallback != null) {
							successCallback.onSuccess(jsonObject.getString(Config.KEY_TOKEN));
						}
						break;

					default:
						if (failCallback != null) {
							failCallback.onFail();
						}
						break;
					}
				} catch (JSONException e) {
					e.printStackTrace();
					if (failCallback != null) {
						failCallback.onFail();
					}
				}
			}
		}, new NetConnect.FailCallback() {
			
			@Override
			public void onFail() {
				if (failCallback != null) {
					failCallback.onFail();
				}
			}
		}, 
		Config.KEY_ACTION,Config.ACTION_LOGIN,
		Config.KEY_PHONE_MD5,phone_md5,
		Config.KEY_CODE,code);
	}
	
	public static interface SuccessCallback{
		public void onSuccess(String token);
	}
	
	public static interface FailCallback{
		public void onFail();
	}

}
