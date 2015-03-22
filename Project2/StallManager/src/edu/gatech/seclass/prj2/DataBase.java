package edu.gatech.seclass.prj2;
import java.util.ArrayList;
import java.util.HashMap; 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

	public DataBase(Context applicationcontext) {
		super(applicationcontext, "customers.db", null, 1);

	}

	public void onCreate(SQLiteDatabase db) {

		String query = "CREATE TABLE customers ( customerId INTEGER PRIMARY KEY, firstName TEXT, " +
				"lastName TEXT, zipCode TEXT, emailAddress TEXT)";

		db.execSQL(query);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String query = "DROP TABLE IF EXISTS customers";
		db.execSQL(query);
		onCreate(db);
	}



	public void insertContact(HashMap<String, String> queryValues) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("firstName", queryValues.get("firstName"));
		values.put("lastName", queryValues.get("lastName"));
		values.put("zipCode", queryValues.get("zipCode"));
		values.put("emailAddress", queryValues.get("emailAddress"));


		db.insert("customers", null, values);


		db.close();
	}

	public int updateContact(HashMap<String, String> queryValues) {

		SQLiteDatabase database = this.getWritableDatabase();  

		ContentValues values = new ContentValues();

		values.put("firstName", queryValues.get("firstName"));
		values.put("lastName", queryValues.get("lastName"));
		values.put("zipCode", queryValues.get("zipCode"));
		values.put("emailAddress", queryValues.get("emailAddress"));

		return database.update("customers", values, "customerId" + " = ?", new String[] { queryValues.get("customerID") });
	}
	public ArrayList<HashMap<String, String>> getAllContacts() {

		// ArrayList that contains every row in the database
		// and each row key / value stored in a HashMap

		ArrayList<HashMap<String, String>> contactArrayList;

		contactArrayList = new ArrayList<HashMap<String, String>>();

		String selectQuery = "SELECT  * FROM contacts";

		// Open a database for reading and writing

		SQLiteDatabase database = this.getWritableDatabase();

		// Cursor provides read and write access for the
		// data returned from a database query

		// rawQuery executes the query and returns the result as a Cursor

		Cursor cursor = database.rawQuery(selectQuery, null);  

		// Move to the first row

		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> contactMap = new HashMap<String, String>();

				// Store the key / value pairs in a HashMap
				// Access the Cursor data by index that is in the same order
				// as used when creating the table

				contactMap.put("contactId", cursor.getString(0));
				contactMap.put("firstName", cursor.getString(1));
				contactMap.put("lastName", cursor.getString(2));
				contactMap.put("phoneNumber", cursor.getString(3));
				contactMap.put("emailAddress", cursor.getString(4));
				contactMap.put("homeAddress", cursor.getString(5));

				contactArrayList.add(contactMap);
			} while (cursor.moveToNext()); // Move Cursor to the next row
		}

		// return contact list
		return contactArrayList;
	}

	public HashMap<String, String> getContactInfo(String id) {
		HashMap<String, String> contactMap = new HashMap<String, String>();

		// Open a database for reading only

		SQLiteDatabase database = this.getReadableDatabase();

		String selectQuery = "SELECT * FROM contacts where contactId='"+id+"'";

		//  executes the query and returns the result as a Cursor

		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {

				contactMap.put("firstName", cursor.getString(1));
				contactMap.put("lastName", cursor.getString(2));
				contactMap.put("phoneNumber", cursor.getString(3));
				contactMap.put("emailAddress", cursor.getString(4));
				contactMap.put("homeAddress", cursor.getString(5));

			} while (cursor.moveToNext());
		}                  
		return contactMap;
	}  
}


