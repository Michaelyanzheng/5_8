/**
 * 
 */
package zheng.com.aty;

import java.util.List;

import zheng.com.Config;
import zheng.com.R;
import zheng.com.Id.MyContacts;
import zheng.com.net.GetMessage;
import zheng.com.net.Message;
import zheng.com.net.UploadContact;
import zheng.com.tools.FailTools;
import zheng.com.tools.Md5;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author michael
 *
 */
public class Atytimeline extends ListActivity {
	
	private String phone_md5;
	private String token ;
	
	private AtytimelineAdapter adapter = null; 
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_time_line);
		
		adapter = new AtytimelineAdapter(Atytimeline.this);
		setListAdapter(adapter);
		
		phone_md5 = Md5.getMD5Str(Config.getCachedPhone(Atytimeline.this));
		token = Config.getCachedToken(Atytimeline.this);
		
		new UploadContact(phone_md5,	token, MyContacts.getContactsJSONArrString(Atytimeline.this), 
				new UploadContact.SuccessCallback() {
					
					@Override
					public void onSuccess() {
						Toast.makeText(Atytimeline.this, R.string.suc_upload_contacts, Toast.LENGTH_LONG).show();
					}
				},new UploadContact.FailCallback() {
					
					@Override
					public void onFail(int errorCode) {
						FailTools.onFail(Atytimeline.this, errorCode, getResources().getString(R.string.fail_upload_contacts));
					}
				});
		
		
		loadMessage();
		
	}
	
	private void loadMessage(){
		new GetMessage(phone_md5,token,1,20,new GetMessage.SuccessCallback() {
			
			@Override
			public void onSuccess(int page, int perpage, List<Message> list) {
				
				adapter.clear();
				adapter.addAll(list);
				
			}
		},new GetMessage.FailCallback() {
			
			@Override
			public void onFail(int errorCode) {
				FailTools.onFail(Atytimeline.this, errorCode, getResources().getString(R.string.fail_get_messages));
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Intent intent = new Intent(Atytimeline.this,AtyComment.class);
		
		intent.putExtra(Config.KEY_MSG,adapter.getItem(position).getMsg());
		intent.putExtra(Config.KEY_PHONE_MD5, phone_md5);
		intent.putExtra(Config.KEY_TOKEN, token);
		intent.putExtra(Config.KEY_MSGID, adapter.getItem(position).getMsgId());
		
		startActivity(intent);
		
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.aty_timeline_menu, menu);
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.menuShowAtyPublish:
			
			Intent intent = new Intent(Atytimeline.this,AtyPublish.class);
			intent.putExtra(Config.KEY_PHONE_MD5, phone_md5);
			intent.putExtra(Config.KEY_TOKEN, token);
			startActivityForResult(intent, 0);
			
			break;

		default:
			break;
		}
		
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		switch (resultCode) {
		case Config.ACTION_RESULT_PUBLISH:
			loadMessage();
			break;

		default:
			break;
		}
		
	}
	
	

}
