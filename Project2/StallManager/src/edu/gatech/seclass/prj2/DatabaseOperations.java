package edu.gatech.seclass.prj2;

import edu.gatech.seclass.prj2.CustomerTableData.CustomerTableInfo;
import edu.gatech.seclass.prj2.TransactionTableData.TransactionTableInfo;
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
	public String CreateCustomerQuery = "CREATE TABLE if not exists " + CustomerTableInfo.TABLE_NAME + "(" + CustomerTableInfo.FIRST_NAME + " TEXT," + CustomerTableInfo.LAST_NAME + " TEXT," + 
			CustomerTableInfo.ZIP + " TEXT," + CustomerTableInfo.EMAIL + " TEXT," + CustomerTableInfo.USER_ID + " TEXT );";
	public String CreateTransactionQuery = "CREATE TABLE if not exists " + TransactionTableInfo.TABLE_NAME + "(" + TransactionTableInfo.DATE + " TEXT," + TransactionTableInfo.AMOUNT + " TEXT," + 
			CustomerTableInfo.USER_ID + " TEXT );";
	private DatabaseOperations dop;
	private Context ctx;
	private SQLiteDatabase sqldb;

	public DatabaseOperations(Context context) {
		super(context, CustomerTableInfo.DATABASE_NAME, null, DB_Version);
		this.ctx = context;
		Log.d("DataBase Operations", "Database Created");
	}

	@Override
	public void onCreate(SQLiteDatabase sdb) {
		sdb.execSQL(CreateCustomerQuery);
		sdb.execSQL(CreateTransactionQuery);
		this.sqldb = sdb;
		Log.d("DataBase Operations", "Table Created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public DatabaseOperations open() throws SQLException {
		this.dbop = new DatabaseOperations(ctx);
		return this;
	}

	public void deleteCustomer(DatabaseOperations dop, String ID) {
		sqldb = dop.getWritableDatabase();
		String[] args = {ID};
		sqldb.delete(CustomerTableInfo.TABLE_NAME, CustomerTableInfo.USER_ID + " LIKE ?", args);
	}

	public void EnterCustomerInfo(DatabaseOperations dop, String fname, String lname, String zip, String email, String acct) {
		sqldb = dop.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(CustomerTableInfo.FIRST_NAME, fname);
		cv.put(CustomerTableInfo.LAST_NAME, lname);
		cv.put(CustomerTableInfo.ZIP, zip);
		cv.put(CustomerTableInfo.EMAIL, email);
		cv.put(CustomerTableInfo.USER_ID, acct);

		long success = sqldb.insert(CustomerTableInfo.TABLE_NAME, null, cv);

		Log.d("DataBase Operations", "Database Row Inserted (Customer)");
	}

	public void EditCustomerInfo(DatabaseOperations dop, String fname, String lname, String zip, String email, String acct) {
		sqldb = dop.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(CustomerTableInfo.FIRST_NAME, fname);
		cv.put(CustomerTableInfo.LAST_NAME, lname);
		cv.put(CustomerTableInfo.ZIP, zip);
		cv.put(CustomerTableInfo.EMAIL, email);
		cv.put(CustomerTableInfo.USER_ID, acct);

		String selection = CustomerTableInfo.USER_ID + " = " + acct;

		sqldb.update(CustomerTableInfo.TABLE_NAME, cv, selection, null);

		Log.d("DataBase Operations", "Database Row Updated (Customer)");
	}

	public Cursor getCustomerInfo(DatabaseOperations dop) {
		sqldb = dop.getReadableDatabase();
		String[] col = {"rowid _id", CustomerTableInfo.USER_ID,CustomerTableInfo.FIRST_NAME,CustomerTableInfo.LAST_NAME,CustomerTableInfo.ZIP,CustomerTableInfo.EMAIL};
		Cursor cr = sqldb.query(CustomerTableInfo.TABLE_NAME, col, null, null, null, null, CustomerTableInfo.LAST_NAME);
		cr.moveToFirst();
		return cr;
	}

	public Cursor getInfoByKey(String searched, String query) throws SQLException {
		Cursor cr = null;
		String[] col = {"rowid _id", CustomerTableInfo.USER_ID,CustomerTableInfo.FIRST_NAME,CustomerTableInfo.LAST_NAME,CustomerTableInfo.ZIP,CustomerTableInfo.EMAIL};
		if(query == null || query.length() == 0 || searched == null || searched.length() == 0) {
			cr = sqldb.query(CustomerTableInfo.TABLE_NAME, col, null, null, null, null, CustomerTableInfo.LAST_NAME);
		}
		else {
			cr = sqldb.query(CustomerTableInfo.TABLE_NAME, col, searched + " like '%" + query + "%'", null, null, null, CustomerTableInfo.LAST_NAME);
		}
		if(cr != null) {
			cr.moveToFirst();
		}
		return cr;
	}

	public void EnterTransactionInfo(DatabaseOperations dop, String amount, String date, String acct) {
		sqldb = dop.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(TransactionTableInfo.AMOUNT, amount);
		cv.put(TransactionTableInfo.DATE, date);
		cv.put(TransactionTableInfo.USER_ID, acct);

		long success = sqldb.insert(TransactionTableInfo.TABLE_NAME, null, cv);

<<<<<<< HEAD
		Log.d("DataBase Operations", "Database Row Inserted (Transaction)");
=======
	Log.d("DataBase Operations", "Database Row Inserted (Transaction)");
}

public Cursor getTransactionInfo(DatabaseOperations dop) {
	sqldb = dop.getReadableDatabase();
	String[] col = {"rowid _id", TransactionTableInfo.USER_ID,TransactionTableInfo.DATE,TransactionTableInfo.AMOUNT,TransactionTableInfo.USER_ID};
	//Cursor cr = sqldb.query(CustomerTableInfo.TABLE_NAME, col, null, null, null, null, CustomerTableInfo.LAST_NAME);
	Cursor cr = sqldb.query(TransactionTableInfo.TABLE_NAME, col, null, null, null, null, TransactionTableInfo.DATE);
	cr.moveToFirst();
	return cr;
>>>>>>> fcb9f99f2c3e0152be16aaa1874bb6ccba7ddf32
	}
}
