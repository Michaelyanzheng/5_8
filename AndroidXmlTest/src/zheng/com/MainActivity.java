package zheng.com;

import zheng.com.util.Config;
import zheng.com.util.HttpGetCall;
import zheng.com.util.HttpGetJson;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private TextView tvResult;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvResult = (TextView) findViewById(R.id.tvResult);
		
		findViewById(R.id.btnGetXml).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("HttpGetCall", "-------------------------");

				new HttpGetCall(Config.SERVER_URL+Config.XML_URL,new zheng.com.util.HttpGetCall.SuccessCallback() {
					
					@Override
					public void onSuccess(String result) {
						tvResult.setText(result);
					}
				},new zheng.com.util.HttpGetCall.FailCallback() {
					
					@Override
					public void onFail() {
					}
				});
				
			}
		});
		
		findViewById(R.id.btnGetJson).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new HttpGetJson(Config.SERVER_URL+Config.JSON_URL, new zheng.com.util.HttpGetJson.SuccessCallback() {
					
					@Override
					public void onSuccess(String result) {
						tvResult.setText(result);
					}
				}, new zheng.com.util.HttpGetJson.FailCallback() {
					
					@Override
					public void onFail() {
						Toast.makeText(MainActivity.this, "onFail", Toast.LENGTH_LONG).show();
					}
				});
			}
		});
	}

}
