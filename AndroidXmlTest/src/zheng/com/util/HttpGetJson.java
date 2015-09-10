package zheng.com.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpGetJson {

	public HttpGetJson(String url,final SuccessCallback successCallback,final FailCallback failCallback) {
		
		new HttpResponse(url, new zheng.com.util.HttpResponse.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				try {
					JSONArray jsonArray = new JSONArray(result);
					StringBuffer resultBuffer = new StringBuffer();
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						String id = jsonObject.getString(Config.XML_ID);
						String name = jsonObject.getString(Config.XML_NAME);
						String version = jsonObject.getString(Config.XML_VERSION);
						resultBuffer.append(id).append("---").append(name).append("---").append(version).append("\n");
					}
					if (successCallback != null) {
						successCallback.onSuccess(resultBuffer.toString());
					}
				} catch (JSONException e) {
					e.printStackTrace();
					if (failCallback != null) {
						failCallback.onFail();
					}
				}
			}
		}, new zheng.com.util.HttpResponse.FailCallback() {
			
			@Override
			public void onFail() {
				if (failCallback != null) {
					failCallback.onFail();
				}
			}
		});
	}
	
	public interface SuccessCallback{
		void onSuccess(String result);
	}
	
	public interface FailCallback{
		void onFail();
	}

}
