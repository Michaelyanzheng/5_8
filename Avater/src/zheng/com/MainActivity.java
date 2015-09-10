package zheng.com;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends Activity {
	
	
	private static int CAMERA_REQUEST_CODE = 1;
	private static int GALLERY_REQUEST_CODE = 2;
	private static int CROP_REQUEST_CODE = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		Button btn_camera = (Button) findViewById(R.id.btn_camera);
		btn_camera.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, CAMERA_REQUEST_CODE);
			}
		});
		
		Button btn_gallery = (Button) findViewById(R.id.btn_gallery);
		btn_gallery.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				startActivityForResult(intent, GALLERY_REQUEST_CODE);
				
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(requestCode == CAMERA_REQUEST_CODE){
			
			if(data == null){
				
				return ;
			}else{
				
				Bundle extras = data.getExtras();
				if(extras != null){
					
					Bitmap bitmap = extras.getParcelable("data");
					Uri uri = saveUri(bitmap);
					startImageZoom(uri);
				}
			}
		}else if(requestCode == GALLERY_REQUEST_CODE){
			
			if(data == null){
				
				return;
			}
			
			Uri uri = data.getData();
			Uri fileUri = converUri(uri);
			startImageZoom(fileUri);
		}else if (requestCode == CROP_REQUEST_CODE) {
			if (data == null) {
				return ;
			}
			Bundle extras = data.getExtras();
			Bitmap bitmap = extras.getParcelable("data");
			ImageView imageView = (ImageView)findViewById(R.id.imageView);
			imageView.setImageBitmap(bitmap);
			sendImage(bitmap);
		}
	}
	
	private void sendImage(Bitmap bitmap){
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 60, stream);
		byte[] bytes = stream.toByteArray();
		String img = new String(Base64.encodeToString(bytes, Base64.DEFAULT));
		
		
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.add("img", img);
		client.post("http://192.168.2.100:8081/ImgUpload.php", params,new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				Toast.makeText(MainActivity.this, "Upload Success ! ", Toast.LENGTH_LONG).show();
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				Toast.makeText(MainActivity.this, "Upload Failure ! ", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	private void startImageZoom(Uri uri){
		
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, CROP_REQUEST_CODE);
	}
	
	private Uri converUri(Uri uri){
		
		InputStream is = null;
		
		try {
			is = getContentResolver().openInputStream(uri);
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			
			is.close();
			return saveUri(bitmap);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Uri saveUri(Bitmap bitmap){
		
		File tmpDir = new File(Environment.getExternalStorageDirectory() + "/zheng.com");
		if (!tmpDir.exists()) {
			tmpDir.mkdir();
		}
		
		File img = new File(tmpDir.getAbsolutePath() + "avater.png");
		
		try {
			FileOutputStream fos = new FileOutputStream(img);
			bitmap.compress(Bitmap.CompressFormat.PNG, 85, fos);
			
			fos.flush();
			fos.close();
			
			return Uri.fromFile(img);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
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
