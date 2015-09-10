package zheng.com;

import java.security.PublicKey;

import org.apache.http.client.HttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText editText;
	private TextView text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		editText = (EditText) findViewById(R.id.editText1);
		text = (TextView) findViewById(R.id.textView1);
		
		findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				readNet("");
			}
		});
		
	}
	
	
	public void readNet(String url){
		
		
	}

	
}
