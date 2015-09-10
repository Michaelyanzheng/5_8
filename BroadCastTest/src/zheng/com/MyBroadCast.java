package zheng.com;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadCast extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent intent) {
		System.out.println(intent.getStringExtra("zheng"));
		System.out.println("onReceive");
	}

}
