package zheng.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView = (TextView) findViewById(R.id.textView1);
		textView.setText(GetString.getStr());
		
		
		Toast.makeText(this, GetInt.getInt()+"", Toast.LENGTH_LONG).show();
	}
	
	static{
		System.loadLibrary("HelloNDK");
		
	}
	
}
