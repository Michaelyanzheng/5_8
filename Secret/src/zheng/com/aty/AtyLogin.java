/**
 * 
 */
package zheng.com.aty;

import zheng.com.Config;
import zheng.com.R;
import zheng.com.net.GetCode;
import zheng.com.net.Login;
import zheng.com.tools.Md5;
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
public class AtyLogin extends Activity{
	
	
	private EditText etPhone = null;
	
	private EditText etCode = null;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_login);
		
		etPhone = (EditText) findViewById(R.id.etPhone);
		etCode = (EditText) findViewById(R.id.etCode);
		
		findViewById(R.id.btn_get_code).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (TextUtils.isEmpty(etPhone.getText().toString())) {
					Toast.makeText(AtyLogin.this, R.string.phone_no_empty, Toast.LENGTH_LONG).show();
				}else{
					
					new GetCode(etPhone.getText().toString(), new GetCode.SuccessCallback() {
						
						@Override
						public void onSuccess() {
							Config.setCachedPhone(AtyLogin.this, etPhone.getText().toString());
							Toast.makeText(AtyLogin.this, R.string.suc_get_code, Toast.LENGTH_LONG).show();
							
						}
					}, new GetCode.FailCallback() {
						
						@Override
						public void onFail() {
							
							Toast.makeText(AtyLogin.this, R.string.fail_get_code, Toast.LENGTH_LONG).show();
						}
					});
				}
			}
		});
		
		findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (TextUtils.isEmpty(etCode.getText().toString())) {
					Toast.makeText(AtyLogin.this, R.string.code_no_empty, Toast.LENGTH_LONG).show();
				}else{
					new Login(Md5.getMD5Str(Config.getCachedPhone(AtyLogin.this)), etCode.getText().toString(), new Login.SuccessCallback() {
						
						@Override
						public void onSuccess(String token) {
							Config.setCachedToken(AtyLogin.this, token);
							Toast.makeText(AtyLogin.this, R.string.fail_login, Toast.LENGTH_LONG).show();
							startActivity(new Intent(AtyLogin.this,Atytimeline.class));
						}
					}, new Login.FailCallback() {
						
						@Override
						public void onFail() {
							Toast.makeText(AtyLogin.this, R.string.fail_login, Toast.LENGTH_LONG).show();
						}
					});
				}
				
			}
		});
		
		
		
		
	}

}














