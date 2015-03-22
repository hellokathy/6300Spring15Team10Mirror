package com.example.stallmanager;

import edu.gatech.seclass.prj2.CustomerTableData.CustomerTableInfo;
import edu.gatech.seclass.prj2.DatabaseOperations;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SelectcustomerActivity extends Activity {
	private DatabaseOperations DB;
	//private SimpleCursorAdapter SCA;
	public enum views {EDIT_CUSTOMER, ADD_TRANSACTION, VIEW_TRANSACTIONS};
	public static views previousView;
	Context ctx = this;
	String SelectedID = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectcustomer);

		DB = new DatabaseOperations(ctx);
		DB.open();
		displayListView();
	}

	private void displayListView() {
		Cursor cursor = DB.getCustomerInfo(DB);
		String[] col = new String[] {
				CustomerTableInfo.USER_ID,
				CustomerTableInfo.FIRST_NAME,
				CustomerTableInfo.LAST_NAME,
				CustomerTableInfo.ZIP,
				CustomerTableInfo.EMAIL
		};

		int[] to = new int[] {
				R.id.uid,
				R.id.fname,
				R.id.lname,
				R.id.zip,
				R.id.mail
		};

		SimpleCursorAdapter SCA = null;
		try{
				SCA = new SimpleCursorAdapter(getBaseContext(), R.layout.customer_layout, cursor, col, to, 0);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ListView lv = (ListView)findViewById(R.id.transactionList);
		lv.setAdapter(SCA);
		Log.d("Waiting for click: ", "Waiting for click");
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> lv, View v, int pos, long id) {
				//Log.d("Click received: ", "Click received");
				Cursor cr = (Cursor)lv.getItemAtPosition(pos);
				//Log.d("Click received: ", "Cursor initialized");
				SelectedID = cr.getString(cr.getColumnIndexOrThrow("acctnum"));
				
				if(previousView == views.EDIT_CUSTOMER){
				String firstName = cr.getString(cr.getColumnIndexOrThrow("fname"));
				String lastName = cr.getString(cr.getColumnIndexOrThrow("lname"));
				String email = cr.getString(cr.getColumnIndexOrThrow("email"));
				String zip = cr.getString(cr.getColumnIndexOrThrow("zip"));
				EditCustomer.setValues(firstName, lastName, zip, email, SelectedID);
//				Log.d("Click received: ", "Selected ID set");
//				Log.d("Selected ID: ", String.valueOf(SelectedID));
				Intent launchactivity= new Intent(SelectcustomerActivity.this, EditCustomer.class);                             
				startActivity(launchactivity);
				}
				
				else if (previousView == views.ADD_TRANSACTION){
					//Add transaction brought us here
					AddTransaction.acct = SelectedID; 
					Intent launchactivity= new Intent(SelectcustomerActivity.this, AddTransaction.class);                             
					startActivity(launchactivity);
				}
				else if (previousView == views.VIEW_TRANSACTIONS){
					//View transaction brought us here
					ViewTransactions.acct = SelectedID;
					Intent launchactivity= new Intent(SelectcustomerActivity.this, ViewTransactions.class);                             
					startActivity(launchactivity);
				}
				finish();
			}
		});
	}
}
