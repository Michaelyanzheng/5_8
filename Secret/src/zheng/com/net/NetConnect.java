/**
 * 
 */
package zheng.com.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import zheng.com.Config;
import android.os.AsyncTask;
import android.util.Log;

/**
 * @author michael
 *
 */
public class NetConnect {
	
	private String TAG = "NetConnect";
	
	public NetConnect(final String url,final HttpMethod method,final SuccessCallback successCallback,final FailCallback failCallback,final String ...kvs){
		
		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				
				
				StringBuffer paramsBuffer = new StringBuffer();
				for (int i = 0; i < kvs.length; i+=2) {
					paramsBuffer.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
				}
				Log.d(TAG, paramsBuffer.toString());

				
				try {
					URLConnection connection = null;
					
					switch (method) {
					case POST:
						 connection = new URL(url).openConnection();
						 connection.setDoOutput(true);
						 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), Config.SETCHAT));
						 bufferedWriter.write(paramsBuffer.toString());
						 bufferedWriter.flush();
						 bufferedWriter.close();
						break;

					default:
						connection = new URL(url+"?"+paramsBuffer.toString()).openConnection();
						break;
					}
					
					
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Config.SETCHAT));
					
					StringBuffer result = new StringBuffer();
					String line = null;
					
					while ((line = bufferedReader.readLine()) != null) {
						result.append(line);
						line = "";
					}
					
					bufferedReader.close();
					
					Log.d(TAG, connection.toString());
					Log.d(TAG, result.toString());
					
					return result.toString();
					
					
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
					return null;
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
			
			/* (non-Javadoc)
			 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
			 */
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				if (result != null) {
					if (successCallback != null) {
						
						Log.d(TAG, "ttttttttttttttttttttt");
						successCallback.onSuccess(result);
					}
				}else{
					if (failCallback != null) {
						
						Log.d(TAG, "aaaaaaaaaaaaa");
						failCallback.onFail();
					}
				}
			}
		}.execute();
		
		
	}
	
	public static interface SuccessCallback{
		public void onSuccess(String result);
	}
	
	public static interface FailCallback{
		public void onFail();
	}

}
