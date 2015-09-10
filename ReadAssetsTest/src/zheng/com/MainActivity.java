package zheng.com;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			
			InputStream inputStream = getAssets().open("zheng.txt");
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			String str = new String(buffer,"UTF-8");
			System.out.println(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
}
