package zheng.com;

import zheng.com.http.HttpServer;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btnJavaGet).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("HttpServer", "---------------------------");
				HttpServer.get("http://fanyi.youdao.com/openapi.do?keyfrom=zhengwen&key=1318083984&type=data&doctype=xml&version=1.1&q=sweet");
//				HttpServer.get("https://www.baidu.com");
			}
		});
		
		findViewById(R.id.btnJavaPost).setOnClickListener(new View.OnClickListener() {
//http://fanyi.youdao.com/openapi.do?keyfrom=zhengwen&key=1318083984&type=data&doctype=<doctype>&version=1.1&q=要翻译的
	
			@Override
			public void onClick(View v) {
				HttpServer.post("http://fanyi.youdao.com/openapi.do", 
						"keyfrom=zhengwen&key=1318083984&type=data&doctype=xml&version=1.1&q=love");
			}
		});
		
		findViewById(R.id.btnHttpGet).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				HttpServer.HttpGet("http://www.baidu.com");
				HttpServer.HttpGet("http://fanyi.youdao.com/openapi.do?keyfrom=zhengwen&key=1318083984&type=data&doctype=xml&version=1.1&q=sweet");
			}
		});
		
		findViewById(R.id.btnHttpPost).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("HttpServer", "-------------------");
				HttpServer.HttpPost("http://fanyi.youdao.com/openapi.do", "keyfrom=zhengwen&key=1318083984&type=data&doctype=xml&version=1.1&q=sweet");
			}
		});
		
		
	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.btnJavaGet:
//			Log.d("HttpServer", "---------------------------");
//			HttpServer.get("http://www.google.com");
//			
//			break;
//
//		default:
//			break;
//		}
//	}

}
