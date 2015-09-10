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
public class Publish {
	
	public Publish(String phone_md5,String token,String msg,final SuccessCallback successCallback,final FailCallback failCallback){
		
		new NetConnect(Config.SERVER_URL, HttpMethod.POST, new NetConnect.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					
					switch (jsonObject.getInt(Config.KEY_STATUS)) {
					case Config.RESULT_SUCCESS:
						if (successCallback != null) {
							successCallback.onSuccess();
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
		Config.KEY_ACTION,Config.ACTION_PUBLISH,
		Config.KEY_PHONE_MD5,phone_md5,
		Config.KEY_TOKEN,token,
		Config.KEY_MSG,msg);
	}

	
	public static interface SuccessCallback{
		void onSuccess();
	}
	
	public static interface FailCallback{
		void onFail(int error);
	}
}
