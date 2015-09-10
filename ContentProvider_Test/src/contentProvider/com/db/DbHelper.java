/**
 * 
 */
package contentProvider.com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author michael
 *
 */
public class DbHelper extends SQLiteOpenHelper {

	public static final String STUDENT_TABLE = "student";
	
	private static String name = "mydb.db";
	private static int version = 1;
	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DbHelper(Context context) {
		super(context, name, null, version);
		
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table " + STUDENT_TABLE + "("
				+ "id integer primary key autoincrement,"
				+ "name varchar(64),"
				+ "address varchar(64))";
		db.execSQL(sql);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
