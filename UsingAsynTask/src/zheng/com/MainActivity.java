package zheng.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView Text;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Text = (TextView) findViewById(R.id.textView1);
		
		findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ReadURL("http://www.baidu.com");
			}
		});
	}
	
	
	
	public void ReadURL(String url){
		
		
		new AsyncTask<String, Float, String>() {

			@Override
			protected String doInBackground(String... params) {
				
				try {
					URL url = new URL(params[0]);
					URLConnection connection = url.openConnection();
					InputStream is = connection.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					
					String line;
					
					StringBuilder builder = new StringBuilder();
					
					long total = connection.getContentLength();
					
					while ((line = br.readLine()) != null) {
						builder.append(line);
						publishProgress((float)builder.toString().length()/total);
					}
					
					br.close();
					isr.close();
					is.close();
					
					return builder.toString();
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onCancelled() {
				super.onCancelled();
			}

			@Override
			protected void onCancelled(String result) {
				super.onCancelled(result);
			}

			@Override
			protected void onPostExecute(String result) {
				
				Text.setText(result);
				super.onPostExecute(result);
			}

			@Override
			protected void onPreExecute() {
				
				Toast.makeText(MainActivity.this, "start read", Toast.LENGTH_SHORT).show();
				super.onPreExecute();
			}

			@Override
			protected void onProgressUpdate(Float... values) {
				
				System.err.println(values[0]);
				super.onProgressUpdate(values);
			}

			@Override
			protected Object clone() throws CloneNotSupportedException {
				return super.clone();
			}

			@Override
			public boolean equals(Object o) {
				return super.equals(o);
			}

			@Override
			protected void finalize() throws Throwable {
				super.finalize();
			}

			@Override
			public int hashCode() {
				return super.hashCode();
			}

			@Override
			public String toString() {
				return super.toString();
			}
			
			
		}.execute(url);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
