/**
 * 
 */
package zheng.com.aty;

import java.util.List;

import org.apache.http.TokenIterator;

import zheng.com.Config;
import zheng.com.R;
import zheng.com.net.AtyCommentAdapter;
import zheng.com.net.Comment;
import zheng.com.net.GetComment;
import zheng.com.tools.FailTools;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author michael
 *
 */
public class AtyComment extends ListActivity {
	
	private TextView tvMeg;
	
	private String msg;
	private String msgId;
	private String phone_md5;
	private String token;
	
	private AtyCommentAdapter adapter = null;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_comment);
		adapter = new AtyCommentAdapter(AtyComment.this);
		setListAdapter(adapter);
		
		Intent intent = getIntent();
		
		tvMeg = (TextView) findViewById(R.id.tvMeg);
		
		msg = intent.getStringExtra(Config.KEY_MSG);
		msgId = intent.getStringExtra(Config.KEY_MSGID);
		phone_md5 = intent.getStringExtra(Config.KEY_PHONE_MD5);
		token = intent.getStringExtra(Config.KEY_TOKEN);
		
		tvMeg.setText(msg);
		
		
		new GetComment(phone_md5, token, 1, 20, msgId, new GetComment.SuccessCallback() {
			
			@Override
			public void onSuccess(int page, int perpage, List<Comment> list) {
				
				adapter.clear();
				adapter.addAll(list);
				Toast.makeText(AtyComment.this, R.string.suc_get_comment, Toast.LENGTH_LONG).show();
			}
		}, new GetComment.FailCallback() {
			
			@Override
			public void onFail(int error) {
				FailTools.onFail(AtyComment.this, error, getResources().getString(R.string.fail_get_comment));
			}
		});
		
	}

}
