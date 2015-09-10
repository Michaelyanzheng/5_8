/**
 * 
 */
package zheng.com.db_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author michael
 *
 */
public class DbOpenHelper extends SQLiteOpenHelper {

	public static int Version_Table = 1;
	
	public static String Table_name = "zheng.db";
	
	public static String CREATE_TABLE = "create table Person("
			+ "id integer primary key autoincrement,"
			+ "name varchar(30) not null,"
			+ "age interger"
			+ ")";

	public DbOpenHelper(Context context) {
		super(context, Table_name, null, Version_Table);
		
	}
;
	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
