/**
 * 
 */
package contentProvider.com;

import contentProvider.com.db.DbHelper;
import android.R.integer;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * @author michael
 *
 */
public class StudentProvider extends ContentProvider {
	
	private final String TAG = "StudentProvider";
	
	private DbHelper helper = null;
	
	private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	
	private static final int STUDENT = 1;
	private static final int STUDENTS = 2;
	
	static{
		URI_MATCHER.addURI("contentProvider.com.StudentProvider", "student", STUDENTS);
		URI_MATCHER.addURI("contentProvider.com.StudentProvider", "student/#", STUDENT);
	}
	/**
	 * 
	 */
	public StudentProvider() {
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#onCreate()
	 */
	@Override
	public boolean onCreate() {
		helper = new DbHelper(getContext());
		return false;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		return null;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#getType(android.net.Uri)
	 */
	@Override
	public String getType(Uri uri) {
		int flag = URI_MATCHER.match(uri);
		switch (flag) {
		case STUDENT:
			
			return "vnd.android.cursor.item/student";

		case STUDENTS:
			
			return "vnd.android.cursor.dir/students";
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Uri resultUri = null;
		int flag = URI_MATCHER.match(uri);
		switch (flag) {
		case STUDENTS:
			SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
			long id = sqLiteDatabase.insert(DbHelper.STUDENT_TABLE, null, values);
			resultUri = ContentUris.withAppendedId(uri, id);
			
			break;
		}
		Log.i(TAG, resultUri.toString());
		return resultUri;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		int count = -1;
		try {
			int flag = URI_MATCHER.match(uri);
			SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
			switch (flag) {
			case STUDENT:
				long id = ContentUris.parseId(uri);
				String where_value = " id = " + id;
				if (selection != null && selection.equals("")) {
					where_value += " and " + selection;
				}
				count = sqLiteDatabase.delete(DbHelper.STUDENT_TABLE, where_value, selectionArgs);
				break;

			case STUDENTS:
				count = sqLiteDatabase.delete(DbHelper.STUDENT_TABLE, selection, selectionArgs);
				break;
			}
		} catch (Exception e) {
			
		}
		
		return count;
	}

	/* (non-Javadoc)
	 * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
