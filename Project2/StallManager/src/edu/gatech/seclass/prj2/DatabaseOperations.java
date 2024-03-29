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
import android.widget.Toast;

public class DatabaseOperations extends SQLiteOpenHelper {
	public static final int DB_Version = 1;
	public String CreateCustomerQuery = "CREATE TABLE if not exists " + CustomerTableInfo.TABLE_NAME + "(" + CustomerTableInfo.FIRST_NAME + " TEXT," + CustomerTableInfo.LAST_NAME + " TEXT," + 
			CustomerTableInfo.ZIP + " TEXT," + CustomerTableInfo.EMAIL + " TEXT," + CustomerTableInfo.USER_ID + " TEXT," + CustomerTableInfo.GOLD_STATUS + " INTEGER," + 
			CustomerTableInfo.TOTAL_SPENT + " REAL," + CustomerTableInfo.DISCOUNT + " TEXT );";
	public String CreateTransactionQuery = "CREATE TABLE if not exists " + TransactionTableInfo.TABLE_NAME + "(" + TransactionTableInfo.DATE + " TEXT," + 
			TransactionTableInfo.AMOUNT + " TEXT," + TransactionTableInfo.USER_ID + " TEXT," + TransactionTableInfo.GOLD_STATUS + " INTEGER," + 
			TransactionTableInfo.DISCOUNT_USED + " REAL );";
	private DatabaseOperations dbop;
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
		sqldb.delete(TransactionTableInfo.TABLE_NAME, TransactionTableInfo.USER_ID + " LIKE ?", args);
	}

	public void EnterCustomerInfo(DatabaseOperations dop, String fname, String lname, String zip, String email, String acct) {
		sqldb = dop.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(CustomerTableInfo.FIRST_NAME, fname);
		cv.put(CustomerTableInfo.LAST_NAME, lname);
		cv.put(CustomerTableInfo.ZIP, zip);
		cv.put(CustomerTableInfo.EMAIL, email);
		cv.put(CustomerTableInfo.USER_ID, acct);
		cv.put(CustomerTableInfo.GOLD_STATUS, 0);
		cv.put(CustomerTableInfo.TOTAL_SPENT, 0.00);
		cv.put(CustomerTableInfo.DISCOUNT, 0.00);

		long success = sqldb.insert(CustomerTableInfo.TABLE_NAME, null, cv);

		Log.d("DataBase Operations", "Database Row Inserted (Customer)");
	}

	public void EditCustomerInfo(DatabaseOperations dop, String fname, String lname, String zip, String email, String oldAcct, String newAcct) {
		sqldb = dop.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(CustomerTableInfo.FIRST_NAME, fname);
		cv.put(CustomerTableInfo.LAST_NAME, lname);
		cv.put(CustomerTableInfo.ZIP, zip);
		cv.put(CustomerTableInfo.EMAIL, email);
		cv.put(CustomerTableInfo.USER_ID, newAcct);

		String selection = CustomerTableInfo.USER_ID + " = " + oldAcct;

		sqldb.update(CustomerTableInfo.TABLE_NAME, cv, selection, null);

		Log.d("DataBase Operations", "Database Row Updated (Customer)");
	}
	
	public void EditCustomerInfo(DatabaseOperations dop, String acct, double total, int gold, double discount) {
		sqldb = dop.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(CustomerTableInfo.USER_ID, acct);
		cv.put(CustomerTableInfo.TOTAL_SPENT, total);
		cv.put(CustomerTableInfo.GOLD_STATUS, gold);
		cv.put(CustomerTableInfo.DISCOUNT, discount);

		String selection = CustomerTableInfo.USER_ID + " = " + acct;

		sqldb.update(CustomerTableInfo.TABLE_NAME, cv, selection, null);

		Log.d("DataBase Operations", "Database Row Updated (Customer)");
	}
	
	public void EditTransactionInfo(DatabaseOperations dop, String oldAcct, String newAcct) {
		sqldb = dop.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(TransactionTableInfo.USER_ID, newAcct);

		String selection = TransactionTableInfo.USER_ID + " = " + oldAcct;

		sqldb.update(TransactionTableInfo.TABLE_NAME, cv, selection, null);

		Log.d("DataBase Operations", "Database Row Updated (Transaction)");
	}

	public Cursor getCustomerInfo(DatabaseOperations dop) {
		sqldb = dop.getReadableDatabase();
		String[] col = {"rowid _id", CustomerTableInfo.USER_ID,CustomerTableInfo.FIRST_NAME,CustomerTableInfo.LAST_NAME,CustomerTableInfo.ZIP,CustomerTableInfo.EMAIL};
		Cursor cr = sqldb.query(CustomerTableInfo.TABLE_NAME, col, null, null, null, null, CustomerTableInfo.LAST_NAME);
		cr.moveToFirst();
		return cr;
	}

	public Cursor getInfoByKey(DatabaseOperations dop, String searched, String query) throws SQLException {
		sqldb = dop.getReadableDatabase();
		Cursor cr = null;
		String[] col = {"rowid _id", CustomerTableInfo.FIRST_NAME,CustomerTableInfo.LAST_NAME,CustomerTableInfo.ZIP,CustomerTableInfo.EMAIL,
				CustomerTableInfo.USER_ID,CustomerTableInfo.GOLD_STATUS,CustomerTableInfo.TOTAL_SPENT, CustomerTableInfo.DISCOUNT};
		if(query == null || query.length() == 0 || searched == null || searched.length() == 0) {
			cr = sqldb.query(CustomerTableInfo.TABLE_NAME, col, null, null, null, null, CustomerTableInfo.LAST_NAME);
		}
		else {
			cr = sqldb.query(CustomerTableInfo.TABLE_NAME, col, searched + " like '%" + query + "%'", null, null, null, CustomerTableInfo.LAST_NAME);
			//cr = sqldb.query(CustomerTableInfo.TABLE_NAME, col, searched + " LIKE ?", new String[] {query}, null, null, CustomerTableInfo.LAST_NAME);
		}
		if(cr != null) {
			cr.moveToFirst();
		}
		return cr;
	}
	
	public Cursor getLargestAcctnum(DatabaseOperations dop) {
		sqldb = dop.getReadableDatabase();
		Cursor cr = null;
		String[] col = {"rowid _id", CustomerTableInfo.FIRST_NAME,CustomerTableInfo.LAST_NAME,CustomerTableInfo.ZIP,CustomerTableInfo.EMAIL,
				CustomerTableInfo.USER_ID,CustomerTableInfo.GOLD_STATUS,CustomerTableInfo.TOTAL_SPENT, CustomerTableInfo.DISCOUNT};
		cr = sqldb.query(CustomerTableInfo.TABLE_NAME, col, null, null, null, null, "CAST('" + CustomerTableInfo.USER_ID + "' AS INTEGER)");
		cr.moveToLast();
		return cr;
	}

	public void EnterTransactionInfo(DatabaseOperations dop, String amount, String date, String acct, double goldDiscount, double discount) {
		sqldb = dop.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(TransactionTableInfo.AMOUNT, amount);
		cv.put(TransactionTableInfo.DATE, date);
		cv.put(TransactionTableInfo.USER_ID, acct);
		cv.put(TransactionTableInfo.GOLD_STATUS, goldDiscount);
		cv.put(TransactionTableInfo.DISCOUNT_USED, discount);

		sqldb.insert(TransactionTableInfo.TABLE_NAME, null, cv);

		Log.d("DataBase Operations", "Database Row Inserted (Transaction)");
	}

	public Cursor getTransactionInfo(DatabaseOperations dop) {
		sqldb = dop.getReadableDatabase();
		String[] col = {"rowid _id", TransactionTableInfo.USER_ID,TransactionTableInfo.DATE,
				TransactionTableInfo.AMOUNT,TransactionTableInfo.USER_ID,TransactionTableInfo.GOLD_STATUS,TransactionTableInfo.DISCOUNT_USED};
		
		Cursor cr = sqldb.query(TransactionTableInfo.TABLE_NAME, col, null, null, null, null, TransactionTableInfo.DATE);
		cr.moveToFirst();
		return cr;
	}
	
	public Cursor getTransactionInfo(DatabaseOperations dop, String acctnum) {
		sqldb = dop.getReadableDatabase();
		String[] col = {"rowid _id", TransactionTableInfo.USER_ID,TransactionTableInfo.DATE,
				TransactionTableInfo.AMOUNT,TransactionTableInfo.USER_ID,TransactionTableInfo.GOLD_STATUS,TransactionTableInfo.DISCOUNT_USED};
		
		Cursor cr = sqldb.query(TransactionTableInfo.TABLE_NAME, col, "acctnum='" + ViewTransactions.acct + "'", null, null, null, TransactionTableInfo.DATE);
		cr.moveToFirst();
		return cr;
	}
}
