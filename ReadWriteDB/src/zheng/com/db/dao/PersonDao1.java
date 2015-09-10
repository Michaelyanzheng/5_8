/**
 * 
 */
package zheng.com.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract.Columns;
import android.util.Log;
import zheng.com.db.server.Server1;
import zheng.com.db_db.DbOpenHelper;

/**
 * @author michael
 *
 */
public class PersonDao1 implements Server1{
	
	private DbOpenHelper dbOpenHelper = null;
	
	/**
	 * 
	 */
	public PersonDao1(Context context) {
		
		dbOpenHelper = new DbOpenHelper(context);
	}

	/* (non-Javadoc)
	 * @see zheng.com.db.server.Server1#addPerson(java.lang.Object[])
	 */
	@Override
	public boolean addPerson(Object[] bindArgs) {
		
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
		sqLiteDatabase.execSQL("insert into Person(name,age) values(?,?)", bindArgs);
		
		return false;
	}

	/* (non-Javadoc)
	 * @see zheng.com.db.server.Server1#deletePerson(java.lang.Object[])
	 */
	@Override
	public boolean deletePerson(Object[] bindArgs) {
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
		sqLiteDatabase.execSQL("delete from Person where id = ?", bindArgs);
		
		return false;
	}

	/* (non-Javadoc)
	 * @see zheng.com.db.server.Server1#updatePerson(java.lang.Object[])
	 */
	@Override
	public boolean updatePerson(Object[] bindArgs) {
		
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
		sqLiteDatabase.execSQL("update Person set age = ? where name = ?", bindArgs);
		return false;
	}

	/* (non-Javadoc)
	 * @see zheng.com.db.server.Server1#selectAllPerson(java.lang.Object[])
	 */
	@Override
	public boolean selectAllPerson(Object[] bindArgs) {
		return false;
	}

	/* (non-Javadoc)
	 * @see zheng.com.db.server.Server1#selectOnePerson(java.lang.String[])
	 */
	@Override
	public Map<String, String> selectOnePerson(String[] bindArgs) {
		SQLiteDatabase sqLiteDatabase = dbOpenHelper.getWritableDatabase();
		Cursor cursor = sqLiteDatabase.query("Person", null, "id = ?", bindArgs, null, null, null);
//		int columns = cursor.getColumnCount();
//		for (int i = 0; i < columns; i++) {
//			int id = cursor.getInt(cursor.getColumnIndex("id"));
//			String name = cursor.getString(1);
			
//			int age = cursor.getInt(cursor.getColumnIndex("age"));
			Log.d("AndroidTestCase", cursor.getCount()+"");
		cursor.close();
//		}
		return null;
	}

}
