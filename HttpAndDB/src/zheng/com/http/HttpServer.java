package zheng.com.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpServer {
	
	private static final String TAG = "HttpServer";

	public static void get(final String urlString){
		new Thread(){
			public void run() {
				try {

					URL url = new URL(urlString);
					URLConnection connection =  url.openConnection();
					
					InputStream inputStream = connection.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					
					String line = null;
					StringBuffer result = new StringBuffer();
					if ((line = bufferedReader.readLine()) != null) {
						result.append(line);
					}
					
					Log.d(TAG, result.toString());
					
					bufferedReader.close();
					inputStreamReader.close();
					inputStream.close();
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
	//http://fanyi.youdao.com/openapi.do?keyfrom=zhengwen&key=1318083984&type=data&doctype=<doctype>&version=1.1&q=要翻译的
	
	public static void post(final String urlString,final String parameter){
		
		new Thread(){
			public void run() {
				try {
					URL url = new URL(urlString);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("POST");
					connection.setRequestProperty("encoding", "utf-8");
					connection.setDoOutput(true);
					connection.setDoInput(true);
					
					OutputStream outputStream = connection.getOutputStream();
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
					BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
					bufferedWriter.write(parameter);
					bufferedWriter.flush();
					
					InputStream inputStream = connection.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					
					String line = "";
					StringBuffer result = new StringBuffer();
					while ((line = bufferedReader.readLine()) != null) {
						result.append(line);
					}
					Log.d(TAG,result.toString());
					bufferedReader.close();
					inputStreamReader.close();
					inputStream.close();
					
					bufferedWriter.close();
					outputStreamWriter.close();
					outputStream.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			};
		}.start();
	}
	
	public static void HttpGet(final String urlString){
		new Thread(){
			public void run() {
				
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(urlString);
				try {
					HttpResponse response = client.execute(get);
					if (response.getStatusLine().getStatusCode() == 200) {
						String result = EntityUtils.toString(response.getEntity());
						Log.d(TAG, result);						
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
	
	public static void HttpPost(final String postString, final String  param){
		new Thread(){
			
			public void run() {
				
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(postString);
				List<BasicNameValuePair> parameter = new ArrayList<BasicNameValuePair>();
				String [] nameValue = param.split("&");
				for (int i = 0; i < nameValue.length; i++) {
					String before = nameValue[i].substring(0, nameValue[i].indexOf("="));
					String after = nameValue[i].substring(nameValue[i].indexOf("=")+1, nameValue[i].length());
					Log.d(TAG, before + "-----" + after);
					parameter.add(
							new BasicNameValuePair(
									nameValue[i].substring(0, nameValue[i].indexOf("=")),
									nameValue[i].substring(nameValue[i].indexOf("=")+1, nameValue[i].length())));
				}
				try {
					post.setEntity(new UrlEncodedFormEntity(parameter));
					HttpResponse response = client.execute(post);
					if (response.getStatusLine().getStatusCode() == 200) {
						String result = EntityUtils.toString(response.getEntity());
						Log.d(TAG, result);
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
}




























