/**
 * 
 */
package zheng.com.aty;

import zheng.com.Config;
import zheng.com.R;
import zheng.com.net.Publish;
import zheng.com.tools.FailTools;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author michael
 *
 */
public class AtyPublish extends Activity {
	
	private EditText etPublish;
	
	private String phone_md5;
	private String token;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_publish);
		
		Intent intent = getIntent();
		phone_md5 = intent.getStringExtra(Config.KEY_PHONE_MD5);
		token = intent.getStringExtra(Config.KEY_TOKEN);
		
		etPublish = (EditText) findViewById(R.id.etPulish);
		
		findViewById(R.id.btnPublish).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (TextUtils.isEmpty(etPublish.getText().toString())) {
					Toast.makeText(AtyPublish.this, R.string.publish_no_empty, Toast.LENGTH_LONG).show();
				}else{
					new Publish(phone_md5, token, etPublish.getText().toString(), new Publish.SuccessCallback() {
						
						@Override
						public void onSuccess() {
							Toast.makeText(AtyPublish.this, R.string.suc_publish_message, Toast.LENGTH_LONG).show();
						}
					}, new Publish.FailCallback() {
						
						@Override
						public void onFail(int error) {
							FailTools.onFail(AtyPublish.this, error, getResources().getString(R.string.fail_publish_message));
						}
					});
				}
			}
		});
		
		
		
		
		
	}

}
