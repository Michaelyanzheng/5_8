package zheng.com.util;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

public class HttpResponse {

	public HttpResponse(String url,final SuccessCallback successCallback,final FailCallback failCallback) {
		
		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				
				String urlString = params[0];
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(urlString);
				try {
					org.apache.http.HttpResponse response = client.execute(get);
					if (response.getStatusLine().getStatusCode() == 200) {
						String result = EntityUtils.toString(response.getEntity());
					//	Log.d("HttpGetCall", result);
						return result;
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return null;
			}
			@Override
			protected void onPostExecute(String result) {
				
				if (result != null) {
					if (successCallback != null) {
						successCallback.onSuccess(result);
					}
				}else{
					Log.d("HttpGetCall", "--------------result-----------"+result);
					if (failCallback != null) {
						failCallback.onFail();
					}
				}
			}

		}.execute(url);
	}
	
	interface SuccessCallback{
		void onSuccess(String result);
	}
	interface FailCallback{
		void onFail();
	}
	
}


