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
public class UploadContact {
	
	
	public UploadContact(String phone_md5,String token,String contact,final SuccessCallback successCallback,final FailCallback failCallback){
		
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
		Config.KEY_ACTION,Config.ACTION_UPLOAD_CONTACTS,
		Config.KEY_PHONE_MD5,phone_md5,
		Config.KEY_TOKEN,token,
		Config.KEY_CONTACTS,contact);
		
		
	}
	
	public static interface SuccessCallback{
		public void onSuccess();
	}

	public static interface FailCallback{
		public void onFail(int errorCode);
	}
}
