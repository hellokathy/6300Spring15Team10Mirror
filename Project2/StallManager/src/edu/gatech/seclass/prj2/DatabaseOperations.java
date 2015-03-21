package edu.gatech.seclass.prj2;

import edu.gatech.seclass.prj2.CustomerTableData.TableInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperations extends SQLiteOpenHelper {
	public static final int DB_Version = 1;
	public String CreateQuery = "CREATE TABLE if not exists " + TableInfo.TABLE_NAME + "(" + TableInfo.FIRST_NAME + " TEXT," + TableInfo.LAST_NAME + " TEXT," + 
	TableInfo.ZIP + " TEXT," + TableInfo.EMAIL + " TEXT," + TableInfo.USER_ID + " TEXT );";
	private DatabaseOperations dop;
	private Context ctx;
	private SQLiteDatabase sqldb;

	public DatabaseOperations(Context context) {
		super(context, TableInfo.DATABASE_NAME, null, DB_Version);
		this.ctx = context;
		Log.d("DataBase Operations", "Database Created");
	}

	@Override
	public void onCreate(SQLiteDatabase sdb) {
		sdb.execSQL(CreateQuery);
		this.sqldb = sdb;
		Log.d("DataBase Operations", "Table Created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	public DatabaseOperations open() throws SQLException {
		this.dop = new DatabaseOperations(ctx);
		return this;
	}
	
	public void EnterInfo(DatabaseOperations dop, String fname, String lname, String zip, String email, String acct) {
		SQLiteDatabase sqldb = dop.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(TableInfo.FIRST_NAME, fname);
		cv.put(TableInfo.LAST_NAME, lname);
		cv.put(TableInfo.ZIP, zip);
		cv.put(TableInfo.EMAIL, email);
		cv.put(TableInfo.USER_ID, acct);
		long success = sqldb.insert(TableInfo.TABLE_NAME, null, cv);
		Log.d("DataBase Operations", "Database Row Inserted");
	}
	
	public Cursor getInfo(DatabaseOperations dop) {
		SQLiteDatabase sqldb = dop.getReadableDatabase();
		String[] col = {TableInfo.USER_ID,TableInfo.FIRST_NAME,TableInfo.LAST_NAME,TableInfo.ZIP,TableInfo.EMAIL};
		Cursor cr = sqldb.query(TableInfo.TABLE_NAME, col, null, null, null, null, TableInfo.LAST_NAME);
		cr.moveToFirst();
		return cr;
		}
	}
