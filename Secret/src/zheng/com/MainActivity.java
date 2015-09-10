package zheng.com;

import zheng.com.aty.AtyLogin;
import zheng.com.aty.Atytimeline;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String token = Config.getCachedToken(this);
		String phone = Config.getCachedPhone(this);
		
		if (token != null && phone != null) {
			Intent intent = new Intent(MainActivity.this,Atytimeline.class);
			intent.putExtra(Config.KEY_TOKEN, token);
			intent.putExtra(Config.KEY_PHONE, phone);
			startActivity(intent);
			finish();
		}else{
			startActivity(new Intent(MainActivity.this,AtyLogin.class));
		}
		
		
	}

	
}
