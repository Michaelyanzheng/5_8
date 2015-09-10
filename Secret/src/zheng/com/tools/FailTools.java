/**
 * 
 */
package zheng.com.tools;

import zheng.com.Config;
import zheng.com.aty.AtyLogin;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author michael
 *
 */
public class FailTools {
	
	public static void onFail(Activity activity,int error,String errorString){
		
		if (error == Config.RESULT_INVALID) {
			Intent intent = new Intent(activity,AtyLogin.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			activity.startActivity(intent);
			activity.finish();
		}else{
			Toast.makeText(activity, errorString, Toast.LENGTH_LONG).show();
		}
		
		
	}

}
