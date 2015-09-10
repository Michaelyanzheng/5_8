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
public class GetCode {
	
	public GetCode(String phone,final SuccessCallback successCallback,final FailCallback failCallback){
		
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
		}, Config.KEY_ACTION,Config.ACTION_SEND_PASS,
		Config.KEY_PHONE,phone);
	}
	
	
	public static interface SuccessCallback{
		public void onSuccess();
	}
	
	public static interface FailCallback{
		public void onFail();
	}

}
