package com.example.stallmanager;

import edu.gatech.seclass.prj2.CustomerTableData.CustomerTableInfo;
import edu.gatech.seclass.prj2.DatabaseOperations;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SelectcustomerActivity extends Activity {
	private DatabaseOperations DB;
	private SimpleCursorAdapter SCA = null;
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
	
	public void DeletePressed(View view) {
		AlertDialog.Builder bldr = new AlertDialog.Builder(ctx);
		bldr.setTitle("Confirm");
		bldr.setMessage("Do you really want to delete customer?");
		bldr.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				DB.deleteCustomer(DB, SelectedID);
				displayListView();
				dialog.dismiss();
			}
		});
		bldr.setNegativeButton("Keep", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog dialog = bldr.create();
		dialog.show();
	}
	
	public void EditPressed(View view) {
		Intent launchactivity = new Intent(ctx, EditCustomer.class);
		Bundle b = new Bundle();
		b.putString("KEY", SelectedID);
		launchactivity.putExtras(b);
		startActivity(launchactivity);
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

		try{
			SCA = new SimpleCursorAdapter(getBaseContext(), R.layout.customer_layout, cursor, col, to, 0);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ListView lv = (ListView)findViewById(R.id.transactionList);
		lv.setAdapter(SCA);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> lv, View v, int pos, long id) {
				//Log.d("Click received: ", "Click received");
				Cursor cr = (Cursor)lv.getItemAtPosition(pos);

				SelectedID = cr.getString(cr.getColumnIndexOrThrow(CustomerTableInfo.USER_ID));
			}
		});
		
		EditText filter = (EditText) findViewById(R.id.Search);
		filter.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				SCA.getFilter().filter(s.toString());
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
			@Override
			public void afterTextChanged(Editable s) { }
		});
		
		SCA.setFilterQueryProvider(new FilterQueryProvider() {
			@Override
			public Cursor runQuery(CharSequence constraint) {
				return DB.getInfoByKey(CustomerTableInfo.LAST_NAME, constraint.toString());

				//Log.d("Click received: ", "Cursor initialized");
				/*SelectedID = cr.getString(cr.getColumnIndexOrThrow("acctnum"));
				
				if(previousView == views.EDIT_CUSTOMER){
				String firstName = cr.getString(cr.getColumnIndexOrThrow("fname"));
				String lastName = cr.getString(cr.getColumnIndexOrThrow("lname"));
				String email = cr.getString(cr.getColumnIndexOrThrow("email"));
				String zip = cr.getString(cr.getColumnIndexOrThrow("zip"));
				EditCustomer.setValues(firstName, lastName, zip, email, SelectedID);
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
>>>>>>> fcb9f99f2c3e0152be16aaa1874bb6ccba7ddf32*/
			}
		});
	}
}
