package zheng.com;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private MyTask myTask = new MyTask();
	
	private static int TASK_STOP = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn_start = (Button)findViewById(R.id.start);
		btn_start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				myTask.execute();
				
			}
		});
		
		Button btn_stop = (Button)findViewById(R.id.stop);
		btn_stop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				myTask.cancel(true);
				
			}
		});
	}
	
	private class MyTask extends AsyncTask<Void, Integer, Void>{

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
		 */
		
		@Override
		protected Void doInBackground(Void... params) {
			
			int count = 0;
			
			while (!isCancelled()) {
				
				publishProgress(count++);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			return null;
		}
		@Override
		protected void onCancelled() {
			Toast.makeText(MainActivity.this, "stop", Toast.LENGTH_SHORT).show();

			super.onCancelled();
		}
		
		@Override
		protected void onCancelled(Void result) {

			super.onCancelled(result);
		}
		@Override
		protected void onPostExecute(Void result) {
			Toast.makeText(MainActivity.this, "stop", Toast.LENGTH_SHORT).show();
		}
		@Override
		protected void onPreExecute() {
			
			Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			
			if(values[0] == TASK_STOP){
				Toast.makeText(MainActivity.this, "stop", Toast.LENGTH_SHORT).show();
				System.out.println("stop");
				
			}else{
				
				Toast.makeText(MainActivity.this, String.valueOf(values[0]), Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
