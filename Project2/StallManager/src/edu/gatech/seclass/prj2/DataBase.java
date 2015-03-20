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
}
